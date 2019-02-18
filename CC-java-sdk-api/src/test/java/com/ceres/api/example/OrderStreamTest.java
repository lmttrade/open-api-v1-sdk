package com.ceres.api.example;

import com.ceres.api.client.CoinceresApiClientFactory;
import com.ceres.api.domain.stream.EntrustNotice;
import com.ceres.api.domain.stream.PositionNotice;
import com.ceres.api.service.CoinceresTradeWebSocketClient;
import com.ceres.api.util.JsonUtils;
import junit.framework.TestCase;

public class OrderStreamTest {
    private static CoinceresTradeWebSocketClient orderStreamClient;
    static  {
        orderStreamClient = CoinceresApiClientFactory.newInstance("jfAFaUgGPexhzsnJ","jDW3sYWwV6LZRo3plnrpJmK9EkJrwjZM").newTradeWebSocketClient();
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
        } catch (Exception e) {
            TestCase.fail(e.getMessage());
        }
    }
}
