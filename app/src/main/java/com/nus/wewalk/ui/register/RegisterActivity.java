package com.nus.wewalk.ui.register;

import static com.nus.wewalk.utilities.Utils.hideStatusBar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.nus.wewalk.databinding.ActivityRegisterBinding;
import com.nus.wewalk.net.ApiResponse;
import com.nus.wewalk.ui.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private RegisterViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        hideStatusBar(this);

        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        binding.tvToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                // clear LoginActivity activity instance if there is LoginActivity in activity stack
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.etUsername.getText().toString();
                String password = binding.etPassword.getText().toString();
                String confirmPs = binding.etConfirmPassword.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getBaseContext(), "please input username and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(confirmPs)) {
                    Toast.makeText(getBaseContext(), "Please ensure password if correct ", Toast.LENGTH_SHORT).show();
                    return;
                }
                viewModel.register(username, password);
            }
        });

        viewModel.liveData.observe(this, new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse isSuccessful) {
                if (isSuccessful.getCode() == 200) {
                    Toast.makeText(RegisterActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, isSuccessful.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
