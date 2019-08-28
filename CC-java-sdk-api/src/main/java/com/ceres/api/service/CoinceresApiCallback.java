package com.ceres.api.service;

/**
 * @author LMT
 * @date 2019/01/30
 */
@FunctionalInterface
public interface CoinceresApiCallback<T> {
    /**
     * 响应
     * @param response
     */
    void onResponse(T response);

    /**
     * 失败响应
     * @param cause
     */
    default void onFailure(Throwable cause) {}
}
