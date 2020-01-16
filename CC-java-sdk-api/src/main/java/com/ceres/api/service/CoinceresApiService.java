package com.ceres.api.service;

import com.ceres.api.constant.Const;
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
import com.ceres.api.domain.trade.OrderDetailRes;
import com.ceres.api.domain.trade.OrdersHisReq;
import com.ceres.api.domain.trade.OrdersRes;
import com.ceres.api.domain.trade.PositionDetailRes;
import com.ceres.api.domain.trade.ProgramOrdersHisReq;
import com.ceres.api.domain.trade.ResultsVO;
import com.ceres.api.domain.trade.TransRep;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;
/**
 * @author LMT
 * @date 2019/01/30
 */
public interface CoinceresApiService {
    /**
     * 查询账户列表
     * @return
     */
    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @GET("/api/v1/account/list")
    Call<ResultsVO<List<AccountBase>>> getAccounts();

    /**
     * 添加子账户
     * @param req
     * @return
     */
    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @POST("/api/v1/account/add_sub_account")
    Call<ResultsVO<AddSubAccountRes>> addSubAccount(@Body AddSubAccountReq req);

    /**
     * 子账户资产划转
     * @param req
     * @return
     */
    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @POST("/api/v1/account/transfer")
    Call<ResultsVO<List<AccountInfoRes>>> accountTransfer(@Body AccountTransferReq req);

    /**
     * 查询借贷信息
     * @param assetCode
     * @return
     */
    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @GET("/api/v1/account/get_borrowed")
    Call<ResultsVO<List<AccountBorrowInfoRes>>> getBorrowed(@Query("asset_code") Long assetCode);

    /**
     * 下单
     * @param req
     * @return
     */
    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @POST("/api/v1/trade/input")
    Call<ResultsVO<InputOrderRes>> input(@Body InputOrderReq req);

    /**
     * 撤单
     * @param systemOid
     * @param assetCode
     * @return
     */
    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @DELETE("/api/v1/trade/order/{system_oid}")
    Call<ResultsVO<CancelOrderRep>> cancel(@Path("system_oid") String systemOid,@Query("asset_code")Long assetCode);

    /**
     * 账户余额信息
     * @param timestamp
     * @param assetCode 账户编码
     * @param symbol
     * @param side
     * @return
     */
    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @GET("/api/v1/account/get")
    Call<ResultsVO<AccountInfoRes>> getAccountInfo(@Query("timestamp") Long timestamp,@Query("asset_code") Long
            assetCode,@Query("symbol")String symbol,@Query("side")String side);

    /**
     * 查询订单详情
     * @param systemOid
     * @param clientOid
     * @param assetCode
     * @param timestamp
     * @return
     */
    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @GET("/api/v1/trade/order")
    Call<ResultsVO<OrderDetailRes>> getOrderInfo(@Query("system_oid") String systemOid, @Query("client_oid") String
            clientOid,@Query("asset_code") Long assetCode,@Query("timestamp") Long timestamp);

    /**
     * 查询当前活动委托
     * @param openOrdersReq
     * @return
     */
    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @POST("/api/v1/trade/open_orders")
    Call<ResultsVO<OrdersRes>> getOpenOrders(@Body OpenOrdersReq openOrdersReq);

    /**
     * 查询历史订单信息
     * @param ordersHisReq
     * @return
     */
    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @POST("/api/v1/trade/orders")
    Call<ResultsVO<OrdersRes>> getOrders(@Body OrdersHisReq ordersHisReq);

    /**
     * 查询程序单的子单列表
     * @param programOrdersHisReq
     * @return
     */
    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @POST("/api/v1/trade/program_orders")
    Call<ResultsVO<OrdersRes>> getProgramOrders(@Body ProgramOrdersHisReq programOrdersHisReq);

    /**
     * 查询合约持仓信息
     * @param symbol
     * @param positionDir
     * @param assetCode
     * @return
     */
    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @GET("/api/v1/trade/position")
    Call<ResultsVO<List<PositionDetailRes>>> getPosition(@Query("symbol") String symbol, @Query("position_dir")
            String positionDir,@Query("asset_code") Long assetCode);

    /**
     * 查询成交记录
     * @param exchange
     * @param symbol
     * @param systemOid
     * @param from
     * @param count
     * @param assetCode
     * @return
     */
    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @GET("/api/v1/trade/trans")
    Call<ResultsVO<TransRep>> queryTransRecord(@Query("exchange") String exchange, @Query("symbol") String symbol,
            @Query("system_oid") String systemOid,@Query("from") Long from, @Query("count") Integer count,@Query("asset_code") Long assetCode);

    /**
     * 平仓
     * @param req
     * @return
     */
    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @POST("/api/v1/trade/close")
    Call<ResultsVO<InputOrderRes> > close(@Body CloseOrderReq req);

    /**
     * 闪电交易询价(预成交)
     * @param req
     * @return
     */
    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @POST("/api/v1/trade/flash/ask_price")
    Call<ResultsVO<InstantTradingAskPriceRes> > flashAskPrice(@Body InstantTradingAskPriceReq req);

    /**
     * 闪电交易成交确认
     * @param req
     * @return
     */
    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @POST("/api/v1/trade/flash/confirm")
    Call<ResultsVO<InstantTradingConfirmRes> > flashConfirm(@Body InstantTradingConfirmReq req);
}
