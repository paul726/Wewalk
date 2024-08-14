package com.nus.wewalk.ui.me;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.nus.wewalk.databinding.ActivityFriendsBinding;
import com.nus.wewalk.net.ApiResponse;
import com.nus.wewalk.utilities.XShareCacheUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Add friends
 */
public class FriendsAddActivity extends AppCompatActivity {

    private ActivityFriendsBinding binding;
    private FriendsAddAdapter friendsAddAdapter;
    private MineViewModel mineViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFriendsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mineViewModel = new ViewModelProvider(this).get(MineViewModel.class);
        initView();
        setupRecyclerView();
    }

    private void initView() {
        binding.top.ivBack.setOnClickListener(v -> finish());
        binding.top.tvTitle.setText("Add friends");
        binding.recycle.setLayoutManager(new LinearLayoutManager(this));

        binding.ivSearch.setOnClickListener(v -> {
            String uid = XShareCacheUtils.getInstance().getString("uid");

            if (binding.etSearch.getText().toString().isEmpty()) {
                Toast.makeText(getBaseContext(), "Please enter the friend information to be queried", Toast.LENGTH_SHORT).show();
                return;
            }

            mineViewModel.getFriends(uid, binding.etSearch.getText().toString());
        });
    }

    private void setupRecyclerView() {


        mineViewModel.friendsListLiveData.observe(this, new Observer<List<UserInfoBean>>() {
            @Override
            public void onChanged(List<UserInfoBean> userInfoBeans) {

                friendsAddAdapter = new FriendsAddAdapter(FriendsAddActivity.this, userInfoBeans);
                binding.recycle.setAdapter(friendsAddAdapter);
                friendsAddAdapter.setOnItemClickListener((data1, pos) -> {
                    String uid = XShareCacheUtils.getInstance().getString("uid");
                    mineViewModel.addFriend(uid, data1.getUserId());
                });
            }
        });

        mineViewModel.saveBeanLiveData.observe(this, new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {
                if (apiResponse.getCode() == 200) {
                    Toast.makeText(FriendsAddActivity.this, "Added friend successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(FriendsAddActivity.this, apiResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

}
