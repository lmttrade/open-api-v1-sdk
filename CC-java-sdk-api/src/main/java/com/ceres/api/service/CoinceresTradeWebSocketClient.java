package com.ceres.api.service;

import com.ceres.api.domain.stream.EntrustNotice;

import java.io.Closeable;
/**
 * @author LMT
 * @date 2019/01/30
 */
public interface CoinceresTradeWebSocketClient {
    /**
     * 交易订单通知
     * @param callback
     * @return
     */
    Closeable onOrderStreamEvent(CoinceresApiCallback<EntrustNotice> callback);

    /**
     * 重连 关闭之前的连接
     */
    void closeWebSocket();
}
