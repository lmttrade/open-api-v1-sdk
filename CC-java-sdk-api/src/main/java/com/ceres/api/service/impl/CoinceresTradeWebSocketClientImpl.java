package com.ceres.api.service.impl;

import com.ceres.api.constant.Const;
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

public class CoinceresTradeWebSocketClientImpl implements CoinceresTradeWebSocketClient, Closeable {

    private final static Logger log = LoggerFactory.getLogger(CoinceresTradeWebSocketClientImpl.class);

    private final OkHttpClient client;

    private String apiKey;

    private String secret;

    public CoinceresTradeWebSocketClientImpl(OkHttpClient client,String apiKey, String secret) {
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
        String src = "api_key=" + apiKey + "&secret=" + secret;
        String sign = null;
        try {
            sign = SHA256Util.toSignBySha256(src);
        } catch (Exception e) {
            log.error("Signature Error，apiKey:{}",apiKey);
        }
        String streamingUrl = Const.orderWsUrl + "?api_key=" + apiKey + "&sign=" + sign;
        Request request = new Request.Builder().url(streamingUrl).build();
        final WebSocket webSocket = client.newWebSocket(request, listener);
        ScheduledExecutorService scheduledService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("coinceres-scheduled-%d").daemon(true).build());
        scheduledService.scheduleAtFixedRate(()->webSocket.send("ping"),5,10,TimeUnit.SECONDS);
        return () -> {
            final int code = 1000;
            listener.onClosing(webSocket, code, null);
            webSocket.close(code, null);
            listener.onClosed(webSocket, code, null);
        };
    }
}
