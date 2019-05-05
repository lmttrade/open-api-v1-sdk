package com.ceres.api.example;

import com.ceres.api.client.CoinceresApiClientFactory;
import com.ceres.api.domain.data.*;
import com.ceres.api.domain.trade.ResultsVO;
import com.ceres.api.service.CoinceresDataRestClient;
import util.PrettyPrinter;

import java.util.List;

/**
 * 关于行情数据请求的示例
 *
 * @author xiaotian.huang
 * @date 2019/02/14
 */
public class DataTest {

    private static CoinceresDataRestClient dataRestClient;

    static {
        dataRestClient = CoinceresApiClientFactory.newInstance().newDataClient();
    }

    public static void main(String[] args) {

        // 01. 请求历史分钟线数据
        cycleLineData();

        // 02. 请求成交数据
        tradeData();

        // 03. 请求depth10数据
        depth10();

        // 04. 请求tick数据
        tickData();
    }

    /** tick数据 */
    private static void tickData() {
        TickDataReq req = new TickDataReq();
        req.setExchange("OKEX");
        req.setContract("BTC/USDT");
        TickData result = dataRestClient.queryTickData(req);
        PrettyPrinter.println(result);
    }

    /** depth10数据 */
    private static void depth10() {
        DepthDataReq req = new DepthDataReq();
        req.setExchange("OKEX");
        req.setContract("BTC/USDT");
        OpenBookData result = dataRestClient.queryBookData(req);
        PrettyPrinter.println(result);
    }

    /** 请求成交数据 */
    private static void tradeData() {
        TradeDataReq req = new TradeDataReq();
        req.setExchange("OKEX");
        req.setContract("BTC/USDT");
        req.setEnd(System.currentTimeMillis()+"");
        req.setSize(5);
        OpenResp<List<TradeData>> result = dataRestClient.queryTradeData(req);
        PrettyPrinter.println(result);
    }

    /** 请求历史分钟线数据 */
    private static void cycleLineData() {
        CycleReq req = new CycleReq();
        req.setExchange("OKEX");
        req.setContract("BTC/USDT");
        req.setEnd(System.currentTimeMillis() + "");
        System.out.println(req.getEnd());
        req.setSize(5);
        req.setDuration("1h");
        OpenResp<List<CycleData>> result = dataRestClient.queryHistoryCycleData(req);
        PrettyPrinter.println(result);
    }

}
