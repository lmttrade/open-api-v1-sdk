package com.ceres.api.service;

import com.ceres.api.domain.data.*;
import com.ceres.api.domain.trade.ResultsVO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

public interface CoinceresDataService {

    @POST("/api/v1/query/all/market-area")
    Call<ResultsVO<List<MarketArea>>> queryAllMarketArea();

    @GET("/api/v1/market/candle")
    Call<OpenResp<List<CycleData>>> queryHistoryCycleData(@Query("exchange") String exchange, @Query("contract") String contract,
                                                          @Query("begin") String begin, @Query("end") String end,
                                                          @Query("size") Integer size, @Query("duration") String duration);

    @GET("/api/v1/market/trade")
    Call<OpenResp<List<TradeData>>> queryTradeData(@Query("exchange") String exchange, @Query("contract") String contract,
                                                   @Query("begin") String begin, @Query("end") String end,
                                                   @Query("size") Integer size);

    @GET("/api/v1/market/depth10")
    Call<OpenBookData> queryBookData(@Query("exchange") String exchange, @Query("contract") String contract);

    @GET("/api/v1/market/tick")
    Call<TickData> queryTickData(@Query("exchange") String exchange, @Query("contract") String contract);
}
