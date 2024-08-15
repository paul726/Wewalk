package com.nus.wewalk.net;

import com.nus.wewalk.ui.challenges.RankBean;
import com.nus.wewalk.ui.dashboard.NoticeNewBean;
import com.nus.wewalk.ui.dashboard.Notification;
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

    @POST("login2")
    Call<ApiResponse<LoginBean>> login(@Body Map<String, Object> params);

    @POST("register")
    Call<ApiResponse> register(@Body Map<String, Object> params);


    @GET("system/user/profile")
    Call<ApiResponse<UserInfoBean>> getUserInfo(@Query("userId") String userId);

    @POST("history/updateProfile")
    Call<ApiResponse> saveUserInfo(@Body Map<String, Object> params);

    @POST("logout")
    Call<ApiResponse> logout(@Body Map<String, Object> params);


    @FormUrlEncoded
    @PUT("system/user/profile/updatePwd")
    Call<ApiResponse> updatePwd(@Field("oldPassword") String oldPassword, @Field("newPassword") String newPassword,@Query("userId") String userId);


    @GET("history/getFriends")
    Call<ApiResponse<List<UserInfoBean>>> getFriends(@Query("userId") String userId, @Query("userName") String userName);


    @POST("history/addFriend")
    Call<ApiResponse> addFriend(@Body Map<String, Object> params);



    @GET("history/getFriendStepRank")
    Call<ApiResponse<List<RankBean>>> getFriendStepRank(@Query("userId") String userId);


    @GET("system/notice/list")
    Call<ApiResponse<List<Notification>>> noticeList(@Query("pageSize") int pageSize, @Query("pageSize") int pageNum);


    @GET("system/notice/count")
    Call<ApiResponse<NoticeNewBean>> countNum(@Query("userId") String userId);

    @POST("history/getStepData")
    Call<ApiResponse> getStepData(@Body Map<String, Object> params);

    @GET(" system/notice/getInfo")
    Call<ApiResponse<Notification>> getNoticInfo(@Query("userId") String userId,@Query("noticeId") String noticeId);



}
