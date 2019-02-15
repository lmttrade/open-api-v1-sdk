package com.ceres.api.service.impl;

import com.ceres.api.exception.CoinceresApiException;
import com.ceres.api.service.CoinceresApiCallback;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author xiaotian.huang
 * @date 2019/01/30
 */
public class CoinceresApiWebSocketListener<T> extends WebSocketListener {

  private static final Logger log = LoggerFactory.getLogger(CoinceresApiWebSocketListener.class);

  private CoinceresApiCallback<T> callback;

  private Class<T> eventClass;

  private TypeReference<T> eventTypeReference;

  private boolean closing = false;

  public CoinceresApiWebSocketListener(CoinceresApiCallback<T> callback, Class<T> eventClass) {
    this.callback = callback;
    this.eventClass = eventClass;
  }

  public CoinceresApiWebSocketListener(CoinceresApiCallback<T> callback, TypeReference<T> eventTypeReference) {
    this.callback = callback;
    this.eventTypeReference = eventTypeReference;
  }

  @Override
  @SuppressWarnings("all")
  public void onMessage(WebSocket webSocket, String text) {
    ObjectMapper mapper = new ObjectMapper();
    JsonNode jsonNode = null;
    try {
      jsonNode = mapper.readTree(text);
    } catch (Exception e) {
      log.error("jackson readTree occur error",e);
      return;
    }
    if (jsonNode.has("info")) {
      log.info("The handshake with Coinceres Server Endpoint successful! {}",jsonNode.get("info"));
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
        event = mapper.readValue(text, eventTypeReference);
      } else {
        event = mapper.readValue(text, eventClass);
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
      log.info("Client Endpoint Have Closed ! info:{}",t.getMessage());
      callback.onFailure(t);
    }
  }
}