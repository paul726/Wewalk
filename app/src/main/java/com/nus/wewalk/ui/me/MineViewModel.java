package com.nus.wewalk.ui.me;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nus.wewalk.net.HttpUtil;
import com.nus.wewalk.ui.login.data.LoginBean;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MineViewModel extends ViewModel {


    MutableLiveData<UserInfoBean> userInfoBeanLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> saveBeanLiveData = new MutableLiveData<>();
    /**
     * 个人资料
     */
    public void getUserInfo() {
        HttpUtil.restAPI.getUserInfo().enqueue(new Callback<UserInfoBean>() {
            @Override
            public void onResponse(Call<UserInfoBean> call, Response<UserInfoBean> response) {
                userInfoBeanLiveData.setValue(response.body());
            }
            @Override
            public void onFailure(Call<UserInfoBean> call, Throwable throwable) {
                userInfoBeanLiveData.setValue(null);
            }
        });
    }
    /**
     * 个人资料-保存
     */
    public void saveUserInfo(Map<String, Object> params) {
        HttpUtil.restAPI.saveUserInfo(params).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                saveBeanLiveData.setValue(response.body());
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable throwable) {
                saveBeanLiveData.setValue(null);
            }
        });
    }
}