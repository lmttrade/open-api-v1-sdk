package com.ceres.api.example;

import com.ceres.api.client.CoinceresApiClientFactory;
import com.ceres.api.domain.stream.EntrustNotice;
import com.ceres.api.domain.stream.PositionNotice;
import com.ceres.api.service.CoinceresTradeWebSocketClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;

public class OrderStreamTest {
    private static CoinceresTradeWebSocketClient orderStreamClient;
    private static ObjectMapper mapper;

    static  {
        orderStreamClient = CoinceresApiClientFactory.newInstance("jfAFaUgGPexhzsnJ","jDW3sYWwV6LZRo3plnrpJmK9EkJrwjZM").newTradeWebSocketClient();
        mapper = new ObjectMapper();
    }

    public static void main(String[] args) {
        try {
            orderStreamClient.onOrderStreamEvent(response -> {
                String json = mapper.writeValueAsString(response);
                int messageType = response.getMessageType();
                if (1 == messageType) {
                    // entrustNotice
                    EntrustNotice entrustNotice = mapper.readValue(json, EntrustNotice.class);
                    System.out.println(entrustNotice);
                } else {
                    // positionNotice
                    PositionNotice positionNotice = mapper.readValue(json, PositionNotice.class);
                    System.out.println(positionNotice);
                }
            });
        } catch (Exception e) {
            TestCase.fail(e.getMessage());
        }
    }
}
