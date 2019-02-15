package com.ceres.api.service;

import com.ceres.api.constant.Const;
import com.ceres.api.exception.CoinceresApiError;
import com.ceres.api.exception.CoinceresApiException;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;

/**
 * @author xiaotian.huang
 * @date 2019/01/30
 */
@SuppressWarnings("all")
public class CoinceresDataServiceGenerator {

    private static final OkHttpClient sharedClient;
    private static final Converter.Factory converterFactory = JacksonConverterFactory.create();

    static {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequestsPerHost(500);
        dispatcher.setMaxRequests(500);
        sharedClient = new OkHttpClient.Builder()
                .dispatcher(dispatcher)
                .build();
    }

    private static final Converter<ResponseBody, CoinceresApiError> errorBodyConverter =
            (Converter<ResponseBody, CoinceresApiError>)converterFactory.responseBodyConverter(
                    CoinceresApiError.class, new Annotation[0], null);

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(Const.DATA_BASE_URL)
                .addConverterFactory(converterFactory)
                .client(sharedClient);
        Retrofit retrofit = retrofitBuilder.build();
        return retrofit.create(serviceClass);
    }

    /**
     * Execute a REST call and block until the response is received.
     */
    public static <T> T executeSync(Call<T> call) {
        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                CoinceresApiError apiError = getCoinceresApiError(response);
                throw new CoinceresApiException(apiError);
            }
        } catch (IOException e) {
            throw new CoinceresApiException(e);
        }
    }

    /**
     * Extracts and converts the response error body into an object.
     */
    public static CoinceresApiError getCoinceresApiError(Response<?> response) throws IOException, CoinceresApiException {
        return errorBodyConverter.convert(response.errorBody());
    }

    /**
     * Returns the shared OkHttpClient instance.
     */
    public static OkHttpClient getSharedClient() {
        return sharedClient;
    }
}
