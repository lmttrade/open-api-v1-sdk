package com.ceres.api.service.impl;

import com.ceres.api.domain.trade.*;
import com.ceres.api.service.CoinceresApiRestClient;
import com.ceres.api.service.CoinceresApiService;

import java.util.List;
import java.util.Map;

import static com.ceres.api.service.CoinceresApiServiceGenerator.createService;
import static com.ceres.api.service.CoinceresApiServiceGenerator.executeSync;

/**
 * @author LMT
 */
public class CoinceresApiRestClientImpl implements CoinceresApiRestClient {

    private final CoinceresApiService coinceresApiService;

    public CoinceresApiRestClientImpl(String apiKey, String secret) {
        coinceresApiService = createService(CoinceresApiService.class, apiKey, secret);
    }

    @Override
    public ResultsVO<List<AccountBase>> getAccounts() {
        return executeSync(coinceresApiService.getAccounts());
    }

    @Override
    public ResultsVO<AddSubAccountRes> addSubAccount(AddSubAccountReq req) {
        return executeSync(coinceresApiService.addSubAccount(req));
    }

    @Override
    public ResultsVO<List<AccountInfoRes>> accountTransfer(AccountTransferReq req) {
        return executeSync(coinceresApiService.accountTransfer(req));
    }

    @Override
    public ResultsVO<InputOrderRes> input(InputOrderReq req) {
        return executeSync(coinceresApiService.input(req));
    }

    @Override
    public ResultsVO<Map<String, List<SystemOidRecord>>> cancel(String systemOid) {
        return executeSync(coinceresApiService.cancel(systemOid));
    }

    @Override
    public ResultsVO<List<AccountInfoRes>> getAccountInfo(Long timestamp,Long assetCode) {
        return executeSync(coinceresApiService.getAccountInfo(timestamp,assetCode));
    }

    @Override
    public ResultsVO<List<OrderDetailRes>> getOrderInfo(OrderDetailReq req) {
        return executeSync(coinceresApiService.getOrderInfo(req.getSystemOid(),req.getTimestamp()));
    }

    @Override
    public ResultsVO<List<PositionDetailRes>> getPosition(PositionQueryReq req) {
        return executeSync(coinceresApiService.getPosition(req.getSymbol(), req.getPositionDir()));
    }

    @Override
    public ResultsVO<List<TransRecord>> queryTransRecord(TransReq req) {
        return executeSync(coinceresApiService.queryTransRecord(req.getExchange(), req.getSymbol(), req.getCount(),
                req.getAssetCode()));
    }

    @Override
    public ResultsVO<InputOrderRes> close(CloseOrderReq req) {
        return executeSync(coinceresApiService.close(req));
    }

    @Override
    public ResultsVO<InstantTradingAskPriceRes> flashAskPrice(InstantTradingAskPriceReq req) {
        return executeSync(coinceresApiService.flashAskPrice(req));
    }

    @Override
    public ResultsVO<InstantTradingConfirmRes> flashConfirm(InstantTradingConfirmReq req) {
        return executeSync(coinceresApiService.flashConfirm(req));
    }
}
