package com.ceres.api.service.impl;

import com.ceres.api.exception.CoinceresApiException;
import com.ceres.api.service.CoinceresApiCallback;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.ceres.api.client.CoinceresApiClientFactory.MONITOR_MAP;
import static com.ceres.api.constant.Const.MONITOR_TRADE;

/**
 * @author LMT
 * @date 2019/01/30
 */
public class CoinceresTradeWebSocketListener<T> extends WebSocketListener {

    private static final Logger log = LoggerFactory.getLogger(CoinceresTradeWebSocketListener.class);

    private CoinceresApiCallback<T> callback;

    private Class<T> eventClass;

    private TypeReference<T> eventTypeReference;

    private boolean closing = false;

    private  static ObjectMapper objectMapper = buildObjectMapper();

    private static ObjectMapper buildObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    public CoinceresTradeWebSocketListener(CoinceresApiCallback<T> callback, Class<T> eventClass) {
        this.callback = callback;
        this.eventClass = eventClass;
    }


    public CoinceresTradeWebSocketListener(CoinceresApiCallback<T> callback, TypeReference<T> eventTypeReference) {
        this.callback = callback;
        this.eventTypeReference = eventTypeReference;
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        log.info("连接成功:{}", response.code());
        MONITOR_MAP.put(MONITOR_TRADE, 0L);
    }

    @Override
    @SuppressWarnings("all")
    public void onMessage(WebSocket webSocket, String text) {
        if ("pong".equalsIgnoreCase(text)) {
            log.info("收到交易连接心跳:{} 当前线程:{}", text,Thread.currentThread().getId());
            if (MONITOR_MAP.get(MONITOR_TRADE) != null){
                Long current = MONITOR_MAP.get(MONITOR_TRADE);
                long next = current+1;
                log.info("交易-最新心跳监测数:{}", next);
                MONITOR_MAP.put(MONITOR_TRADE, next);
            }else {
                MONITOR_MAP.put(MONITOR_TRADE, 0L);
            }
            return;
        }
        try {
            T event = null;
            if (eventClass == null) {
                event = objectMapper.readValue(text, eventTypeReference);
            } else {
                event = objectMapper.readValue(text, eventClass);
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

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        log.warn("关闭连接");
    }
}
