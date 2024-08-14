package com.nus.wewalk.ui.me;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.nus.wewalk.R;
import com.nus.wewalk.databinding.ActivityPwdBinding;
import com.nus.wewalk.net.ApiResponse;

/**
 * Change password
 */
public class PwdActivity extends AppCompatActivity {


    private ActivityPwdBinding binding;
    private MineViewModel mineViewModel;
    private boolean isShow = true, isShow1 = true, isShow2 = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPwdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mineViewModel = new ViewModelProvider(this).get(MineViewModel.class);
        initView();
    }

    private void initView() {
        binding.top.ivBack.setOnClickListener(v -> finish());
        binding.top.tvTitle.setText("Change password");


        binding.btSave.setOnClickListener(view -> {
            String etOldPwd = binding.etOldPwd.getText().toString();
            String etNewPwd = binding.etNewPwd.getText().toString();
            String etSurePwd = binding.etSure.getText().toString();
            if (etOldPwd.trim().isEmpty()) {
                Toast.makeText(getBaseContext(), "please input old password", Toast.LENGTH_SHORT).show();
                return;
            }
            if (etNewPwd.trim().isEmpty()) {
                Toast.makeText(getBaseContext(), "please input new password", Toast.LENGTH_SHORT).show();
                return;
            }
            if (etSurePwd.trim().isEmpty()) {
                Toast.makeText(getBaseContext(), "please Confirm password", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!TextUtils.equals(etNewPwd.trim(), etSurePwd.trim())) {
                Toast.makeText(getBaseContext(), "The two passwords are different", Toast.LENGTH_SHORT).show();
                return;
            }
            mineViewModel.updatePwd(etOldPwd, etNewPwd);
        });

        mineViewModel.saveBeanLiveData.observe(this, new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {
                if (apiResponse.getCode() == 200) {
                    Toast.makeText(PwdActivity.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(PwdActivity.this, apiResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.iv1.setOnClickListener(v -> {
            //展示密码
            if (isShow) {
                binding.iv1.setImageResource(R.mipmap.ic_pwd_open);
                binding.etOldPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                binding.iv1.setImageResource(R.mipmap.ic_pwd_close);
                binding.etOldPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            isShow = !isShow;
        });

        binding.iv2.setOnClickListener(v -> {
            //展示密码
            if (isShow1) {
                binding.iv2.setImageResource(R.mipmap.ic_pwd_open);
                binding.etOldPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                binding.iv2.setImageResource(R.mipmap.ic_pwd_close);
                binding.etOldPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            isShow1 = !isShow1;
        });

        binding.iv3.setOnClickListener(v -> {
            //展示密码
            if (isShow2) {
                binding.iv3.setImageResource(R.mipmap.ic_pwd_open);
                binding.etSure.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                binding.iv3.setImageResource(R.mipmap.ic_pwd_close);
                binding.etSure.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            isShow2 = !isShow2;
        });
    }


}
