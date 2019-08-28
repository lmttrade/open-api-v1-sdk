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

    static  {
        ccClient = CoinceresApiClientFactory.newWebSocketClient();
        mapper = new ObjectMapper();
    }

    public static void main(String[] args) {
        // 订阅trade
        subTrade();

        // 订阅tick
        subTick();

        // 订阅candle
        subCandle();

        // 订阅depth
        subDepth();
    }

    /** 订阅depth */
    private static void subDepth() {
        SubscribeReq subscribeReq = new SubscribeReq();
        subscribeReq.setMsgType("subscribe-depth10");
        subscribeReq.setExchange("OKEX");
        subscribeReq.setSymbol("BTC/USDT");
        List<SubscribeReq> subscribeReqList = Arrays.asList(subscribeReq);
        try {
            String text = mapper.writeValueAsString(subscribeReqList);
            ccClient.onDepthEvent(text, reponse -> System.out.println(reponse));
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }

    /** 订阅candle */
    private static void subCandle() {
        SubscribeReq subscribeReq = new SubscribeReq();
        subscribeReq.setMsgType("subscribe-candle");
        subscribeReq.setExchange("OKEX");
        subscribeReq.setSymbol("BTC/USDT");
        subscribeReq.setDuration("1m");
        List<SubscribeReq> subscribeReqList = Arrays.asList(subscribeReq);
        try {
            String text = mapper.writeValueAsString(subscribeReqList);
            ccClient.onCandleEvent(text, response -> System.out.println(response));
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }

    /** 订阅tick */
    private static void subTick() {
        SubscribeReq subscribeReq = new SubscribeReq();
        subscribeReq.setMsgType("subscribe-tick");
        subscribeReq.setExchange("OKEX");
        subscribeReq.setSymbol("BTC/USDT");
        List<SubscribeReq> subscribeReqList = Arrays.asList(subscribeReq);
        try {
            String text = mapper.writeValueAsString(subscribeReqList);
            ccClient.onTickEvent(text, response -> System.out.println(response));
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }

    /** 订阅trade */
    private static void subTrade() {
        SubscribeReq subscribeReq = new SubscribeReq();
        subscribeReq.setMsgType("subscribe-trade");
        subscribeReq.setExchange("OKEX");
        subscribeReq.setSymbol("BTC/USDT");
        List<SubscribeReq> subscribeReqList = Arrays.asList(subscribeReq);
        try {
            String text = mapper.writeValueAsString(subscribeReqList);
            ccClient.onTradeEvent(text, response -> System.out.println(response.toString()));
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }

}
