package com.ceres.api.example;

import com.ceres.api.client.CoinceresApiClientFactory;
import com.ceres.api.domain.data.CycleReq;
import com.ceres.api.domain.data.CycleResData;
import com.ceres.api.domain.data.FlashIndicativePriceData;
import com.ceres.api.domain.data.FlashIndicativePriceReq;
import com.ceres.api.domain.enums.ActionTypeEnum;
import com.ceres.api.domain.enums.ExchangeEnum;
import com.ceres.api.domain.market.SubscribeReq;
import com.ceres.api.domain.trade.CurrencyPair;
import com.ceres.api.domain.trade.ResultsVO;
import com.ceres.api.domain.trade.SymbolReq;
import com.ceres.api.service.CoinceresApiWebSocketClient;
import com.ceres.api.service.CoinceresDataRestClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import util.PrettyPrinter;

import java.util.Arrays;
import java.util.List;

/**
 * 关于行情查询和订阅的示例
 * 具体文档请参考Github https://github.com/coinceres/open-api-v1-sdk/blob/master/docs/market/%E8%A1%8C%E6%83%85api.md
 *
 * @author LMT
 * @date 2019/01/30
 */
public class MarketTest {
    private static CoinceresDataRestClient dataRestClient;
    private static CoinceresApiWebSocketClient dataWebSocketClient;
    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        dataRestClient = CoinceresApiClientFactory.newInstance().newDataClient();
        dataWebSocketClient = CoinceresApiClientFactory.newWebSocketClient();
    }


    public static void main(String[] args) {
        //Rest API
        // 01. 请求币对信息
        querySymbols();

        // 02. 请求闪电交易币对报价信息
        queryFlashIndicativePrice();

        // 03. 请求历史分钟线数据
        queryCycleLine();

        /*
         * 注意：SDK请求服务端后，如果不同币对多次请求会认为是同一个客户端，数据会合并推送，解决方案：
         * 1、根据业务需求订阅之前，先取消订阅之前的币对，再订阅新的币对行情信息
         * 2、一直订阅，SDK根据需求筛选需要的币对信息，
         */
        //WebSocket API
        // 订阅trade
        subTrade();

        // 订阅tick
        subTick();

        // 订阅candle
        subCandle();

        // 订阅depth
        subDepth();

        //取消订阅
        //unSubTrade();
        // 心跳维护
        // heartbeat();
    }


    /**
     * 查询交易对详细信息
     */
    private static void querySymbols() {
        SymbolReq req = new SymbolReq();
        req.setExchange(ExchangeEnum.LMT.getValue());
        req.setSymbol("BTC/USDT");
        ResultsVO<List<CurrencyPair>> result = dataRestClient.getSymbols(req);
        PrettyPrinter.println(result);
    }

    /**
     * 请求闪电交易币对报价信息
     */
    private static void queryFlashIndicativePrice() {
        FlashIndicativePriceReq req = new FlashIndicativePriceReq();
        req.setExchange(ExchangeEnum.INSTANTEX.getValue());
        req.setSymbol("BTC/USDT");
        ResultsVO<FlashIndicativePriceData> result = dataRestClient.queryFlashIndicativePrice(req);
        PrettyPrinter.println(result);
    }

    /**
     * 请求历史分钟线数据
     */
    private static void queryCycleLine() {
        CycleReq req = new CycleReq();
        req.setExchange(ExchangeEnum.LMT.getValue());
        req.setSymbol("BTC/USDT");
        req.setBegin(1567683193L);
        req.setEnd(System.currentTimeMillis() / 1000);
        req.setDuration(1);
        ResultsVO<CycleResData> result = dataRestClient.queryHistoryCycleData(req);
        PrettyPrinter.println(result);
    }


    /**
     * 订阅depth
     */
    private static void subDepth() {
        SubscribeReq subscribeReq = new SubscribeReq();
        subscribeReq.setDataType("AskBidQueue");
        subscribeReq.setActionType(ActionTypeEnum.sub.name());
        subscribeReq.setExchange(ExchangeEnum.HUOBI.getValue());
        subscribeReq.setSymbol("ETH/BTC");
        List<SubscribeReq> subscribeReqList = Arrays.asList(subscribeReq);
        try {
            String text = mapper.writeValueAsString(subscribeReqList);
            dataWebSocketClient.onDepthEvent(text, reponse -> System.out.println(reponse));
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
        subscribeReq.setActionType(ActionTypeEnum.sub.name());
        subscribeReq.setExchange(ExchangeEnum.HUOBI.getValue());
        subscribeReq.setSymbol("ETH/BTC");
        subscribeReq.setDuration("5");
        List<SubscribeReq> subscribeReqList = Arrays.asList(subscribeReq);
        try {
            String text = mapper.writeValueAsString(subscribeReqList);
            dataWebSocketClient.onCandleEvent(text, response -> System.out.println(response));
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
        subscribeReq.setSymbol("HT/BTC");
        subscribeReq.setActionType(ActionTypeEnum.sub.name());
        subscribeReq.setExchange(ExchangeEnum.HUOBI.getValue());
        List<SubscribeReq> subscribeReqList = Arrays.asList(subscribeReq);
        try {
            String text = mapper.writeValueAsString(subscribeReqList);
            dataWebSocketClient.onTickEvent(text, response -> System.out.println(response));
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
        subscribeReq.setSymbol("ETH/BTC");
        subscribeReq.setActionType(ActionTypeEnum.sub.name());
        subscribeReq.setExchange(ExchangeEnum.OKEX.getValue());

        List<SubscribeReq> subscribeReqList = Arrays.asList(subscribeReq);
        try {
            String text = mapper.writeValueAsString(subscribeReqList);
            dataWebSocketClient.onTradeEvent(text, response -> System.out.println(response.toString()));
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }

    /**
     * 取消订阅trade
     */
    private static void unSubTrade() {
        SubscribeReq subscribeReq = new SubscribeReq();
        subscribeReq.setDataType("Trade");
        subscribeReq.setSymbol("ETH/BTC");
        subscribeReq.setActionType(ActionTypeEnum.unsub.name());
        subscribeReq.setExchange(ExchangeEnum.HUOBI.getValue());

        List<SubscribeReq> subscribeReqList = Arrays.asList(subscribeReq);
        try {
            String text = mapper.writeValueAsString(subscribeReqList);
            dataWebSocketClient.onTradeEvent(text, response -> System.out.println(response.toString()));
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
            dataWebSocketClient.onTradeEvent(text, response -> System.out.println(response.toString()));
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }

}