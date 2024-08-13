package com.nus.wewalk.ui.me;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nus.wewalk.databinding.ActivitySupportBinding;

/**
 * Support
 */
public class SupportActivity extends AppCompatActivity {

    private ActivitySupportBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySupportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        binding.top.re.setBackgroundColor(Color.parseColor("#61CBAB"));
        binding.top.ivBack.setOnClickListener(v -> finish());
        binding.top.tvTitle.setText("support");
        binding.top.tvTitle.setTextColor(Color.parseColor("#ffffff"));
    }
}
