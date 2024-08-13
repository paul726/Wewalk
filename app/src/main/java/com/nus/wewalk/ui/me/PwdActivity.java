package com.nus.wewalk.ui.me;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nus.wewalk.databinding.ActivityPersonBinding;
import com.nus.wewalk.databinding.ActivityPwdBinding;

/**
 * Change password
 */
public class PwdActivity extends AppCompatActivity {


    private ActivityPwdBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPwdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        binding.top.ivBack.setOnClickListener(v -> finish());
        binding.top.tvTitle.setText("Change password");
    }


}
