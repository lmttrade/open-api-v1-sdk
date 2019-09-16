package com.ceres.api.service.impl;

import com.ceres.api.domain.data.*;
import com.ceres.api.domain.trade.CurrencyPair;
import com.ceres.api.domain.trade.ResultsVO;
import com.ceres.api.domain.trade.SymbolReq;
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
    public ResultsVO<List<CurrencyPair>> getSymbols(SymbolReq req) {

        return executeSync(coinceresDataService.getSymbols(req.getExchange(), req.getSymbol()));
    }

    @Override
    public ResultsVO<FlashIndicativePriceData> queryFlashIndicativePrice(FlashIndicativePriceReq req) {
        return executeSync(coinceresDataService.queryFlashIndicativePrice(req.getExchange(), req.getSymbol()));
    }

    @Override
    public ResultsVO<CycleResData> queryHistoryCycleData(CycleReq req) {
        return executeSync(coinceresDataService.queryHistoryCycleData(req.getExchange(),req.getSymbol(),
                req.getBegin(), req.getEnd(),req.getDuration()));
    }
}
