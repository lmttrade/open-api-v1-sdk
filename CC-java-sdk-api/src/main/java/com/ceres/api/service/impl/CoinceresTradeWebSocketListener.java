package com.ceres.api.service.impl;

import com.ceres.api.exception.CoinceresApiException;
import com.ceres.api.service.CoinceresApiCallback;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CoinceresTradeWebSocketListener<T> extends WebSocketListener {

    private static final Logger log = LoggerFactory.getLogger(CoinceresTradeWebSocketListener.class);

    private CoinceresApiCallback<T> callback;

    private Class<T> eventClass;

    private TypeReference<T> eventTypeReference;

    private boolean closing = false;

    public CoinceresTradeWebSocketListener(CoinceresApiCallback<T> callback, Class<T> eventClass) {
        this.callback = callback;
        this.eventClass = eventClass;
    }


    public CoinceresTradeWebSocketListener(CoinceresApiCallback<T> callback, TypeReference<T> eventTypeReference) {
        this.callback = callback;
        this.eventTypeReference = eventTypeReference;
    }

    @Override
    @SuppressWarnings("all")
    public void onMessage(WebSocket webSocket, String text) {
        ObjectMapper mapper = new ObjectMapper();
        if ("pong".equalsIgnoreCase(text)) {
            return;
        }
        try {
            T event = null;
            if (eventClass == null) {
                event = mapper.readValue(text, eventTypeReference);
            } else {
                event = mapper.readValue(text, eventClass);
            }
            callback.onResponse(event);
        } catch (IOException e) {
            log.error("反序列化出现错误");
            throw new CoinceresApiException(e);
        }
    }

    @Override
    public void onClosing(final WebSocket webSocket, final int code, final String reason) {
        closing = true;
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        if (!closing) {
            log.info("Client Endpoint Have Closed ! info:{}",t.getMessage());
            callback.onFailure(t);
        }
    }
}
