package com.ceres.api.example;

import com.ceres.api.client.CoinceresApiClientFactory;
import com.ceres.api.domain.trade.AccountBase;
import com.ceres.api.domain.trade.AccountInfoRes;
import com.ceres.api.domain.trade.AccountTransferReq;
import com.ceres.api.domain.trade.AddSubAccountReq;
import com.ceres.api.domain.trade.AddSubAccountRes;
import com.ceres.api.domain.trade.CloseOrderReq;
import com.ceres.api.domain.trade.InputOrderReq;
import com.ceres.api.domain.trade.InputOrderRes;
import com.ceres.api.domain.trade.InstantTradingAskPriceReq;
import com.ceres.api.domain.trade.InstantTradingAskPriceRes;
import com.ceres.api.domain.trade.InstantTradingConfirmReq;
import com.ceres.api.domain.trade.InstantTradingConfirmRes;
import com.ceres.api.domain.trade.OpenOrdersReq;
import com.ceres.api.domain.trade.OrderDetailReq;
import com.ceres.api.domain.trade.OrderDetailRes;
import com.ceres.api.domain.trade.PositionQueryReq;
import com.ceres.api.domain.trade.ResultsVO;
import com.ceres.api.domain.trade.SystemOidRecord;
import com.ceres.api.domain.trade.TransRecord;
import com.ceres.api.domain.trade.TransReq;
import com.ceres.api.service.CoinceresApiRestClient;
import util.PrettyPrinter;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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
        // 02. 下单
//        placeOrder();

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

        // 9.闪电交易询价
//        String confirmId = instantTradingAskPriceByVol();
//        if (StringUtils.isNotEmpty(confirmId)) {
//            instantTradingConfirm(confirmId);
//        }

        //10 活动委托
        queryOpenOrders();
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

    /** 查询活动委托 */
    private static void queryOpenOrders() {
        OpenOrdersReq req = new OpenOrdersReq();
        req.setTimestamp(System.currentTimeMillis());
        ResultsVO<List<OrderDetailRes>> result = restClient.getOpenOrders(req);
        PrettyPrinter.println(result);
    }

    /** 查询成交 */
    private static void queryTrade() {
        TransReq req = new TransReq();
        req.setExchange("OKEX");
        req.setSymbol("EOS/USDT");
        req.setCount("10");
        ResultsVO<List<TransRecord>> result = restClient.queryTransRecord(req);
        PrettyPrinter.println(result);
    }

    /** 查询合约持仓 */
    private static void queryHolding() {
        PositionQueryReq req = new PositionQueryReq();
        req.setExchange("LMT");
        req.setSymbol("BTC/USDT");
        ResultsVO result = restClient.getPosition(req);
        PrettyPrinter.println(result);
    }

    /** 查询订单信息 */
    private static void queryOrderInfo() {
        OrderDetailReq req = new OrderDetailReq();
        req.setSystemOid("199963652592906273");
        req.setTimestamp(System.currentTimeMillis());
        ResultsVO<OrderDetailRes> result = restClient.getOrderInfo(req);
        PrettyPrinter.println(result);
    }

    /** 查询账户列表信息 */
    private static void queryAccounts() {
        ResultsVO<List<AccountBase>> result = restClient.getAccounts();
        PrettyPrinter.println(result);
    }

    /** 添加子账户 */
    private static void addSubAccount() {
        AddSubAccountReq addSubAccountReq = new AddSubAccountReq();
        addSubAccountReq.setTimestamp(System.currentTimeMillis());
        ResultsVO<AddSubAccountRes> result = restClient.addSubAccount(addSubAccountReq);
        PrettyPrinter.println(result);
    }

    /** 子账户划账 */
    private static void accountTransfer() {
        AccountTransferReq accountTransferReq = new AccountTransferReq();
        accountTransferReq.setAssetCode(123456789L);
        accountTransferReq.setBalance("0.123");
        accountTransferReq.setCurrency("BTC");
        accountTransferReq.setTimestamp(System.currentTimeMillis());
        accountTransferReq.setTransferType("IN");
        ResultsVO<List<AccountInfoRes>> result = restClient.accountTransfer(accountTransferReq);
        PrettyPrinter.println(result);
    }

    /** 查询账户信息 */
    private static void queryAccountInfo() {
        ResultsVO<List<AccountInfoRes>> result = restClient.getAccountInfo(System.currentTimeMillis(),null);
        PrettyPrinter.println(result);
    }

    /** 撤单 */
    private static void cancelOrder() {
        ResultsVO<Map<String, List<SystemOidRecord>>> result = restClient.cancel("1124890656693825539",0L);
        PrettyPrinter.println(result);
    }

    /** 下单 */
    private static void placeOrder() {
        InputOrderReq req = new InputOrderReq();
        req.setSymbol("ETH/USDT");
        req.setPriceType("limit");
        req.setEntrustPrice("200");
        req.setEntrustAmount("0.05");
        req.setEntrustBs("sell");
        req.setClientOid("12345");
        req.setTradeType("spot");
        ResultsVO<InputOrderRes> result = restClient.input(req);
        PrettyPrinter.println(result);
    }

    /** 闪电交易询价-按量 */
    private static String instantTradingAskPriceByVol() {
        InstantTradingAskPriceReq instantTradingAskPriceReq = new InstantTradingAskPriceReq();
        instantTradingAskPriceReq.setEntrustAmount("20");
        instantTradingAskPriceReq.setEntrustBs("buy");
        instantTradingAskPriceReq.setEntrustType(2);
        instantTradingAskPriceReq.setSymbol("BTC/USDT");
        ResultsVO<InstantTradingAskPriceRes> result = restClient.flashAskPrice(instantTradingAskPriceReq);
        PrettyPrinter.println(result);
        if (result.getCode().equals("200")){
            return result.getData().getConfirmId();
        }
        return null;
    }

    /** 闪电交易确认成交 */
    private static void instantTradingConfirm(String confirmId) {
        InstantTradingConfirmReq instantTradingConfirmReq = new InstantTradingConfirmReq();
        instantTradingConfirmReq.setClientOid(UUID.randomUUID().toString());
        instantTradingConfirmReq.setConfirmId(confirmId);
        ResultsVO<InstantTradingConfirmRes> result = restClient.flashConfirm(instantTradingConfirmReq);
        PrettyPrinter.println(result);
    }
}
