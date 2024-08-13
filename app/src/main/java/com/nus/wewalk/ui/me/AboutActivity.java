package com.nus.wewalk.ui.me;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nus.wewalk.databinding.ActivityAboutBinding;
import com.nus.wewalk.databinding.ActivityPwdBinding;

/**
 * About
 */
public class AboutActivity extends AppCompatActivity {


    private ActivityAboutBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        binding.top.ivBack.setOnClickListener(v -> finish());
        binding.top.tvTitle.setText("About");
    }


}
