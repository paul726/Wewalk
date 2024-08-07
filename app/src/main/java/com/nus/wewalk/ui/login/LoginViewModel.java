package com.nus.wewalk.ui.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nus.wewalk.net.HttpUtil;
import com.nus.wewalk.ui.login.data.LoginBean;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    MutableLiveData<LoginBean> loginBeanLiveData = new MutableLiveData<>();

    public void login(String email, String password) {
        Map<String, Object> params = new HashMap<>();
        params.put("user_name", email);
        params.put("password", password);
        HttpUtil.restAPI.login(params).enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                loginBeanLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable throwable) {
                loginBeanLiveData.setValue(null);
            }
        });
    }

}
