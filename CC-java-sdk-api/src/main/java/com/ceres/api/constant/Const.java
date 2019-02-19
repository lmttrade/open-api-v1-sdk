package com.ceres.api.constant;

/**
 * @author xiaotian.huang
 * @date 2019/01/30
 */
public class Const {

    public static final String API_BASE_URL = "https://open.coinceres.com";
    public static final String DATA_BASE_URL = "https://open.coinceres.com";
    public static String wsUrl = "ws://open.coinceres.com:18003";
    public static String orderWsUrl = "ws://open.coinceres.com:19003";

    public static final String ENDPOINT_SECURITY_TYPE_APIKEY = "api_key";
    public static final String ENDPOINT_SECURITY_TYPE_SIGNED = "sign";

    public static final String API_KEY_HEADER = "api_key";
    public static final String SIGN_HEADER = "sign";

    public static final String ENDPOINT_SECURITY_TYPE_APIKEY_HEADER = ENDPOINT_SECURITY_TYPE_APIKEY + ": #";
    public static final String ENDPOINT_SECURITY_TYPE_SIGNED_HEADER = ENDPOINT_SECURITY_TYPE_SIGNED + ": #";
}
