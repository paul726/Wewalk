package com.nus.wewalk.ui.dashboard;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nus.wewalk.net.HttpUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardViewModel extends ViewModel {

    MutableLiveData<List<Notification>> notifications = new MutableLiveData<>();
    MutableLiveData<String> countNum = new MutableLiveData<>();

    /**
     * 通知列表
     * @param page
     */
    public void getNotific(int page) {
        HttpUtil.restAPI.noticeList(page, 20).enqueue(new Callback<List<Notification>>() {
            @Override
            public void onResponse(Call<List<Notification>> call, Response<List<Notification>> response) {
                notifications.setValue(response.body());
            }
            @Override
            public void onFailure(Call<List<Notification>> call, Throwable throwable) {
                notifications.setValue(null);
            }
        });
    }

    /**
     * 通知数量
     */
    public void countNum() {
        HttpUtil.restAPI.countNum().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                countNum.setValue(response.body());
            }
            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                countNum.setValue(null);
            }
        });
    }

}