package com.nus.wewalk.net;

import com.nus.wewalk.ui.login.data.LoginBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RestAPI {

    @POST("api/login2")
    Call<LoginBean> login(@Body Map<String, Object> params);

    @POST("api/register")
    Call<Boolean> register(@Body Map<String, Object> params);

}
