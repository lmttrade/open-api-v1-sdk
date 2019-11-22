package com.ceres.api.service.impl;

import com.ceres.api.domain.stream.EntrustNotice;
import com.ceres.api.service.CoinceresApiCallback;
import com.ceres.api.service.CoinceresTradeWebSocketClient;
import com.ceres.api.util.SHA256Util;
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
public class CoinceresTradeWebSocketClientImpl implements CoinceresTradeWebSocketClient, Closeable {

    private final static Logger log = LoggerFactory.getLogger(CoinceresTradeWebSocketClientImpl.class);

    private final String wsEndPoint;

    private final OkHttpClient client;

    private String apiKey;

    private String secret;

    private static ScheduledExecutorService scheduledService;

    private WebSocket webSocket = null;

    static {
        scheduledService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("lmt-trade-ping-scheduled-%d").daemon(true).build());
    }

    public CoinceresTradeWebSocketClientImpl(String wsEndPoint,OkHttpClient client,String apiKey, String secret) {
        this.wsEndPoint = wsEndPoint;
        this.client = client;
        this.apiKey = apiKey;
        this.secret = secret;
    }

    @Override
    public void close() throws IOException {
    }

    @Override
    public Closeable onOrderStreamEvent(CoinceresApiCallback<EntrustNotice> callback) {
        return createNewWebSocket(new CoinceresTradeWebSocketListener<>(callback, EntrustNotice.class));
    }

    private Closeable createNewWebSocket(CoinceresTradeWebSocketListener<?> listener) {
        // cal sign
        long timestamp = System.currentTimeMillis();
        String src = "api_key=" + apiKey + "&timestamp="+timestamp+"&secret=" + secret;
        String sign = null;
        try {
            sign = SHA256Util.toSignBySha256(src);
        } catch (Exception e) {
            log.error("Signature Error，apiKey:{}",apiKey);
        }
        String streamingUrl = this.wsEndPoint + "?api_key=" + apiKey + "&timestamp="+timestamp+"&sign=" + sign;
        Request request = new Request.Builder().url(streamingUrl).build();
        webSocket = client.newWebSocket(request, listener);
        scheduledService.scheduleAtFixedRate(()->webSocket.send("ping"),5,10,TimeUnit.SECONDS);

        return () -> {
            final int code = 1000;
            log.warn("交易-关闭ping-pong线程监听");
            scheduledService.shutdownNow();
            listener.onClosing(webSocket, code, null);
            webSocket.close(code, null);
            listener.onClosed(webSocket, code, null);
        };
    }

    @Override
    public void closeWebSocket() {
        if (this.webSocket != null) {
            this.webSocket.close(1000, "断线重连");
            this.webSocket = null;
        }
    }
}
