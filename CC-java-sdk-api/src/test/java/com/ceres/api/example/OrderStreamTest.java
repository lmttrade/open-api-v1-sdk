package com.ceres.api.example;

import com.ceres.api.client.CoinceresApiClientFactory;
import com.ceres.api.service.CoinceresTradeWebSocketClient;

/**
 * 订单状态数据推送
 *
 * @author xiaotian.huang
 * @date 2019/05/05
 */
public class OrderStreamTest {

    private static CoinceresTradeWebSocketClient orderStreamClient;

    static  {
        orderStreamClient = CoinceresApiClientFactory.newInstance("bVHOwaYzkmtfSUXr","b7tKSQahoYzfcI7nwJ0qAgXXuArzTstl")
                .newTradeWebSocketClient();
    }

    public static void main(String[] args) {
        try {
            orderStreamClient.onOrderStreamEvent(response -> {
                System.out.println(response);
//                String json = JsonUtils.serialize(response);
//
//                int messageType = response.getMessageType();
//                if (1 == messageType) {
//                    // entrustNotice
//                    EntrustNotice entrustNotice = JsonUtils.parse(json, EntrustNotice.class);
//                    System.out.println(entrustNotice);
//                } else {
//                    // positionNotice
//                    PositionNotice positionNotice = JsonUtils.parse(json, PositionNotice.class);
//                    System.out.println(positionNotice);
//                }
            });
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }
}
