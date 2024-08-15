package com.nus.wewalk.ui.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.nus.wewalk.R;
import com.nus.wewalk.databinding.ActivitySetBinding;
import com.nus.wewalk.net.ApiResponse;
import com.nus.wewalk.ui.login.LoginActivity;
import com.nus.wewalk.utilities.XShareCacheUtils;

/**
 * set
 */
public class SetActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivitySetBinding binding;
    private MineViewModel mineViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mineViewModel = new ViewModelProvider(this).get(MineViewModel.class);
        initView();
    }

    private void initView() {
        binding.top.ivBack.setOnClickListener(v -> finish());
        binding.top.tvTitle.setText("Setting");

        binding.btAbout.setOnClickListener(this);
        binding.btOut.setOnClickListener(this);
        binding.btPwd.setOnClickListener(this);
        binding.btYs.setOnClickListener(this);

        mineViewModel.saveBeanLiveData.observe(this, new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {
                XShareCacheUtils.getInstance().remove("token");
                XShareCacheUtils.getInstance().remove("uid");
                startActivity(new Intent(SetActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_out) {
            String uid = XShareCacheUtils.getInstance().getString("uid");
            mineViewModel.logout(uid);
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
