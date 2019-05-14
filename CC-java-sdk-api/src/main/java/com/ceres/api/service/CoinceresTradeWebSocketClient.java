package com.ceres.api.service;

import com.ceres.api.domain.stream.EntrustNotice;

import java.io.Closeable;

public interface CoinceresTradeWebSocketClient {

    Closeable onOrderStreamEvent(CoinceresApiCallback<EntrustNotice> callback);
}
