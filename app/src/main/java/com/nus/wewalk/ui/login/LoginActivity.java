package com.nus.wewalk.ui.login;

import static com.nus.wewalk.utilities.Utils.hideStatusBar;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.nus.wewalk.MainActivity;
import com.nus.wewalk.databinding.ActivityLoginBinding;
import com.nus.wewalk.ui.login.data.LoginBean;
import com.nus.wewalk.ui.register.RegisterActivity;
import com.nus.wewalk.utilities.UserInstance;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    private LoginViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        hideStatusBar(this);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.etUsername.getText().toString();
                String password = binding.etPassword.getText().toString();
                if (username.trim().isEmpty() || password.trim().isEmpty()) {
                    Toast.makeText(getBaseContext(), "please input email and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                viewModel.login(username, password);
            }
        });

        binding.tvToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        viewModel.loginBeanLiveData.observe(this, new Observer<LoginBean>() {
            @Override
            public void onChanged(LoginBean loginBean) {
                UserInstance.username = binding.etUsername.getText().toString();
                UserInstance.token = loginBean.getAccessToken();

                SharedPreferences sharedPref = getSharedPreferences("user", MODE_PRIVATE);
                sharedPref.edit()
                        .putString("username", UserInstance.username)
                        .putString("token", UserInstance.token)
                        .apply();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
