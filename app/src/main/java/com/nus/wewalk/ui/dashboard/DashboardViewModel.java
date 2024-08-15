package com.nus.wewalk.ui.dashboard;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nus.wewalk.net.ApiResponse;
import com.nus.wewalk.net.HttpUtil;
import com.nus.wewalk.utilities.XShareCacheUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardViewModel extends ViewModel {

    MutableLiveData<List<Notification>> notifications = new MutableLiveData<>();
    MutableLiveData<NoticeNewBean> countNum = new MutableLiveData<>();
    MutableLiveData<Notification> notificationIndoData = new MutableLiveData<>();

    /**
     * 通知列表
     *
     * @param page
     */
    public void getNoticeList(int page) {
        HttpUtil.restAPI.noticeList(page, 20).enqueue(new Callback<ApiResponse<List<Notification>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Notification>>> call, Response<ApiResponse<List<Notification>>> response) {
                if (response.isSuccessful()) {
                    notifications.setValue(response.body().getRows());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Notification>>> call, Throwable throwable) {
                notifications.setValue(null);
            }
        });
    }

    /**
     * 通知数量
     */
    public void countNum() {
        String uid = XShareCacheUtils.getInstance().getString("uid");
        HttpUtil.restAPI.countNum(uid).enqueue(new Callback<ApiResponse<NoticeNewBean>>() {
            @Override
            public void onResponse(Call<ApiResponse<NoticeNewBean>> call, Response<ApiResponse<NoticeNewBean>> response) {
                if (response.isSuccessful()) {
                    countNum.setValue(response.body().getData());
                }
            }
            @Override
            public void onFailure(Call<ApiResponse<NoticeNewBean>> call, Throwable throwable) {
                countNum.setValue(null);
            }
        });
    }

    /**
     * 通知详情
     */
    public void getNoticeInfo(String noticeId) {
        String uid = XShareCacheUtils.getInstance().getString("uid");
        HttpUtil.restAPI.getNoticInfo(uid, noticeId).enqueue(new Callback<ApiResponse<Notification>>() {
            @Override
            public void onResponse(Call<ApiResponse<Notification>> call, Response<ApiResponse<Notification>> response) {
                if (response.isSuccessful()) {
                    notificationIndoData.setValue(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Notification>> call, Throwable throwable) {
                notificationIndoData.setValue(null);
            }

        });
    }


}