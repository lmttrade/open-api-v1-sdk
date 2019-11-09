package com.ceres.api.example;

import com.ceres.api.client.CoinceresApiClientFactory;
import com.ceres.api.constant.Const;
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
import com.ceres.api.domain.trade.OrdersHisReq;
import com.ceres.api.domain.trade.OrdersRes;
import com.ceres.api.domain.trade.PositionQueryReq;
import com.ceres.api.domain.trade.ProgramOrdersHisReq;
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
                "b7tKSQahoYzfcI7nwJ0qAgXXuArzTstl").newRestClient(Const.PRE_API_BASE_URL);
    }

    public static void main(String[] args) throws InterruptedException {
        //01. 账户列表
//        queryAccounts();
        //02. 添加子账户
//        addSubAccount();
        //03. 查询账户资产信息
//        queryAccountInfo();
        //04. 子账户资产划转
//        accountTransfer();
        //05. 下单
        placeOrder();
        //06. 查询订单信息
//        queryOrderInfo();
        //07. 活动委托
//        queryOpenOrders();
        //10. 撤单
//        cancelOrder();
        //12. 查询成交
//        queryTrade();
        //13.14.闪电交易询价+确认
//        String confirmId = instantTradingAskPriceByVol();
//        System.out.println(confirmId);
//        if (StringUtils.isNotEmpty(confirmId)) {
//            instantTradingConfirm(confirmId);
//        }
        //15. 根据母单号查询子单列表
//        getProgramOrders();
        //16. 查询历史订单数据(时间最大跨度一个月)
//        getOrders();
    }

    /** 平仓 */
    private static void closeOrder() {
        CloseOrderReq req = new CloseOrderReq();
        req.setClientOid("123");
        req.setCloseRule("admin");
        req.setSymbol("BTC_USDT");
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
        ResultsVO<OrdersRes> result = restClient.getOpenOrders(req);
        PrettyPrinter.println(result);
    }

    /** 查询成交 */
    private static void queryTrade() {
        TransReq req = new TransReq();
        req.setExchange("BINANCE");
        req.setSymbol("NEO_USDT");
        req.setCount("10");
        req.setAssetCode(190900062L);
        ResultsVO<List<TransRecord>> result = restClient.queryTransRecord(req);
        PrettyPrinter.println(result);
    }

    /** 查询合约持仓 */
    private static void queryHolding() {
        PositionQueryReq req = new PositionQueryReq();
        req.setExchange("LMT");
        req.setSymbol("BTC_USDT");
        ResultsVO result = restClient.getPosition(req);
        PrettyPrinter.println(result);
    }

    /** 查询订单信息 */
    private static void queryOrderInfo() {
        OrderDetailReq req = new OrderDetailReq();
        req.setSystemOid("1109950535628501010");
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
        ResultsVO<List<AccountInfoRes>> result = restClient.getAccountInfo(System.currentTimeMillis(),190908122L);
        PrettyPrinter.println(result);
    }

    /** 撤单 */
    private static void cancelOrder() {
        ResultsVO<Map<String, List<SystemOidRecord>>> result = restClient.cancel("1128133132787396617",190908122L);
        PrettyPrinter.println(result);
    }

    /** 下单 */
    private static void placeOrder() {
        InputOrderReq req = new InputOrderReq();
        req.setSymbol("ETH_USDT");
        req.setPriceType("limit");
        req.setEntrustPrice("175");
        req.setEntrustAmount("1");
        req.setEntrustBs("buy");
        req.setClientOid(UUID.randomUUID().toString());
        req.setTradeType("spot");
        req.setProgramOid("AT127366888882712580");
        req.setAssetCode(190908122L);
        req.setTimestamp(System.currentTimeMillis());
        ResultsVO<InputOrderRes> result = restClient.input(req);
        PrettyPrinter.println(result);
    }

    /** 闪电交易询价-按量 */
    private static String instantTradingAskPriceByVol() {
        InstantTradingAskPriceReq instantTradingAskPriceReq = new InstantTradingAskPriceReq();
        instantTradingAskPriceReq.setEntrustAmount("20");
        instantTradingAskPriceReq.setEntrustBs("buy");
        instantTradingAskPriceReq.setEntrustType(2);
        instantTradingAskPriceReq.setSymbol("BTC_USDT");
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
    /** 查询子单 */
    private static void getProgramOrders(){
        ProgramOrdersHisReq programOrdersHisReq = new ProgramOrdersHisReq();
        programOrdersHisReq.setProgramId("AT127366888882712580");
        programOrdersHisReq.setFrom("1127377016134578175");
        ResultsVO<OrdersRes> result = restClient.getProgramOrders(programOrdersHisReq);
        PrettyPrinter.println(result);
    }
    /** 历史订单 */
    private static void getOrders(){
        OrdersHisReq ordersHisReq = new OrdersHisReq();
        ordersHisReq.setAssetCode(190908122L);
        ordersHisReq.setExchange("INSTANTEX");
        ordersHisReq.setSymbol("BTC_USDT");
        ResultsVO<OrdersRes> result = restClient.getOrders(ordersHisReq);
        PrettyPrinter.println(result);
    }
}
