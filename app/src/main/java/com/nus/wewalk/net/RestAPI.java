package com.nus.wewalk.net;

import com.nus.wewalk.ui.challenges.RankBean;
import com.nus.wewalk.ui.login.data.LoginBean;
import com.nus.wewalk.ui.me.UserInfoBean;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface RestAPI {

    @POST("api/login2")
    Call<LoginBean> login(@Body Map<String, Object> params);

    @POST("api/register")
    Call<Boolean> register(@Body Map<String, Object> params);


    @GET("system/user/profile")
    Call<UserInfoBean> getUserInfo();

    @POST("history/updateProfile")
    Call<ApiResponse> saveUserInfo(@Body Map<String, Object> params);

    @POST("logout")
    Call<ApiResponse> logout(@Body Map<String, Object> params);



    @FormUrlEncoded
    @PUT("system/user/profile/updatePwd")
    Call<ApiResponse> updatePwd(@Field("oldPassword") String oldPassword, @Field("newPassword") String newPassword);



    @GET("history/getFriends")
    Call<List<UserInfoBean>> getFriends(@Query("userId") String userId, @Query("userName") String userName);


    @POST("history/addFriend")
    Call<ApiResponse> addFriend(@Body Map<String, Object> params);


    @GET("history/getFriendStepRank")
    Call<List<RankBean>> getFriendStepRank(@Query("userId") String userId);



}
