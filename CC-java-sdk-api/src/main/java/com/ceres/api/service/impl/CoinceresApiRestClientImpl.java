package com.ceres.api.service.impl;

import com.ceres.api.domain.trade.*;
import com.ceres.api.service.CoinceresApiRestClient;
import com.ceres.api.service.CoinceresApiService;

import java.util.List;
import java.util.Map;

import static com.ceres.api.service.CoinceresApiServiceGenerator.createService;
import static com.ceres.api.service.CoinceresApiServiceGenerator.executeSync;

public class CoinceresApiRestClientImpl implements CoinceresApiRestClient {

    private final CoinceresApiService coinceresApiService;

    public CoinceresApiRestClientImpl(String apiKey, String secret) {
        coinceresApiService = createService(CoinceresApiService.class, apiKey, secret);
    }

    @Override
    public ResultsVO<List<CurrencyPair>> getContractList(ContractReq req) {
        return executeSync(coinceresApiService.getContractList(req.getExchange(), req.getContract()));
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
    public ResultsVO<List<AccountInfoRes>> getAccountInfo(String exchange) {
        return executeSync(coinceresApiService.getAccountInfo(exchange));
    }

    @Override
    public ResultsVO<List<OrderDetailRes>> getOrderInfo(OrderDetailReq req) {
        return executeSync(coinceresApiService.getOrderInfo(req.getSystemOid(),req.getStatus(),req.getExchange(),req.getContract()));
    }

    @Override
    public ResultsVO<List<PositionDetailRes>> getPosition(PositionQueryReq req) {
        return executeSync(coinceresApiService.getPosition(req.getExchange(), req.getContract(), req.getPositionDir()));
    }

    @Override
    public ResultsVO<List<TransRecord>> queryTransRecord(TransReq req) {
        return executeSync(coinceresApiService.queryTransRecord(req.getExchange(), req.getContract(), req.getCount()));
    }
}
