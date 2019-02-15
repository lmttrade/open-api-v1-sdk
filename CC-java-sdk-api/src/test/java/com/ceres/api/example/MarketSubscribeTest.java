package com.ceres.api.example;

import com.ceres.api.client.CoinceresApiClientFactory;
import com.ceres.api.domain.market.SubscribeReq;
import com.ceres.api.service.CoinceresApiWebSocketClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;

/**
 * @author xiaotian.huang
 * @date 2019/01/30
 * 关于行情订阅的示例
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
//        SubscribeReq subscribeReq = new SubscribeReq();
//        subscribeReq.setMsgType("subscribe-trade");
//        subscribeReq.setExchange("OKEX");
//        subscribeReq.setContract("BTC/USDT");
//        List<SubscribeReq> subscribeReqList = Arrays.asList(subscribeReq);
//        try {
//            String text = mapper.writeValueAsString(subscribeReqList);
//            ccClient.onTradeEvent(text,response -> System.out.println(response.toString()));
//        } catch (Exception e) {
//            TestCase.fail(e.getMessage());
//        }

        // 订阅tick
//        SubscribeReq subscribeReq = new SubscribeReq();
//        subscribeReq.setMsgType("subscribe-tick");
//        subscribeReq.setExchange("OKEX");
//        subscribeReq.setContract("BTC/USDT");
//        List<SubscribeReq> subscribeReqList = Arrays.asList(subscribeReq);
//        try {
//            String text = mapper.writeValueAsString(subscribeReqList);
//            ccClient.onTickEvent(text, response -> System.out.println(response));
//        } catch (Exception e) {
//            TestCase.fail(e.getMessage());
//        }

        // 订阅candle
        SubscribeReq subscribeReq = new SubscribeReq();
        subscribeReq.setMsgType("subscribe-candle");
        subscribeReq.setExchange("OKEX");
        subscribeReq.setContract("BTC/USDT");
        subscribeReq.setDuration("1m");
        List<SubscribeReq> subscribeReqList = Arrays.asList(subscribeReq);
        try {
            String text = mapper.writeValueAsString(subscribeReqList);
            ccClient.onCandleEvent(text, response -> System.out.println(response));
        } catch (Exception e) {
            TestCase.fail(e.getMessage());
        }

        // 订阅depth
//        SubscribeReq subscribeReq = new SubscribeReq();
//        subscribeReq.setMsgType("subscribe-depth10");
//        subscribeReq.setExchange("OKEX");
//        subscribeReq.setContract("BTC/USDT");
//        List<SubscribeReq> subscribeReqList = Arrays.asList(subscribeReq);
//        try {
//            String text = mapper.writeValueAsString(subscribeReqList);
//            ccClient.onDepthEvent(text, reponse-> System.out.println(reponse));
//        } catch (Exception e) {
//            TestCase.fail(e.getMessage());
//        }
    }

}
