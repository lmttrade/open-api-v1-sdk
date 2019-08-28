package com.ceres.api.service;

import com.ceres.api.domain.data.CycleData;
import com.ceres.api.domain.data.CycleReq;
import com.ceres.api.domain.data.DepthDataReq;
import com.ceres.api.domain.data.OpenBookData;
import com.ceres.api.domain.data.OpenResp;
import com.ceres.api.domain.data.TickData;
import com.ceres.api.domain.data.TickDataReq;
import com.ceres.api.domain.data.TradeData;
import com.ceres.api.domain.data.TradeDataReq;

import java.util.List;
/**
 * @author LMT
 * @date 2019/01/30
 */
public interface CoinceresDataRestClient {

    /**
     * 请求历史分钟线数据
     * @param req
     * @return
     */
    OpenResp<List<CycleData>> queryHistoryCycleData(CycleReq req);

    /**
     * 请求成交数据
     * @param req
     * @return
     */
    OpenResp<List<TradeData>> queryTradeData(TradeDataReq req);

    /**
     * 请求depth10数据
     * @param req
     * @return
     */
    OpenBookData queryBookData(DepthDataReq req);

    /**
     * 请求tick数据
     * @param req
     * @return
     */
    TickData queryTickData(TickDataReq req);

}
