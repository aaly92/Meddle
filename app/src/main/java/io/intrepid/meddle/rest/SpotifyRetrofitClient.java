package io.intrepid.meddle.rest;

import android.support.annotation.VisibleForTesting;

import java.util.concurrent.TimeUnit;

import io.intrepid.meddle.models.TokenStorage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class SpotifyRetrofitClient {
    private static final String BASE_URL = "https://api.spotify.com";
    private static final int CONNECTION_TIMEOUT = 30;

    private static SpotifyApi instance;

    private SpotifyRetrofitClient() {
    }

    public static void init(TokenStorage tokenStorage) {
        instance = createRestApi(BASE_URL, tokenStorage);
    }

    public static SpotifyApi getApi() {
        return instance;
    }

    @VisibleForTesting
    static SpotifyApi getTestApi(String baseUrl, TokenStorage tokenStorage) {
        return createRestApi(baseUrl, tokenStorage);
    }

    private static SpotifyApi createRestApi(String baseUrl, TokenStorage tokenStorage) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(chain -> {
                                   Request original = chain.request();

                                   Request request = original.newBuilder()
                                           .header("Accept", "application/json")
                                           .header("Authorization", "Bearer " + tokenStorage.token)
                                           .method(original.method(), original.body())
                                           .build();

                                   return chain.proceed(request);
                               }
        );

        OkHttpClient httpClient = builder
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(SpotifyApi.class);
    }
}
