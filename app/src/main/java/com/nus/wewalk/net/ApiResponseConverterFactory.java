package com.nus.wewalk.net;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public final class ApiResponseConverterFactory extends Converter.Factory {

    private final Gson gson;

    public ApiResponseConverterFactory() {
        this.gson = new Gson();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(
            Type type,
            Annotation[] annotations,
            Retrofit retrofit
    ) {
        TypeToken<?> typeToken = TypeToken.getParameterized(ApiResponse.class, type);
        return new Converter<ResponseBody, Object>() {
            @Override
            public Object convert(ResponseBody responseBody) throws IOException {
                String jsonString = responseBody.string();
                JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);

                int code = jsonObject.get("code").getAsInt();
                String msg = jsonObject.get("msg").getAsString();
                Object data = gson.fromJson(jsonObject.get("data"), type);

                if (code != 200) {
                    throw new ApiException(code, msg);
                }

                return data;
            }
        };
    }
}
