package com.ceres.api.service.impl;

import com.ceres.api.domain.data.*;
import com.ceres.api.domain.trade.ResultsVO;
import com.ceres.api.service.CoinceresDataRestClient;
import com.ceres.api.service.CoinceresDataService;

import java.util.List;

import static com.ceres.api.service.CoinceresDataServiceGenerator.createService;
import static com.ceres.api.service.CoinceresDataServiceGenerator.executeSync;

public class CoinceresDataRestClientImpl implements CoinceresDataRestClient {

    private final CoinceresDataService coinceresDataService;

    public CoinceresDataRestClientImpl() {
        this.coinceresDataService = createService(CoinceresDataService.class);
    }

    @Override
    public ResultsVO<List<MarketArea>> queryAllMarketArea() {
        return executeSync(coinceresDataService.queryAllMarketArea());
    }

    @Override
    public OpenResp<List<CycleData>> queryHistoryCycleData(CycleReq req) {
        return executeSync(coinceresDataService.queryHistoryCycleData(req.getExchange(), req.getContract(),
                req.getBegin(), req.getEnd(),
                req.getSize(), req.getDuration()));
    }

    @Override
    public OpenResp<List<TradeData>> queryTradeData(TradeDataReq req) {
        return executeSync(coinceresDataService.queryTradeData(req.getExchange(), req.getContract(),
                req.getBegin(), req.getEnd(),
                req.getSize()));
    }

    @Override
    public OpenBookData queryBookData(DepthDataReq req) {
        return executeSync(coinceresDataService.queryBookData(req.getExchange(), req.getContract()));
    }

    @Override
    public TickData queryTickData(TickDataReq req) {
        return executeSync(coinceresDataService.queryTickData(req.getExchange(), req.getContract()));
    }
}
