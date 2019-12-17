package com.ceres.api.service.impl;

import com.ceres.api.service.CoinceresApiWebSocketClient;
import com.ceres.api.service.CoinceresWebsocketCallback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author LMT
 * @date 2019/01/30
 */
public class CoinceresApiWebSocketClientImpl implements CoinceresApiWebSocketClient, Closeable {

    private final static Logger log = LoggerFactory.getLogger(CoinceresTradeWebSocketClientImpl.class);

    private final String wsEndPoint;

    private final OkHttpClient client;

    private static ScheduledExecutorService scheduledService;

    private WebSocket webSocket = null;


    public CoinceresApiWebSocketClientImpl(String wsEndPoint,OkHttpClient client) {
        this.wsEndPoint = wsEndPoint;
        this.client = client;

        scheduledService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("lmt-market-ping-scheduled-%d").daemon(true).build());

        scheduledService.scheduleAtFixedRate(() -> {
            if (webSocket != null){
                webSocket.send("ping");
            }
        }, 5, 10, TimeUnit.SECONDS);
    }

    @Override
    public void close() throws IOException {
    }

    @Override
    public Closeable onMarketEvent(String text, CoinceresWebsocketCallback callback) {
        return createNewWebSocket(text, callback);
    }

    private Closeable createNewWebSocket(String text, CoinceresWebsocketCallback callback) {
        if (webSocket == null) {
            Request request = new Request.Builder().url(this.wsEndPoint).build();
            CoinceresApiWebSocketListener listener = new CoinceresApiWebSocketListener(callback);
            webSocket = client.newWebSocket(request, listener);
            // 订阅
            webSocket.send(text);
            log.error("开始订阅:{}", text);
            return () -> {
                final int code = 1000;
                log.warn("行情-关闭ping-pong线程监听");
                scheduledService.shutdownNow();
                listener.onClosing(webSocket, code, null);
                webSocket.close(code, null);
                listener.onClosed(webSocket, code, null);
            };
        }else {
            webSocket.send(text);
            log.error("开始订阅:{}", text);
            return null;
        }
    }

    @Override
    public void closeWebSocket() {
        if (this.webSocket != null) {
            this.webSocket.close(1000, "断线重连");
            this.webSocket = null;
        }
    }
}
