package com.ceres.api.service.impl;

import com.ceres.api.domain.data.CycleData;
import com.ceres.api.domain.data.CycleReq;
import com.ceres.api.domain.data.DepthDataReq;
import com.ceres.api.domain.data.OpenBookData;
import com.ceres.api.domain.data.OpenResp;
import com.ceres.api.domain.data.TickData;
import com.ceres.api.domain.data.TickDataReq;
import com.ceres.api.domain.data.TradeData;
import com.ceres.api.domain.data.TradeDataReq;
import com.ceres.api.service.CoinceresDataRestClient;
import com.ceres.api.service.CoinceresDataService;

import java.util.List;

import static com.ceres.api.service.CoinceresDataServiceGenerator.createService;
import static com.ceres.api.service.CoinceresDataServiceGenerator.executeSync;
/**
 * @author LMT
 * @date 2019/01/30
 */
public class CoinceresDataRestClientImpl implements CoinceresDataRestClient {

    private final CoinceresDataService coinceresDataService;

    public CoinceresDataRestClientImpl() {
        this.coinceresDataService = createService(CoinceresDataService.class);
    }


    @Override
    public OpenResp<List<CycleData>> queryHistoryCycleData(CycleReq req) {
        return executeSync(coinceresDataService.queryHistoryCycleData(req.getSymbol(),
                req.getBegin(), req.getEnd(),
                req.getSize(), req.getDuration()));
    }

    @Override
    public OpenResp<List<TradeData>> queryTradeData(TradeDataReq req) {
        return executeSync(coinceresDataService.queryTradeData(req.getSymbol(),
                req.getBegin(), req.getEnd(),
                req.getSize()));
    }

    @Override
    public OpenBookData queryBookData(DepthDataReq req) {
        return executeSync(coinceresDataService.queryBookData(req.getExchange(), req.getSymbol()));
    }

    @Override
    public TickData queryTickData(TickDataReq req) {
        return executeSync(coinceresDataService.queryTickData(req.getExchange(), req.getSymbol()));
    }
}
