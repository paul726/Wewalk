package com.nus.wewalk.ui.login.data;

import com.google.gson.annotations.SerializedName;

public class LoginBean {

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("expires_in")
    private int expiresIn;

}
