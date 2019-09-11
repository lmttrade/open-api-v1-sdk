package com.ceres.api.constant;

/**
 * @author LMT
 * @date 2019/01/30
 */
public class Const {

    public static final String API_BASE_URL = "http://open.lmt.trade";
    public static final String DATA_BASE_URL = "https://open.lmt.trade";

    /**
     * 行情订阅
     */
    public static String wsUrl = "wss://open.lmt.trade:28003";

    /**
     * 订单订阅
     */
    public static String orderWsUrl = "wss://open.lmt.trade:29003";

    public static final String ENDPOINT_SECURITY_TYPE_APIKEY = "api_key";
    public static final String ENDPOINT_SECURITY_TYPE_SIGNED = "sign";

    public static final String API_KEY_HEADER = "api_key";
    public static final String SIGN_HEADER = "sign";

    public static final String ENDPOINT_SECURITY_TYPE_APIKEY_HEADER = ENDPOINT_SECURITY_TYPE_APIKEY + ": #";
    public static final String ENDPOINT_SECURITY_TYPE_SIGNED_HEADER = ENDPOINT_SECURITY_TYPE_SIGNED + ": #";
}
