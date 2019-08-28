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
     * 获取交易对信息
     * @param exchange
     * @param symbol
     * @return
     */
    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @GET("/api/v1/basic/symbols")
    Call<ResultsVO<List<CurrencyPair>>> getSymbols(@Query("exchange") String exchange, @Query("symbol") String symbol);

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
     * @return
     */
    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @DELETE("/api/v1/trade/order/{systemOid}")
    Call<ResultsVO<Map<String, List<SystemOidRecord>>>> cancel(@Path("systemOid") String systemOid);

    /**
     * 账户余额信息
     * @param timestamp
     * @return
     */
    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @GET("/api/v1/trade/account")
    Call<ResultsVO<List<AccountInfoRes>>> getAccountInfo(@Query("timestamp") Long timestamp);

    /**
     * 查询订单详情
     * @param systemOid
     * @param timestamp
     * @return
     */
    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @GET("/api/v1/trade/order")
    Call<ResultsVO<List<OrderDetailRes>>> getOrderInfo(@Query("systemOid") String systemOid, @Query("timestamp") Long timestamp);

    /**
     * 查询合约持仓信息
     * @param symbol
     * @param positionDir
     * @return
     */
    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @GET("/api/v1/trade/position")
    Call<ResultsVO<List<PositionDetailRes>>> getPosition(@Query("symbol") String symbol, @Query("positionDir") String positionDir);

    /**
     * 查询成交记录
     * @param exchange
     * @param symbol
     * @param count
     * @return
     */
    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @GET("/api/v1/trade/trans")
    Call<ResultsVO<List<TransRecord>>> queryTransRecord(@Query("exchange") String exchange, @Query("symbol") String symbol,
                                                        @Query("count") String count);

    /**
     * 平仓
     * @param req
     * @return
     */
    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @POST("/api/v1/trade/close")
    Call<ResultsVO<InputOrderRes> > close(@Body CloseOrderReq req);
}
