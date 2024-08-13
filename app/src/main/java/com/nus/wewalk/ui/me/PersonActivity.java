package com.nus.wewalk.ui.me;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nus.wewalk.databinding.ActivityPersonBinding;

/**
 * Personal
 */
public class PersonActivity extends AppCompatActivity {
    private ActivityPersonBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPersonBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        binding.top.ivBack.setOnClickListener(v -> finish());
        binding.top.tvTitle.setText("Personal");
    }


}
