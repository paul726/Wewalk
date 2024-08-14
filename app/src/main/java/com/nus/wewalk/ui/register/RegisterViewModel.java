package com.nus.wewalk.ui.register;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nus.wewalk.net.HttpUtil;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends ViewModel {

    MutableLiveData<Boolean> liveData = new MutableLiveData<>();

    public void register(String username, String password) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        HttpUtil.restAPI.register(params).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    liveData.setValue(response.body());
                } else {
                    liveData.setValue(false);
                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable throwable) {
                liveData.setValue(false);
            }
        });
    }
}
