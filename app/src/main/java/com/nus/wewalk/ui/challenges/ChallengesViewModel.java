package com.nus.wewalk.ui.challenges;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nus.wewalk.net.HttpUtil;
import com.nus.wewalk.ui.me.UserInfoBean;

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
        HttpUtil.restAPI.getFriendStepRank(userId).enqueue(new Callback<List<RankBean>>() {
            @Override
            public void onResponse(Call<List<RankBean>> call, Response<List<RankBean>> response) {
                rankListLiveData.setValue(response.body());
            }
            @Override
            public void onFailure(Call<List<RankBean>> call, Throwable throwable) {
                rankListLiveData.setValue(null);
            }
        });
    }
}