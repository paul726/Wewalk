package com.nus.wewalk.ui.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nus.wewalk.R;
import com.nus.wewalk.databinding.ActivitySetBinding;
import com.nus.wewalk.ui.login.LoginActivity;

/**
 * set
 */
public class SetActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivitySetBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }
    private void initView() {
        binding.top.ivBack.setOnClickListener(v -> finish());
        binding.top.tvTitle.setText("Setting");

        binding.btAbout.setOnClickListener(this);
        binding.btOut.setOnClickListener(this);
        binding.btPwd.setOnClickListener(this);
        binding.btYs.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_out) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else if (v.getId() == R.id.bt_pwd) {
            //password
            startActivity(new Intent(this, PwdActivity.class));
        } else if (v.getId() == R.id.bt_about) {
            //About
            startActivity(new Intent(this, AboutActivity.class));
        } else if (v.getId() == R.id.bt_ys) {
            //Privacy settings
            startActivity(new Intent(this, PrivacyActivity.class));
        }
    }
}
