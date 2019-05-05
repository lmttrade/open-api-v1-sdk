package com.ceres.api.example;

import com.ceres.api.client.CoinceresApiClientFactory;
import com.ceres.api.domain.stream.EntrustNotice;
import com.ceres.api.domain.stream.PositionNotice;
import com.ceres.api.service.CoinceresTradeWebSocketClient;
import com.ceres.api.util.JsonUtils;
import junit.framework.TestCase;

/**
 * 订单状态数据推送
 *
 * @author xiaotian.huang
 * @date 2019/05/05
 */
public class OrderStreamTest {

    private static CoinceresTradeWebSocketClient orderStreamClient;

    static  {
        orderStreamClient = CoinceresApiClientFactory.newInstance("neFwtAEZHixtLMns","n2TqzGIbXobj7Yh0B9Jl8JofFliq6VkM").newTradeWebSocketClient();
    }

    public static void main(String[] args) {
        try {
            orderStreamClient.onOrderStreamEvent(response -> {
                String json = JsonUtils.serialize(response);

                int messageType = response.getMessageType();
                if (1 == messageType) {
                    // entrustNotice
                    EntrustNotice entrustNotice = JsonUtils.parse(json, EntrustNotice.class);
                    System.out.println(entrustNotice);
                } else {
                    // positionNotice
                    PositionNotice positionNotice = JsonUtils.parse(json, PositionNotice.class);
                    System.out.println(positionNotice);
                }
            });
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }
}
