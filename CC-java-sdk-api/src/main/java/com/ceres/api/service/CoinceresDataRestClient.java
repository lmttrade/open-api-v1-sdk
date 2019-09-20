package com.ceres.api.service;

import com.ceres.api.domain.data.*;
import com.ceres.api.domain.trade.CurrencyPair;
import com.ceres.api.domain.trade.ExchangeVO;
import com.ceres.api.domain.trade.ResultsVO;
import com.ceres.api.domain.trade.SymbolReq;

import java.util.List;
/**
 * @author LMT
 * @date 2019/01/30
 */
public interface CoinceresDataRestClient {

    /**
     * 获取交易对列表
     * @param req
     * @return 币对列表
     */
    ResultsVO<List<CurrencyPair>> getSymbols(SymbolReq req);

    /**
     * 请求历史分钟线数据
     * @param req
     * @return
     */
    ResultsVO<FlashIndicativePriceData> queryFlashIndicativePrice(FlashIndicativePriceReq req);

    /**
     * 请求历史分钟线数据
     * @param req
     * @return
     */
    ResultsVO<CycleResData> queryHistoryCycleData(CycleReq req);

    /**
     * 获取交易所
     *
     * @return
     */
    ResultsVO<ExchangeVO> getExchange();

}
