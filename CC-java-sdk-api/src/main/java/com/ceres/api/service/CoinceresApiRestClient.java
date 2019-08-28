package com.ceres.api.service;

import com.ceres.api.domain.trade.*;

import java.util.List;
import java.util.Map;

/**
 * @author LMT
 */
public interface CoinceresApiRestClient {

    /**
     * 下单
     * @param req
     * @return 下单响应返回值
     */
    ResultsVO<InputOrderRes> input(InputOrderReq req);

    /**
     * 撤单
     * @param systemOid
     * @return 撤单结果
     */
    ResultsVO<Map<String,List<SystemOidRecord>>> cancel(String systemOid);

    /**
     * 查询账户信息
     * @param timestamp
     * @return 账户信息
     */
    ResultsVO<List<AccountInfoRes>> getAccountInfo(Long timestamp);

    /**
     * 查询订单信息
     * @param req
     * @return 订单列表
     */
    ResultsVO<List<OrderDetailRes>> getOrderInfo(OrderDetailReq req);

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
}
