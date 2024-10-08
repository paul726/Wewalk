package com.nus.wewalk.net;

import android.util.Log;


import com.google.gson.Gson;
import com.nus.wewalk.utilities.XShareCacheUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtil {

    public static RestAPI restAPI;
    private static String BASE_URL = "http://54.254.244.55:8080/";

    static {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NotNull String s) {
                Log.i("wewalk_http", s);
            }
        });

        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .hostnameVerifier((hostname, sslSession) -> true)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS);

        builder.addInterceptor(httpLoggingInterceptor);

        OkHttpClient okHttpClient = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
//                .addConverterFactory(new ApiResponseConverterFactory())   接口理想话枪口下自定义
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        restAPI = retrofit.create(RestAPI.class);
    }
}
