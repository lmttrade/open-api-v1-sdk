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
import com.ceres.api.domain.trade.ExchangeVO;
import com.ceres.api.domain.trade.ResultsVO;
import com.ceres.api.domain.trade.SymbolReq;
import com.ceres.api.service.CoinceresApiWebSocketClient;
import com.ceres.api.service.CoinceresDataRestClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import util.PrettyPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.ceres.api.client.CoinceresApiClientFactory.MONITOR_MAP;
import static com.ceres.api.constant.Const.MONITOR_MARKET;
import static com.ceres.api.constant.Const.PRE_API_BASE_URL;
import static com.ceres.api.constant.Const.wsUrl;

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

    private static long lastPingCount = 0;

    static {
        mapper = new ObjectMapper();
        dataRestClient = CoinceresApiClientFactory.newInstance().newDataClient(PRE_API_BASE_URL);
        dataWebSocketClient = CoinceresApiClientFactory.newWebSocketClient(wsUrl);
    }


    public static void main(String[] args) {
        //Rest API
        // 01. 请求币对信息
//        querySymbols();

        // 02. 请求闪电交易币对报价信息
//        queryFlashIndicativePrice();

        // 03. 请求历史分钟线数据
//        queryCycleLine();

        //获取交易所
        //getExchange();

        /*
         * 注意：SDK请求服务端后，如果不同币对多次请求会认为是同一个客户端，数据会合并推送，解决方案：
         * 1、根据业务需求订阅之前，先取消订阅之前的币对，再订阅新的币对行情信息
         * 2、一直订阅，SDK根据需求筛选需要的币对信息，
         */
        //WebSocket API
        // 订阅 行情实时数据 包含 depth trade tick candle
        subMarketData();

        MONITOR_MAP.put(MONITOR_MARKET, 5L);

        ScheduledExecutorService reconnectService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("lmt-market-reconnect-scheduled-%d").daemon(true).build());
        reconnectService.scheduleAtFixedRate(()->{
            Long currentPingCount = MONITOR_MAP.getOrDefault(MONITOR_MARKET,0L);
            if (currentPingCount.longValue() == lastPingCount && lastPingCount != 0){
                dataWebSocketClient.closeWebSocket();
                // 订阅 行情实时数据 包含 depth trade tick candle
                subMarketData();
                lastPingCount = 0;
            }else {
                //取消订阅 注意 这里是为了测试 取消订阅这个功能
                unSubTrade();
                lastPingCount = currentPingCount.longValue();
            }
        },6,15,TimeUnit.SECONDS);
    }


    /**
     * 查询交易对详细信息
     */
    private static void querySymbols() {
        SymbolReq req = new SymbolReq();
        req.setExchange(ExchangeEnum.LMT.getValue());
        req.setSymbol("ETH_USDT");
        ResultsVO<List<CurrencyPair>> result = dataRestClient.getSymbols(req);
        PrettyPrinter.println(result);
    }

    /**
     * 请求闪电交易币对报价信息
     */
    private static void queryFlashIndicativePrice() {
        FlashIndicativePriceReq req = new FlashIndicativePriceReq();
        req.setExchange(ExchangeEnum.INSTANTEX.getValue());
        req.setSymbol("BTC_USDT");
        ResultsVO<FlashIndicativePriceData> result = dataRestClient.queryFlashIndicativePrice(req);
        PrettyPrinter.println(result);
    }

    /**
     * 请求历史分钟线数据
     */
    private static void queryCycleLine() {
        CycleReq req = new CycleReq();
        req.setExchange(ExchangeEnum.LMT.getValue());
        req.setSymbol("BTC_USDT");
        req.setBegin(1567683193L);
        req.setEnd(System.currentTimeMillis() / 1000);
        req.setDuration(1);
        ResultsVO<CycleResData> result = dataRestClient.queryHistoryCycleData(req);
        PrettyPrinter.println(result);
    }



    private static void subMarketData() {
        /**
         * 订阅depth
         */
        SubscribeReq subscribeAskBidQueueReq = new SubscribeReq();
        subscribeAskBidQueueReq.setDataType("AskBidQueue");
        subscribeAskBidQueueReq.setActionType(ActionTypeEnum.sub.name());
        subscribeAskBidQueueReq.setExchange(ExchangeEnum.BINANCE.getValue());
        subscribeAskBidQueueReq.setSymbol("BTC_USDT");

        /**
         * 订阅candle
         */
        SubscribeReq subscribeCycleReq = new SubscribeReq();
        subscribeCycleReq.setDataType("Cycle");
        subscribeCycleReq.setActionType(ActionTypeEnum.sub.name());
        subscribeCycleReq.setExchange(ExchangeEnum.BINANCE.getValue());
        subscribeCycleReq.setSymbol("BTC_USDT");
        subscribeCycleReq.setDuration("5");
        /**
         * 订阅tick
         */
        SubscribeReq subscribeTickReq = new SubscribeReq();
        subscribeTickReq.setDataType("Tick");
        subscribeTickReq.setSymbol("BTC_USDT");
        subscribeTickReq.setActionType(ActionTypeEnum.sub.name());
        subscribeTickReq.setExchange(ExchangeEnum.BINANCE.getValue());
        /**
         * 订阅trade
         */
        SubscribeReq subscribeTradeReq = new SubscribeReq();
        subscribeTradeReq.setDataType("Trade");
        subscribeTradeReq.setSymbol("BTC_USDT");
        subscribeTradeReq.setActionType(ActionTypeEnum.sub.name());
        subscribeTradeReq.setExchange(ExchangeEnum.BINANCE.getValue());

        List<SubscribeReq> subscribeReqList = new ArrayList<>();
        subscribeReqList.add(subscribeAskBidQueueReq);
        subscribeReqList.add(subscribeCycleReq);
        subscribeReqList.add(subscribeTickReq);
        subscribeReqList.add(subscribeTradeReq);
        try {
            String text = mapper.writeValueAsString(subscribeReqList);
            dataWebSocketClient.onMarketEvent(text, reponse -> System.out.println(reponse));
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
        subscribeReq.setSymbol("BTC_USDT");
        subscribeReq.setActionType(ActionTypeEnum.unsub.name());
        subscribeReq.setExchange(ExchangeEnum.BINANCE.getValue());

        List<SubscribeReq> subscribeReqList = Arrays.asList(subscribeReq);
        try {
            String text = mapper.writeValueAsString(subscribeReqList);
            dataWebSocketClient.onMarketEvent(text, response -> System.out.println(response.toString()));
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }

    /**
     * 获取交易所
     */
    private static void getExchange() {
        ResultsVO<ExchangeVO> exchange = dataRestClient.getExchange();
        PrettyPrinter.println(exchange);
    }
}
