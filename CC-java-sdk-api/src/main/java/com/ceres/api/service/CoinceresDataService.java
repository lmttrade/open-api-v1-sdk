package com.ceres.api.service;

import com.ceres.api.domain.data.CycleData;
import com.ceres.api.domain.data.OpenBookData;
import com.ceres.api.domain.data.OpenResp;
import com.ceres.api.domain.data.TickData;
import com.ceres.api.domain.data.TradeData;
import com.ceres.api.domain.trade.CurrencyPair;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;
/**
 * @author LMT
 * @date 2019/01/30
 */
public interface CoinceresDataService {

    /**
     * 获取交易对信息
     * @param exchange
     * @param symbol
     * @return
     */
    @GET("/api/v1/basic/symbols")
    Call<List<CurrencyPair>> getSymbols(@Query("exchange") String exchange, @Query("symbol") String symbol);

    /**
     * K线数据
     * @param symbol
     * @param begin
     * @param end
     * @param size
     * @param duration
     * @return
     */
    @GET("/api/v1/market/candle")
    Call<OpenResp<List<CycleData>>> queryHistoryCycleData(@Query("symbol") String symbol,
                                                          @Query("begin") String begin, @Query("end") String end,
                                                          @Query("size") Integer size, @Query("duration") String duration);

    /**
     * trade数据
     * @param symbol
     * @param begin
     * @param end
     * @param size
     * @return
     */
    @GET("/api/v1/market/trade")
    Call<OpenResp<List<TradeData>>> queryTradeData(@Query("symbol") String symbol,
                                                   @Query("begin") String begin, @Query("end") String end,
                                                   @Query("size") Integer size);

    /**
     * orderBook数据
     * @param exchange
     * @param symbol
     * @return
     */
    @GET("/api/v1/market/depth10")
    Call<OpenBookData> queryBookData(@Query("exchange") String exchange, @Query("symbol") String symbol);

    /**
     * Tick数据
     * @param exchange
     * @param symbol
     * @return
     */
    @GET("/api/v1/market/tick")
    Call<TickData> queryTickData(@Query("exchange") String exchange, @Query("symbol") String symbol);
}
