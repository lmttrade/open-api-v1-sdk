package com.ceres.api.service;

import com.ceres.api.constant.Const;
import com.ceres.api.domain.trade.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;
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
    Call<ResultsVO<Map<String, List<SystemOidRecord>>>> cancel(@Path("system_oid") String systemOid,@Query("asset_code")Long assetCode);

    /**
     * 账户余额信息
     * @param timestamp
     * @param assetCode 账户编码
     * @return
     */
    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @GET("/api/v1/account/get")
    Call<ResultsVO<List<AccountInfoRes>>> getAccountInfo(@Query("timestamp") Long timestamp,@Query("asset_code") Long assetCode);

    /**
     * 查询订单详情
     * @param systemOid
     * @param assetCode
     * @param timestamp
     * @return
     */
    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @GET("/api/v1/trade/order")
    Call<ResultsVO<OrderDetailRes>> getOrderInfo(@Query("system_oid") String systemOid, @Query("asset_code") Long assetCode,@Query("timestamp") Long timestamp);

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
     * @param count
     * @param assetCode
     * @return
     */
    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @GET("/api/v1/trade/trans")
    Call<ResultsVO<List<TransRecord>>> queryTransRecord(@Query("exchange") String exchange, @Query("symbol") String symbol,
                                                        @Query("count") String count,@Query("asset_code") Long assetCode);

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
