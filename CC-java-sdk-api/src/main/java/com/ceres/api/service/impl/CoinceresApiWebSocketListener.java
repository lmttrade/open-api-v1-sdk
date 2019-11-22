package com.ceres.api.service.impl;

import com.ceres.api.exception.CoinceresApiException;
import com.ceres.api.service.CoinceresApiCallback;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;

import static com.ceres.api.client.CoinceresApiClientFactory.MONITOR_MAP;
import static com.ceres.api.constant.Const.MONITOR_MARKET;

/**
 * @author LMT
 * @date 2019/01/30
 */
public class CoinceresApiWebSocketListener<T> extends WebSocketListener {

    private static final Logger log = LoggerFactory.getLogger(CoinceresApiWebSocketListener.class);

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

    public CoinceresApiWebSocketListener(CoinceresApiCallback<T> callback, Class<T> eventClass) {
        this.callback = callback;
        this.eventClass = eventClass;
    }

    public CoinceresApiWebSocketListener(CoinceresApiCallback<T> callback, TypeReference<T> eventTypeReference) {
        this.callback = callback;
        this.eventTypeReference = eventTypeReference;
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        log.info("连接成功:{}", response.code());
        MONITOR_MAP.put(MONITOR_MARKET, 0L);
    }

    @Override
    @SuppressWarnings("all")
    public void onMessage(WebSocket webSocket, String text) {
        JsonNode jsonNode = null;

        if (text.equalsIgnoreCase("success")) {
            log.info("WebSocket 连接成功!");
            return;
        }

        if (text.equalsIgnoreCase("pang")) {
            log.info("收到行情连接心跳:{}", text);
            if (MONITOR_MAP.get(MONITOR_MARKET) != null){
                Long current = MONITOR_MAP.get(MONITOR_MARKET);
                long next = current+1;
                log.info("行情-最新心跳监测数:{}", next);
                MONITOR_MAP.put(MONITOR_MARKET, next);
            }else {
                MONITOR_MAP.put(MONITOR_MARKET, 0L);
            }
            return;
        }

        try {
            jsonNode = objectMapper.readTree(text);
        } catch (Exception e) {
            log.error("jackson readTree occur error", e);
            return;
        }
        if (jsonNode.has("info")) {
            log.info("The handshake with Coinceres Server Endpoint successful! {}", jsonNode.get("info"));
            return;
        }
        if (jsonNode.isArray()) {
            Iterator<JsonNode> elements = jsonNode.elements();
            if (elements.next().has("code")) {
                log.info("subscription message: {}", jsonNode.toString());
                return;
            }
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
            log.info("Client Endpoint Have Closed ! info:{}", t.getMessage());
            callback.onFailure(t);
        }
    }
}