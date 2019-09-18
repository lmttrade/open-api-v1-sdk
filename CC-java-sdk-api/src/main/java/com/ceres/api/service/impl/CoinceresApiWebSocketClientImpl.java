package com.ceres.api.service.impl;

import com.ceres.api.constant.Const;
import com.ceres.api.domain.event.CandleEvent;
import com.ceres.api.domain.event.DepthEvent;
import com.ceres.api.domain.event.TickEvent;
import com.ceres.api.domain.event.TradeEvent;
import com.ceres.api.service.CoinceresApiCallback;
import com.ceres.api.service.CoinceresApiWebSocketClient;
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

    private final OkHttpClient client;

    private static ScheduledExecutorService scheduledService;

    static {
        scheduledService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("lmt-market-ping-scheduled-%d").daemon(true).build());
    }

    public CoinceresApiWebSocketClientImpl(OkHttpClient client) {
        this.client = client;
    }

    @Override
    public void close() throws IOException {
    }

    @Override
    public Closeable onDepthEvent(String text, CoinceresApiCallback<DepthEvent> callback) {
        return createNewWebSocket(text, new CoinceresApiWebSocketListener<>(callback, DepthEvent.class));
    }

    @Override
    public Closeable onCandleEvent(String text, CoinceresApiCallback<CandleEvent> callback) {
        return createNewWebSocket(text, new CoinceresApiWebSocketListener<>(callback, CandleEvent.class));
    }

    @Override
    public Closeable onTickEvent(String text, CoinceresApiCallback<TickEvent> callback) {
        return createNewWebSocket(text, new CoinceresApiWebSocketListener<>(callback, TickEvent.class));
    }

    @Override
    public Closeable onTradeEvent(String text, CoinceresApiCallback<TradeEvent> callback) {
        return createNewWebSocket(text, new CoinceresApiWebSocketListener<>(callback, TradeEvent.class));
    }

    private Closeable createNewWebSocket(String text, CoinceresApiWebSocketListener<?> listener) {
        String streamingUrl = Const.wsUrl;
        Request request = new Request.Builder().url(streamingUrl).build();
        final WebSocket webSocket = client.newWebSocket(request, listener);
        // 订阅
        webSocket.send(text);

        scheduledService.scheduleAtFixedRate(()->webSocket.send("ping"),5,10,TimeUnit.SECONDS);
        return () -> {
            final int code = 1000;
            log.warn("行情-关闭ping-pong线程监听");
            scheduledService.shutdownNow();
            listener.onClosing(webSocket, code, null);
            webSocket.close(code, null);
            listener.onClosed(webSocket, code, null);
        };
    }
}
