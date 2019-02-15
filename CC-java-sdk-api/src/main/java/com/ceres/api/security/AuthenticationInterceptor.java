package com.ceres.api.security;

import com.alibaba.fastjson.JSONObject;
import com.ceres.api.constant.Const;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class AuthenticationInterceptor implements Interceptor{


    private final String apiKey;

    private final String secret;

    public AuthenticationInterceptor(String apiKey, String secret) {
        this.apiKey = apiKey;
        this.secret = secret;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder newRequestBuilder = original.newBuilder();

        boolean isApiKeyRequired = original.header(Const.ENDPOINT_SECURITY_TYPE_APIKEY) != null;
        boolean isSignatureRequired = original.header(Const.ENDPOINT_SECURITY_TYPE_SIGNED) != null;
        newRequestBuilder.removeHeader(Const.ENDPOINT_SECURITY_TYPE_APIKEY)
                .removeHeader(Const.ENDPOINT_SECURITY_TYPE_SIGNED);

        // Endpoint requires sending a valid API-KEY
        if (isApiKeyRequired || isSignatureRequired) {
            newRequestBuilder.addHeader(Const.API_KEY_HEADER, apiKey);
        }

        // Endpoint requires sending a valid signature
        if (isSignatureRequired) {
            StringBuilder attr = new StringBuilder();
            //==========拼接参数=======//
            // body
            RequestBody body = original.body();
            String bodyStr = bodyToString(body);
            // splice body
            if (StringUtils.isNotEmpty(bodyStr)) {
                JSONObject params = JSONObject.parseObject(bodyStr);
                String[] keys = params.keySet().toArray(new String[0]);
                Arrays.sort(keys);
                for (String key : keys) {
                    String value = params.get(key)!=null?params.get(key).toString():null;
                    if (!StringUtils.isEmpty(value)) {
                        attr.append(key).append("=").append(value).append("&");
                    }
                }
            }
            // query
            String payload = original.url().query();
            // splice query
            if (StringUtils.isNotEmpty(payload)) {
                String[] strArr = payload.split("&");
                Arrays.sort(strArr);
                for (String str : strArr) {
                    attr.append(str).append("&");
                }
            }
            // path param
            String method = original.method();
            if (method.equalsIgnoreCase("Delete")) {
                String value = original.url().encodedPath().substring(original.url().encodedPath().lastIndexOf("/") + 1);
                attr.append("system_oid=").append(value).append("&");
            }
            //==========拼接参数=======//
            String sign = HmacSHA256Signer.sign2(attr.toString(), secret);
            newRequestBuilder.addHeader(Const.SIGN_HEADER, sign);
        }
        // Build new request after adding the necessary authentication information
        Request newRequest = newRequestBuilder.build();
        return chain.proceed(newRequest);
    }

    /**
     * Extracts the request body into a String.
     *
     * @return request body as a string
     */
    @SuppressWarnings("unused")
    private static String bodyToString(RequestBody request) {
        try (final Buffer buffer = new Buffer()) {
            final RequestBody copy = request;
            if (copy != null) {
                copy.writeTo(buffer);
            } else {
                return "";
            }
            return buffer.readUtf8();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        final AuthenticationInterceptor that = (AuthenticationInterceptor) o;
        return Objects.equals(apiKey, that.apiKey) &&
                Objects.equals(secret, that.secret);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apiKey, secret);
    }
}
