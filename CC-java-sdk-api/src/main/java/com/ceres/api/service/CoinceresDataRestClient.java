package com.ceres.api.service;

import com.ceres.api.domain.data.*;
import com.ceres.api.domain.trade.ResultsVO;

import java.util.List;

public interface CoinceresDataRestClient {

    /**
     * 查询所有交易所及交易对
     */
    ResultsVO<List<MarketArea>> queryAllMarketArea();

    /**
     * 请求历史分钟线数据
     */
    OpenResp<List<CycleData>> queryHistoryCycleData(CycleReq req);

    /**
     * 请求成交数据
     */
    OpenResp<List<TradeData>> queryTradeData(TradeDataReq req);

    /**
     * 请求depth10数据
     */
    OpenBookData queryBookData(DepthDataReq req);

    /**
     * 请求tick数据
     */
    TickData queryTickData(TickDataReq req);

}
