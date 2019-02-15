package com.ceres.api.service;

import com.ceres.api.domain.trade.*;

import java.util.List;
import java.util.Map;

public interface CoinceresApiRestClient {

    /**
     * 获取交易对列表
     */
    ResultsVO<List<CurrencyPair>> getContractList(ContractReq req);

    /**
     * 下单
     */
    ResultsVO<InputOrderRes> input(InputOrderReq req);

    /**
     * 撤单
     */
    ResultsVO<Map<String,List<SystemOidRecord>>> cancel(String systemOid);

    /**
     * 查询账户信息
     */
    ResultsVO<List<AccountInfoRes>> getAccountInfo(String exchange);

    /**
     * 查询订单信息
     */
    ResultsVO<List<OrderDetailRes>> getOrderInfo(OrderDetailReq req);

    /**
     * 查询合约持仓
     */
    ResultsVO<List<PositionDetailRes>> getPosition(PositionQueryReq req);

    /**
     * 查询成交
     */
    ResultsVO<List<TransRecord>> queryTransRecord(TransReq req);
}
