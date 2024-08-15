package com.nus.wewalk.ui.challenges;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nus.wewalk.net.ApiResponse;
import com.nus.wewalk.net.HttpUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChallengesViewModel extends ViewModel {

    MutableLiveData<List<RankBean>> rankListLiveData = new MutableLiveData<>();

    /**
     * 查询好友步数排行榜
     */
    public void getFriendStepRank(String userId) {
        HttpUtil.restAPI.getFriendStepRank(userId).enqueue(new Callback<ApiResponse<List<RankBean>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<RankBean>>> call, Response<ApiResponse<List<RankBean>>> response) {
                if (response.isSuccessful()) {
                    rankListLiveData.setValue(response.body().getData());
                }
            }
            @Override
            public void onFailure(Call<ApiResponse<List<RankBean>>> call, Throwable throwable) {
                rankListLiveData.setValue(null);
            }
        });
    }
}