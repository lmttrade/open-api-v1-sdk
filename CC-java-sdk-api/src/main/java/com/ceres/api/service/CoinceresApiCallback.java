package com.ceres.api.service;

/**
 * @author xiaotian.huang
 * @date 2019/01/30
 */
@FunctionalInterface
public interface CoinceresApiCallback<T> {

    void onResponse(T response);

    default void onFailure(Throwable cause) {}
}
