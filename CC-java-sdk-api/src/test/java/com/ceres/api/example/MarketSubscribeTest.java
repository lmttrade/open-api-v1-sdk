package com.ceres.api.example;

import com.ceres.api.client.CoinceresApiClientFactory;
import com.ceres.api.domain.market.SubscribeReq;
import com.ceres.api.service.CoinceresApiWebSocketClient;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

/**
 * 关于行情订阅的示例
 *
 * @author xiaotian.huang
 * @date 2019/01/30
 */
public class MarketSubscribeTest {

    private static CoinceresApiWebSocketClient ccClient;
    private static ObjectMapper mapper;

    static {
        ccClient = CoinceresApiClientFactory.newWebSocketClient();
        mapper = new ObjectMapper();
    }

    public static void main(String[] args) {
        // 订阅trade
         //subTrade();

        // 订阅tick
<<<<<<< HEAD
//         subTick();
=======
         subTick();
>>>>>>> 20b2108442387d42703ed9049485242ed774e837

        // 订阅candle
         //subCandle();

        // 订阅depth
         //subDepth();

        // 心跳维护
        // heartbeat();
    }

    /**
     * 订阅depth
     */
    private static void subDepth() {
        SubscribeReq subscribeReq = new SubscribeReq();
        subscribeReq.setDataType("AskBidQueue");
        subscribeReq.setExchange("");
        subscribeReq.setSymbol("ETH/BTC");
        List<SubscribeReq> subscribeReqList = Arrays.asList(subscribeReq);
        try {
            String text = mapper.writeValueAsString(subscribeReqList);
            ccClient.onDepthEvent(text, reponse -> System.out.println(reponse));
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }

    /**
     * 订阅candle
     */
    private static void subCandle() {
        SubscribeReq subscribeReq = new SubscribeReq();
        subscribeReq.setDataType("Cycle");
        subscribeReq.setExchange("");
        subscribeReq.setSymbol("ETH/BTC");
        subscribeReq.setDuration("5");
        List<SubscribeReq> subscribeReqList = Arrays.asList(subscribeReq);
        try {
            String text = mapper.writeValueAsString(subscribeReqList);
            ccClient.onCandleEvent(text, response -> System.out.println(response));
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }

    /**
     * 订阅tick
     */
    private static void subTick() {
        SubscribeReq subscribeReq = new SubscribeReq();
        subscribeReq.setDataType("Tick");
        subscribeReq.setExchange("HUOBI");
        subscribeReq.setSymbol("HT/BTC");
        List<SubscribeReq> subscribeReqList = Arrays.asList(subscribeReq);
        try {
            String text = mapper.writeValueAsString(subscribeReqList);
            ccClient.onTickEvent(text, response -> System.out.println(response));
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }

    /**
     * 订阅trade
     */
    private static void subTrade() {
        SubscribeReq subscribeReq = new SubscribeReq();
        subscribeReq.setDataType("Trade");
        subscribeReq.setExchange("BINANCE");
        subscribeReq.setSymbol("ETH/BTC");

        List<SubscribeReq> subscribeReqList = Arrays.asList(subscribeReq);
        try {
            String text = mapper.writeValueAsString(subscribeReqList);
            ccClient.onTradeEvent(text, response -> System.out.println(response.toString()));
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }

    /**
     * 心跳
     */
    private static void heartbeat() {
        try {
            String text = "ping";
            ccClient.onTradeEvent(text, response -> System.out.println(response.toString()));
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }

}
