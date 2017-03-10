package io.intrepid.meddle.rest;

import android.support.annotation.VisibleForTesting;

import java.util.concurrent.TimeUnit;

import io.intrepid.meddle.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class RetrofitClient {
    private static final String BASE_URL = "https://esoncym709.execute-api.us-east-1.amazonaws.com/";
    private static final int CONNECTION_TIMEOUT = 30;

    private static RestApi instance;

    public static RestApi getApi() {
        if (instance == null) {
            instance = createRestApi(BASE_URL);
        }
        return instance;
    }

    @VisibleForTesting
    static RestApi getTestApi(String baseUrl) {
        return createRestApi(baseUrl);
    }

    private RetrofitClient() {
    }

    private static RestApi createRestApi(String baseUrl) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.LOG_CONSOLE) {
            builder.addInterceptor(new HttpLoggingInterceptor(message -> Timber.v(message)).setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        OkHttpClient httpClient = builder
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(RestApi.class);
    }
}
