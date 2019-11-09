package com.ceres.api.client;

import com.ceres.api.service.CoinceresApiRestClient;
import com.ceres.api.service.CoinceresApiWebSocketClient;
import com.ceres.api.service.CoinceresDataRestClient;
import com.ceres.api.service.CoinceresTradeWebSocketClient;
import com.ceres.api.service.impl.CoinceresApiRestClientImpl;
import com.ceres.api.service.impl.CoinceresApiWebSocketClientImpl;
import com.ceres.api.service.impl.CoinceresDataRestClientImpl;
import com.ceres.api.service.impl.CoinceresTradeWebSocketClientImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.ceres.api.service.CoinceresApiServiceGenerator.getSharedClient;

/**
 * @author LMT
 * @date 2019/01/30
 */
public class CoinceresApiClientFactory {

    private String apiKey;

    private String secret;

    public static final Map<String,Long> MONITOR_MAP = new ConcurrentHashMap<>();

    /**
     * Instantiates a new coinceres api client factory.
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
    public static CoinceresApiWebSocketClient newWebSocketClient(String wsEndPoint) {
        return new CoinceresApiWebSocketClientImpl(wsEndPoint,getSharedClient());
    }

    /**
     * Creates a new synchronous/blocking REST client.
     */
    public CoinceresApiRestClient newRestClient(String endPoint) {
        return new CoinceresApiRestClientImpl(endPoint,apiKey, secret);
    }

    /**
     * creates a new synchronous client for data
     */
    public CoinceresDataRestClient newDataClient(String endPoint) {
        return new CoinceresDataRestClientImpl(endPoint);
    }


    /**
     * create a order stream
     */
    public CoinceresTradeWebSocketClient newTradeWebSocketClient(String wsEndPoint) {
        return new CoinceresTradeWebSocketClientImpl(wsEndPoint,getSharedClient(), apiKey, secret);
    }
}
