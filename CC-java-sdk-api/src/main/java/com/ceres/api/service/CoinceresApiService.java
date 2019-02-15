package com.ceres.api.service;

import com.ceres.api.constant.Const;
import com.ceres.api.domain.trade.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

public interface CoinceresApiService {

    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @GET("/api/v1/basic/contracts")
    Call<ResultsVO<List<CurrencyPair>>> getContractList(@Query("exchange") String exchange, @Query("contract") String contract);

    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @POST("/api/v1/trade/input")
    Call<ResultsVO<InputOrderRes>> input(@Body InputOrderReq req);

    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @DELETE("/api/v1/trade/order/{systemOid}")
    Call<ResultsVO<Map<String, List<SystemOidRecord>>>> cancel(@Path("systemOid") String systemOid);

    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @GET("/api/v1/trade/account")
    Call<ResultsVO<List<AccountInfoRes>>> getAccountInfo(@Query("exchange") String exchange);

    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @GET("/api/v1/trade/order")
    Call<ResultsVO<List<OrderDetailRes>>> getOrderInfo(@Query("systemOid") String systemOid, @Query("status") Integer status,
                                                       @Query("exchange") String exchange, @Query("contract") String contract);

    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @GET("/api/v1/trade/position")
    Call<ResultsVO<List<PositionDetailRes>>> getPosition(@Query("exchange") String exchange, @Query("contract") String contract,
                                                         @Query("positionDir") String positionDir);

    @Headers({Const.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER, Const.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER})
    @GET("/api/v1/trade/trans")
    Call<ResultsVO<List<TransRecord>>> queryTransRecord(@Query("exchange") String exchange, @Query("contract") String contract,
                                                        @Query("count") String count);
}
