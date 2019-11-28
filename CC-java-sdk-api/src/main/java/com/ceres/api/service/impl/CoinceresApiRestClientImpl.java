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

    public CoinceresApiRestClientImpl(String endPoint,String apiKey, String secret) {
        coinceresApiService = createService(endPoint,CoinceresApiService.class, apiKey, secret);
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
    public ResultsVO accountTransfer(AccountTransferReq req) {
        return executeSync(coinceresApiService.accountTransfer(req));
    }

    @Override
    public ResultsVO<InputOrderRes> input(InputOrderReq req) {
        return executeSync(coinceresApiService.input(req));
    }

    @Override
    public ResultsVO<Map<String, List<SystemOidRecord>>> cancel(String systemOid,Long assetCode) {
        return executeSync(coinceresApiService.cancel(systemOid,assetCode));
    }

    @Override
    public ResultsVO<AccountInfoRes> getAccountInfo(Long timestamp,Long assetCode,String symbol,String side) {
        return executeSync(coinceresApiService.getAccountInfo(timestamp,assetCode,symbol,side));
    }

    @Override
    public ResultsVO<OrderDetailRes> getOrderInfo(OrderDetailReq req) {
        return executeSync(coinceresApiService.getOrderInfo(req.getSystemOid(),req.getClientOid(),req.getAssetCode(),req.getTimestamp()));
    }

    @Override
    public ResultsVO<OrdersRes> getOpenOrders(OpenOrdersReq req) {
        return executeSync(coinceresApiService.getOpenOrders(req));
    }

    @Override
    public ResultsVO<OrdersRes> getOrders(OrdersHisReq req) {
        return executeSync(coinceresApiService.getOrders(req));
    }

    @Override
    public ResultsVO<OrdersRes> getProgramOrders(ProgramOrdersHisReq req) {
        return executeSync(coinceresApiService.getProgramOrders(req));
    }

    @Override
    public ResultsVO<List<PositionDetailRes>> getPosition(PositionQueryReq req) {
        return executeSync(coinceresApiService.getPosition(req.getSymbol(), req.getPositionDir(),req.getAssetCode()));
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
