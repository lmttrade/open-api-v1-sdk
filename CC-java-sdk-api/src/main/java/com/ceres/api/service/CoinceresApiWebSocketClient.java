package com.ceres.api.service;

import java.io.Closeable;

/**
 * @author LMT
 * @date 2019/01/30
 */
public interface CoinceresApiWebSocketClient {
    /**
     * 行情数据
     * @param text
     * @param callback
     * @return
     */
    Closeable onMarketEvent(String text, CoinceresWebsocketCallback callback);

    /**
     * 重连 关闭之前的连接
     */
    void closeWebSocket();
}
