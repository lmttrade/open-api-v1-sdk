package com.ceres.api.service;

import com.ceres.api.domain.event.ProcessEvent;

/**
 * @author LMT
 * @date 2019/01/30
 */
@FunctionalInterface
public interface CoinceresWebsocketCallback {
    /**
     * 响应
     * @param response
     */
    void onResponse(ProcessEvent response);

    /**
     * 失败响应
     * @param cause
     */
    default void onFailure(Throwable cause) {

    }
}
