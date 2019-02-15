package com.ceres.api.service;

import com.ceres.api.domain.event.CandleEvent;
import com.ceres.api.domain.event.DepthEvent;
import com.ceres.api.domain.event.TickEvent;
import com.ceres.api.domain.event.TradeEvent;

import java.io.Closeable;

/**
 * @author xiaotian.huang
 * @date 2019/01/30
 */
public interface CoinceresApiWebSocketClient {

    Closeable onDepthEvent(String text, CoinceresApiCallback<DepthEvent> callback);

    Closeable onCandleEvent(String text, CoinceresApiCallback<CandleEvent> callback);

    Closeable onTickEvent(String text, CoinceresApiCallback<TickEvent> callback);

    Closeable onTradeEvent(String text, CoinceresApiCallback<TradeEvent> callback);
}
