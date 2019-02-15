package com.ceres.api.client;

import com.ceres.api.service.CoinceresApiRestClient;
import com.ceres.api.service.CoinceresApiWebSocketClient;
import com.ceres.api.service.CoinceresDataRestClient;
import com.ceres.api.service.impl.CoinceresApiRestClientImpl;
import com.ceres.api.service.impl.CoinceresApiWebSocketClientImpl;
import com.ceres.api.service.impl.CoinceresDataRestClientImpl;

import static com.ceres.api.service.CoinceresApiServiceGenerator.getSharedClient;

/**
 * @author xiaotian.huang
 * @date 2019/01/30
 */
public class CoinceresApiClientFactory {

    private String apiKey;

    private String secret;

    /**
     * Instantiates a new binance api client factory.
     */
    private CoinceresApiClientFactory(String apiKey, String secret) {
        this.apiKey = apiKey;
        this.secret = secret;
    }

    /**
     * New instance.
     */
    public static CoinceresApiClientFactory newInstance(String apiKey, String secret) {
        return new CoinceresApiClientFactory(apiKey, secret);
    }

    /**
     * New instance without authentication.
     */
    public static CoinceresApiClientFactory newInstance() {
        return new CoinceresApiClientFactory(null, null);
    }

    /**
     * Creates a new web socket client used for handling data streams.
     */
    public static CoinceresApiWebSocketClient newWebSocketClient() {
        return new CoinceresApiWebSocketClientImpl(getSharedClient());
    }

    /**
     * Creates a new synchronous/blocking REST client.
     */
    public CoinceresApiRestClient newRestClient() {
        return new CoinceresApiRestClientImpl(apiKey, secret);
    }

    /**
     * creates a new synchronous client for data
     */
    public CoinceresDataRestClient newDataClient() {
        return new CoinceresDataRestClientImpl();
    }
}
