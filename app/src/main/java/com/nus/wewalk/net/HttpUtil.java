package com.nus.wewalk.net;

import android.util.Log;


import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public class HttpUtil {

    public static RestAPI restAPI;
    private static String BASE_URL = "";

    static {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NotNull String s) {
                Log.i("wewalk_http", s);
            }
        });

        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.MILLISECONDS)
                .connectTimeout(60, TimeUnit.MILLISECONDS);

        builder.addInterceptor(httpLoggingInterceptor);

        OkHttpClient okHttpClient = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(new ApiResponseConverterFactory())
                .build();
        restAPI = retrofit.create(RestAPI.class);
    }
}
