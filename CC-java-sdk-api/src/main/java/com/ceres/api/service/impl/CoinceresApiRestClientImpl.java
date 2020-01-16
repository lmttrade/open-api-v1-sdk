package com.ceres.api.service.impl;

import com.ceres.api.domain.trade.AccountBase;
import com.ceres.api.domain.trade.AccountBorrowInfoRes;
import com.ceres.api.domain.trade.AccountInfoRes;
import com.ceres.api.domain.trade.AccountTransferReq;
import com.ceres.api.domain.trade.AddSubAccountReq;
import com.ceres.api.domain.trade.AddSubAccountRes;
import com.ceres.api.domain.trade.CancelOrderRep;
import com.ceres.api.domain.trade.CloseOrderReq;
import com.ceres.api.domain.trade.InputOrderReq;
import com.ceres.api.domain.trade.InputOrderRes;
import com.ceres.api.domain.trade.InstantTradingAskPriceReq;
import com.ceres.api.domain.trade.InstantTradingAskPriceRes;
import com.ceres.api.domain.trade.InstantTradingConfirmReq;
import com.ceres.api.domain.trade.InstantTradingConfirmRes;
import com.ceres.api.domain.trade.OpenOrdersReq;
import com.ceres.api.domain.trade.OrderDetailReq;
import com.ceres.api.domain.trade.OrderDetailRes;
import com.ceres.api.domain.trade.OrdersHisReq;
import com.ceres.api.domain.trade.OrdersRes;
import com.ceres.api.domain.trade.PositionDetailRes;
import com.ceres.api.domain.trade.PositionQueryReq;
import com.ceres.api.domain.trade.ProgramOrdersHisReq;
import com.ceres.api.domain.trade.ResultsVO;
import com.ceres.api.domain.trade.TransRep;
import com.ceres.api.domain.trade.TransReq;
import com.ceres.api.service.CoinceresApiRestClient;
import com.ceres.api.service.CoinceresApiService;

import java.util.List;

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
    public ResultsVO<CancelOrderRep> cancel(String systemOid,Long assetCode) {
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
    public ResultsVO<TransRep> queryTransRecord(TransReq req) {
        return executeSync(coinceresApiService.queryTransRecord(req.getExchange(), req.getSymbol(), req.getSystemOid
                        (),req.getFrom(),req.getCount(), req.getAssetCode()));
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

    @Override
    public ResultsVO<List<AccountBorrowInfoRes>> getBorrowed(Long timestamp,Long assetCode) {
        return executeSync(coinceresApiService.getBorrowed(timestamp,assetCode));
    }
}
