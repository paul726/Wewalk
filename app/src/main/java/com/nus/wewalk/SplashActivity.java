package com.nus.wewalk;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nus.wewalk.ui.login.LoginActivity;
import com.nus.wewalk.utilities.UserInstance;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences sharepref = getSharedPreferences("user", MODE_PRIVATE);
        String token = sharepref.getString("token", null);
        if (token == null) {
            // go to login page
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            // go to main page
            UserInstance.username = sharepref.getString("username", "");
            UserInstance.token = token;
            startActivity(new Intent(this, MainActivity.class));
        }

        finish();
    }
}
