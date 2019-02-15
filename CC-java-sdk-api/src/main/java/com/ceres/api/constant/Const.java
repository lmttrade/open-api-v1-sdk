package com.ceres.api.constant;

/**
 * @author xiaotian.huang
 * @date 2019/01/30
 */
public class Const {

    public static final String API_BASE_URL = "http://localhost:9088";
    public static final String DATA_BASE_URL = "http://localhost:9101";
    public static String wsUrl = "ws://localhost:9307";

    public static final String ENDPOINT_SECURITY_TYPE_APIKEY = "api_key";
    public static final String ENDPOINT_SECURITY_TYPE_SIGNED = "sign";

    public static final String API_KEY_HEADER = "api_key";
    public static final String SIGN_HEADER = "sign";

    public static final String ENDPOINT_SECURITY_TYPE_APIKEY_HEADER = ENDPOINT_SECURITY_TYPE_APIKEY + ": #";
    public static final String ENDPOINT_SECURITY_TYPE_SIGNED_HEADER = ENDPOINT_SECURITY_TYPE_SIGNED + ": #";
}
