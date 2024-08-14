package com.nus.wewalk;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nus.wewalk.ui.login.LoginActivity;
import com.nus.wewalk.utilities.UserInstance;
import com.nus.wewalk.utilities.XShareCacheUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        String token = XShareCacheUtils.getInstance().getString("token");
        if (TextUtils.isEmpty(token)) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            // go to main page
            startActivity(new Intent(this, MainActivity.class));
        }
        finish();
    }

}
