package com.nus.wewalk.ui.register;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nus.wewalk.net.ApiResponse;
import com.nus.wewalk.net.HttpUtil;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends ViewModel {

    MutableLiveData<ApiResponse> liveData = new MutableLiveData<>();

    public void register(String username, String password) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        HttpUtil.restAPI.register(params).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    liveData.setValue(response.body());
                } else {
                    liveData.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable throwable) {
                liveData.setValue(null);
            }
        });
    }
}
