package com.ceres.api.example;

import com.alibaba.fastjson.JSON;
import com.ceres.api.client.CoinceresApiClientFactory;
import com.ceres.api.constant.Const;
import com.ceres.api.domain.enums.ActionTypeEnum;
import com.ceres.api.domain.event.DepthEvent;
import com.ceres.api.domain.event.ProcessEvent;
import com.ceres.api.domain.event.TickEvent;
import com.ceres.api.domain.event.TradeEvent;
import com.ceres.api.domain.market.SubscribeReq;
import com.ceres.api.service.CoinceresApiWebSocketClient;
import com.ceres.api.service.CoinceresDataRestClient;
import com.ceres.api.service.CoinceresWebsocketCallback;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author: wulei
 * @date: 2019/12/16
 * @description:
 */
public class Market2Test {

    private static final Logger logger = LoggerFactory.getLogger(Market2Test.class);


    private CoinceresApiWebSocketClient ccClient;
    private CoinceresDataRestClient dataRestClient;
    private ObjectMapper mapper = new ObjectMapper();
    private int maxLevel = 5;
    private ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
            new BasicThreadFactory.Builder().namingPattern("LMTManager-RestPolling-%d").daemon(true).build());

    private Map<String, String> symbolExchangeMap = new ConcurrentHashMap<>();
    private AtomicBoolean needConnect = new AtomicBoolean(true);
    private String defaultExchange = null;

    int testCount = 1;

    private static ObjectMapper buildObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    public void init() throws Exception {
        this.ccClient = CoinceresApiClientFactory.newInstance().newWebSocketClient(Const.wsUrl);
        dataRestClient = CoinceresApiClientFactory.newInstance().newDataClient(Const.API_BASE_URL);
        // 交易所币对
        this.defaultExchange = "BINANCE";

        symbolExchangeMap.put("BTC_USDT", this.defaultExchange);
    }

    public void start() {
        try {
            setNeedConnect(false);
            subAll();
        } catch (Exception e) {
            setNeedConnect(true);
        }

        executorService.scheduleAtFixedRate(() -> {

            try {
                logger.info("scheduleAtFixedRate");

                if ("BINANCE".equals(this.defaultExchange) == false) {
                    logger.info("Default exchange switch {}->{}", this.defaultExchange, "BINANCE");
                    this.defaultExchange = "BINANCE";

                    ccClient.closeWebSocket();
                    setNeedConnect(true);
                }
                if (needConnect.get() == true) {
                    logger.error("进入重连" );
                    onDepthConnectFailure();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 5, 30, TimeUnit.SECONDS);
    }

    private void subAll() throws Exception {
        List<Pair<String, String>> list = new ArrayList<>();
        this.symbolExchangeMap.entrySet().forEach((entry) -> {
            logger.info("subAll {}.{}", entry.getKey(), entry.getValue());
            list.add(new Pair(entry.getKey(), entry.getValue()));
        });
        subMarketData(list);
    }

    private void subMarketData(List<Pair<String, String>> list) throws Exception {
        List<SubscribeReq> subscribeReqList = new ArrayList<>();
        for (Pair<String, String> pair : list) {
            SubscribeReq subscribeReq = new SubscribeReq();
            subscribeReq.setDataType("AskBidQueue");
            subscribeReq.setActionType(ActionTypeEnum.sub.name());
            subscribeReq.setSymbol(pair.getKey());
            subscribeReq.setExchange(pair.getValue());
            subscribeReqList.add(subscribeReq);
        }

        for (Pair<String, String> pair : list) {
            SubscribeReq subscribeReq = new SubscribeReq();
            subscribeReq.setDataType("Trade");
            subscribeReq.setActionType(ActionTypeEnum.sub.name());
            subscribeReq.setSymbol(pair.getKey());
            subscribeReq.setExchange(pair.getValue());
            subscribeReqList.add(subscribeReq);
        }

        for (Pair<String, String> pair : list) {
            SubscribeReq subscribeReq = new SubscribeReq();
            subscribeReq.setDataType("Tick");
            subscribeReq.setActionType(ActionTypeEnum.sub.name());
            subscribeReq.setSymbol(pair.getKey());
            subscribeReq.setExchange(pair.getValue());
            subscribeReqList.add(subscribeReq);
        }
        String text = mapper.writeValueAsString(subscribeReqList);

        ccClient.onMarketEvent(text, new CoinceresWebsocketCallback() {
            @Override
            public void onResponse(ProcessEvent processEvent) {
                if (processEvent instanceof DepthEvent){
                    DepthEvent depthEvent = (DepthEvent)processEvent;
                    System.out.println(JSON.toJSONString(depthEvent));
                }else if (processEvent instanceof TradeEvent){
                    TradeEvent depthEvent = (TradeEvent)processEvent;
                    System.out.println(JSON.toJSONString(depthEvent));
                }else if (processEvent instanceof TickEvent){
                    TickEvent depthEvent = (TickEvent)processEvent;
                    System.out.println(JSON.toJSONString(depthEvent));
                }

                testCount++;
                if (testCount%200 == 0){
                    logger.error("当前hashcode:{}", ccClient.hashCode());
                    logger.error("测试重连");
                    setNeedConnect(true);
                }
            }

            @Override
            public void onFailure(Throwable cause) {
                logger.error("onDepthEvent.onFailure", cause);
                setNeedConnect(true);
            }
        });
    }

    private void onDepthConnectFailure() {
        ccClient.closeWebSocket();
        try {
            logger.error("onDepthConnectFailure1");
            subAll();
        } catch (Exception e) {
            setNeedConnect(true);
            logger.error("onDepthConnectFailure2", e);
        }
    }

    public static String convertLMTSymbol(String symbol) {
        return symbol.replaceAll("_", "/");
    }

    public static String getQuoteCurrency(String symbol) {

        String quoteCurrency = null;
        if (symbol.indexOf("_") > -1) {
            quoteCurrency = symbol.split("_")[1];
        }
        if (symbol.indexOf("/") > -1) {
            quoteCurrency = symbol.split("_")[1];
        }
        return quoteCurrency;
    }

    public static String getBaseCurrency(String symbol) {

        String quoteCurrency = null;
        if (symbol.indexOf("_") > -1) {
            quoteCurrency = symbol.split("_")[0];
        }
        if (symbol.indexOf("/") > -1) {
            quoteCurrency = symbol.split("_")[0];
        }
        return quoteCurrency;
    }

    public void setNeedConnect(boolean connect) {
        this.needConnect.set(connect);
        logger.info("setNeedConnect {}", connect);
    }

    public static void main(String[] args) throws Exception{
        Market2Test market2Test = new Market2Test();
        market2Test.init();
        market2Test.start();
    }
}
