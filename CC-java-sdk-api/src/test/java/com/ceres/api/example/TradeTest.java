package com.ceres.api.example;

import com.ceres.api.client.CoinceresApiClientFactory;
import com.ceres.api.domain.trade.*;
import com.ceres.api.service.CoinceresApiRestClient;
import util.PrettyPrinter;

import java.util.List;
import java.util.Map;

/**
 * 关于交易的相关接口
 *
 * @author xiaotian.huang
 * @date 2019/02/11
 */
public class TradeTest {

    private static CoinceresApiRestClient restClient;

    static {
        restClient = CoinceresApiClientFactory.newInstance("bVHOwaYzkmtfSUXr",
                "b7tKSQahoYzfcI7nwJ0qAgXXuArzTstl").newRestClient();
    }

    public static void main(String[] args) throws InterruptedException {
        // 01. 查询交易对
//        queryContractList();

        // 02. 下单
        placeOrder();

        // 03. 撤单(path参数签名处理，进行了临时处理 todo）
//        cancelOrder();

        // 04. 查询账户信息
//        queryAccountInfo();

        // 05. 查询订单信息
//        queryOrderInfo();

        // 06. 查询合约持仓
//        queryHolding();

        // 07. 查询成交
//        queryTrade();

        // 8.平仓
//        closeOrder();

    }

    /** 平仓 */
    private static void closeOrder() {
        CloseOrderReq req = new CloseOrderReq();
        req.setClientOid("123");
        req.setCloseRule("admin");
        req.setSymbol("BTC/USDT");
        req.setDealId("123456789");
        req.setEntrustBs("buy");
        req.setEntrustPrice("1212");
        req.setEntrustVol("10");
        req.setPriceType("limit");
        ResultsVO<InputOrderRes> result = restClient.close(req);
        PrettyPrinter.println(result);
    }

    /** 查询成交 */
    private static void queryTrade() {
        TransReq req = new TransReq();
        req.setExchange("OKEX");
        req.setSymbol("BTC/USDT");
        req.setCount("10");
        ResultsVO<List<TransRecord>> result = restClient.queryTransRecord(req);
        PrettyPrinter.println(result);
    }

    /** 查询合约持仓 */
    private static void queryHolding() {
        PositionQueryReq req = new PositionQueryReq();
        req.setExchange("OKEX");
        req.setSymbol("BTC/USDT");
        ResultsVO result = restClient.getPosition(req);
        PrettyPrinter.println(result);
    }

    /** 查询订单信息 */
    private static void queryOrderInfo() {
        OrderDetailReq req = new OrderDetailReq();
        req.setSystemOid("123456789");
        req.setTimestamp(System.currentTimeMillis());
        ResultsVO<List<OrderDetailRes>> result = restClient.getOrderInfo(req);
        PrettyPrinter.println(result);
    }

    /** 查询账户信息 */
    private static void queryAccountInfo() {
        ResultsVO<List<AccountInfoRes>> result = restClient.getAccountInfo(System.currentTimeMillis());
        PrettyPrinter.println(result);
    }

    /** 撤单 */
    private static void cancelOrder() {
        ResultsVO<Map<String, List<SystemOidRecord>>> result = restClient.cancel("149150845501059075");
        PrettyPrinter.println(result);
    }

    /** 下单 */
    private static void placeOrder() {
        InputOrderReq req = new InputOrderReq();
        req.setSymbol("LTC/BTC");
        req.setPriceType("limit");
        req.setEntrustPrice("0.01");
        req.setProfitValue("100");
        req.setEntrustAmount("1");
        req.setEntrustBs("buy");
//        req.setFutureDir("open");
//        req.setLever("10");
        req.setClientOid("12345");
        req.setTradeType("spot");
//        req.setMarginMode("fixed");
        ResultsVO<InputOrderRes> result = restClient.input(req);
        PrettyPrinter.println(result);
    }
}
