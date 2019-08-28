package com.ceres.api.service;

import com.ceres.api.domain.event.CandleEvent;
import com.ceres.api.domain.event.DepthEvent;
import com.ceres.api.domain.event.TickEvent;
import com.ceres.api.domain.event.TradeEvent;

import java.io.Closeable;

/**
 * @author LMT
 * @date 2019/01/30
 */
public interface CoinceresApiWebSocketClient {
    /**
     * OrderBook数据
     * @param text
     * @param callback
     * @return
     */
    Closeable onDepthEvent(String text, CoinceresApiCallback<DepthEvent> callback);

    /**
     * K线数据
     * @param text
     * @param callback
     * @return
     */
    Closeable onCandleEvent(String text, CoinceresApiCallback<CandleEvent> callback);

    /**
     * tick数据
     * @param text
     * @param callback
     * @return
     */
    Closeable onTickEvent(String text, CoinceresApiCallback<TickEvent> callback);

    /**
     * trade数据
     * @param text
     * @param callback
     * @return
     */
    Closeable onTradeEvent(String text, CoinceresApiCallback<TradeEvent> callback);
}
