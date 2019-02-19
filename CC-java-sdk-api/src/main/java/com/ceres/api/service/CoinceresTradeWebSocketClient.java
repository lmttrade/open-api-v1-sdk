package com.ceres.api.service;

import com.ceres.api.domain.stream.OrderNotice;

import java.io.Closeable;

public interface CoinceresTradeWebSocketClient {

    Closeable onOrderStreamEvent(CoinceresApiCallback<OrderNotice> callback);
}
