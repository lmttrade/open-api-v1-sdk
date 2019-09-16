package com.ceres.api.service;

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
import com.ceres.api.domain.trade.PositionDetailRes;
import com.ceres.api.domain.trade.PositionQueryReq;
import com.ceres.api.domain.trade.ResultsVO;
import com.ceres.api.domain.trade.SystemOidRecord;
import com.ceres.api.domain.trade.TransRecord;
import com.ceres.api.domain.trade.TransReq;

import java.util.List;
import java.util.Map;

/**
 * @author LMT
 */
public interface CoinceresApiRestClient {
    /**
     * 列出账户列表
     * @return
     */
    ResultsVO<List<AccountBase>> getAccounts();

    /**
     * 添加子账户
     * @param req
     * @return
     */
    ResultsVO<AddSubAccountRes> addSubAccount(AddSubAccountReq req);

    /**
     * 子账户资金划转
     * @param req
     * @return
     */
    ResultsVO<List<AccountInfoRes>> accountTransfer(AccountTransferReq req);

    /**
     * 下单
     * @param req
     * @return 下单响应返回值
     */
    ResultsVO<InputOrderRes> input(InputOrderReq req);

    /**
     * 撤单
     * @param systemOid
     * @param assetCode
     * @return 撤单结果
     */
    ResultsVO<Map<String,List<SystemOidRecord>>> cancel(String systemOid,Long assetCode);

    /**
     * 查询账户信息
     * @param timestamp
     * @param assetCode
     * @return 账户信息
     */
    ResultsVO<List<AccountInfoRes>> getAccountInfo(Long timestamp,Long assetCode);

    /**
     * 查询订单信息
     * @param req
     * @return 订单详情
     */
    ResultsVO<OrderDetailRes> getOrderInfo(OrderDetailReq req);

    /**
     * 查询当前活动委托
     * @param req
     * @return
     */
    ResultsVO<List<OrderDetailRes>> getOpenOrders(OpenOrdersReq req);

    /**
     * 历史订单列表
     * @param req
     * @return
     */
    ResultsVO<List<OrderDetailRes>> getOrders(OrdersHisReq req);

    /**
     * 查询合约持仓
     * @param req
     * @return
     */
    ResultsVO<List<PositionDetailRes>> getPosition(PositionQueryReq req);

    /**
     * 查询成交
     * @param req
     * @return
     */
    ResultsVO<List<TransRecord>> queryTransRecord(TransReq req);

    /**
     * 平仓
     * @param req
     * @return
     */
    ResultsVO<InputOrderRes> close(CloseOrderReq req);

    /**
     * 闪电交易询价(预成交)
     * @param req
     * @return
     */
    ResultsVO<InstantTradingAskPriceRes> flashAskPrice(InstantTradingAskPriceReq req);

    /**
     * 闪电交易订单确认成交
     * @param req
     * @return
     */
    ResultsVO<InstantTradingConfirmRes> flashConfirm(InstantTradingConfirmReq req);
}
