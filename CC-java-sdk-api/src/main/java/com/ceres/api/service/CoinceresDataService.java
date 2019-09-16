package com.ceres.api.service;

import com.ceres.api.domain.data.*;
import com.ceres.api.domain.trade.CurrencyPair;
import com.ceres.api.domain.trade.ResultsVO;
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
     *
     * @param exchange 交易所信息
     * @param symbol   交易对信息
     * @return
     */
    @GET("/api/v1/basic/symbols")
    Call<ResultsVO<List<CurrencyPair>>> getSymbols(@Query("exchange") String exchange,
                                                   @Query("symbol") String symbol);

    /**
     * 请求闪电交易币对报价信息
     *
     * @param exchange 交易所信息
     * @param symbol   交易对信息
     * @return 交易对报价信息
     */
    @GET("/api/v1/market/flash/indicative_price")
    Call<ResultsVO<FlashIndicativePriceData>> queryFlashIndicativePrice(@Query("exchange") String exchange,
                                                                        @Query("symbol") String symbol);

    /**
     * K线数据
     *
     * @param exchange 交易所信息
     * @param symbol   交易对信息
     * @param begin    开始时间
     * @param end      结束时间
     * @param duration K线类型，详情见文档
     * @return K线数据包体
     */
    @GET("/api/v1/market/candle")
    Call<ResultsVO<CycleResData>> queryHistoryCycleData(@Query("exchange") String exchange,
                                                        @Query("symbol") String symbol,
                                                        @Query("begin") Long begin, @Query("end") Long end,
                                                        @Query("duration") Integer duration);

}
