package com.ceres.api.constant;

/**
 * @author LMT
 * @date 2019/01/30
 */
public class Const {

    /**
     * 沙箱环境
     */

    public static final String PRE_API_BASE_URL = "https://open.lmttrade.net";
    public static String PRE_wsUrl = "wss://open.lmttrade.net/ws/market";
    public static String PRE_orderWsUrl = "wss://open.lmttrade.net/ws/trade";

    /**
     * =================================================================================================================
     */

    /**
     * 生产环境
     */
    public static final String API_BASE_URL = "https://open.lmt.trade";
    /**
     * 行情订阅
     */
    public static String wsUrl = "wss://open.lmt.trade/ws/market";

    /**
     * 订单订阅
     */
    public static String orderWsUrl = "wss://open.lmt.trade/ws/trade";

    public static final String ENDPOINT_SECURITY_TYPE_APIKEY = "api_key";
    public static final String ENDPOINT_SECURITY_TYPE_SIGNED = "sign";

    public static final String API_KEY_HEADER = "api_key";
    public static final String SIGN_HEADER = "sign";

    public static final String ENDPOINT_SECURITY_TYPE_APIKEY_HEADER = ENDPOINT_SECURITY_TYPE_APIKEY + ": #";
    public static final String ENDPOINT_SECURITY_TYPE_SIGNED_HEADER = ENDPOINT_SECURITY_TYPE_SIGNED + ": #";

    public static final String MONITOR_TRADE = "trade";
    public static final String MONITOR_MARKET = "market";
}
