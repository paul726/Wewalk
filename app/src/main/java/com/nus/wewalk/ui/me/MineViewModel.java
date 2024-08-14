package com.nus.wewalk.ui.me;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nus.wewalk.net.ApiResponse;
import com.nus.wewalk.net.HttpUtil;
import com.nus.wewalk.ui.login.data.LoginBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MineViewModel extends ViewModel {


    MutableLiveData<UserInfoBean> userInfoBeanLiveData = new MutableLiveData<>();
    MutableLiveData<ApiResponse> saveBeanLiveData = new MutableLiveData<>();
    MutableLiveData<List<UserInfoBean>> friendsListLiveData = new MutableLiveData<>();

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
        HttpUtil.restAPI.saveUserInfo(params).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                saveBeanLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable throwable) {
                saveBeanLiveData.setValue(null);
            }
        });
    }

    /**
     * 密码修改
     */
    public void updatePwd(String oid, String newPwd) {
        HttpUtil.restAPI.updatePwd(oid, newPwd).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                saveBeanLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable throwable) {
                saveBeanLiveData.setValue(null);
            }
        });
    }

    /**
     * 密码修改
     */
    public void logout(String uid) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", uid);
        HttpUtil.restAPI.logout(params).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                saveBeanLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable throwable) {
                saveBeanLiveData.setValue(null);
            }
        });
    }

    /**
     * 查询好友
     */
    public void getFriends(String oid, String newPwd) {
        HttpUtil.restAPI.getFriends(oid, newPwd).enqueue(new Callback<List<UserInfoBean>>() {
            @Override
            public void onResponse(Call<List<UserInfoBean>> call, Response<List<UserInfoBean>> response) {
                friendsListLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<UserInfoBean>> call, Throwable throwable) {
                friendsListLiveData.setValue(null);
            }
        });
    }

    /**
     * 添加好友
     */
    public void addFriend(String userId, String friendUserId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("friendUserId", friendUserId);
        HttpUtil.restAPI.addFriend(params).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                saveBeanLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable throwable) {
                saveBeanLiveData.setValue(null);
            }
        });
    }


}