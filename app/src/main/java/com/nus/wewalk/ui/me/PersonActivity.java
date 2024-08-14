package com.nus.wewalk.ui.me;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.nus.wewalk.R;
import com.nus.wewalk.databinding.ActivityPersonBinding;
import com.nus.wewalk.net.ApiResponse;
import com.nus.wewalk.ui.register.RegisterActivity;
import com.nus.wewalk.utilities.XShareCacheUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Personal
 */
public class PersonActivity extends AppCompatActivity {
    private ActivityPersonBinding binding;
    private MineViewModel mineViewModel;
    private String sex = "1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPersonBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mineViewModel = new ViewModelProvider(this).get(MineViewModel.class);
        initView();
    }

    private void initView() {
        binding.top.ivBack.setOnClickListener(v -> finish());
        binding.top.tvTitle.setText("Personal");


        //请求数据
        mineViewModel.getUserInfo();
        //数据展示
        mineViewModel.userInfoBeanLiveData.observe(this, new Observer<UserInfoBean>() {
            @Override
            public void onChanged(UserInfoBean loginBean) {
                binding.tvNickName.setText(loginBean.getUserName());
                binding.etAde.setText(loginBean.getAge() + "");
                binding.etSg.setText(loginBean.getHeight());
                binding.etTz.setText(loginBean.getWeight());
                //性别：1-男 2-女
                if (loginBean.getSex() == 1) {
                    binding.tvNan.setBackgroundResource(R.drawable.bg_green_write_line);
                    binding.tvNv.setBackgroundResource(R.drawable.bg_gray_shen_line_five);
                } else if (loginBean.getSex() == 2) {
                    binding.tvNv.setBackgroundResource(R.drawable.bg_green_write_line);
                    binding.tvNan.setBackgroundResource(R.drawable.bg_gray_shen_line_five);
                } else {
                    binding.tvNan.setBackgroundResource(R.drawable.bg_gray_shen_line_five);
                    binding.tvNv.setBackgroundResource(R.drawable.bg_gray_shen_line_five);
                }
            }
        });

        binding.btSave.setOnClickListener(v -> {
            String uid = XShareCacheUtils.getInstance().getString("uid");
            Map<String, Object> params = new HashMap<>();
            params.put("userId", uid);
            params.put("sex", sex);
            params.put("status", 0);
            params.put("age", binding.etAde.getText().toString());
            params.put("height", binding.etSg.getText().toString());
            params.put("weight", binding.etTz.getText().toString());
            mineViewModel.saveUserInfo(params);
        });

        binding.tvNan.setOnClickListener(v -> {
            sex = "1";
            binding.tvNan.setBackgroundResource(R.drawable.bg_green_write_line);
            binding.tvNv.setBackgroundResource(R.drawable.bg_gray_shen_line_five);
        });
        binding.tvNv.setOnClickListener(v -> {
            sex = "2";
            binding.tvNv.setBackgroundResource(R.drawable.bg_green_write_line);
            binding.tvNan.setBackgroundResource(R.drawable.bg_gray_shen_line_five);
        });

        mineViewModel.saveBeanLiveData.observe(this, new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {
                if (apiResponse.getCode() == 200) {
                    Toast.makeText(PersonActivity.this, "Successfully saved!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(PersonActivity.this, "Save failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
