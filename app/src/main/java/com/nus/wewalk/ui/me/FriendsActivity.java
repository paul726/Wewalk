package com.nus.wewalk.ui.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.nus.wewalk.R;
import com.nus.wewalk.databinding.ActivityFriendsBinding;
import com.nus.wewalk.utilities.XShareCacheUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Friends
 */
public class FriendsActivity extends AppCompatActivity {

    private ActivityFriendsBinding binding;
    private FriendsListAdapter friendsListAdapter;
    private MineViewModel mineViewModel;
    private List<UserInfoBean> beanList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFriendsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mineViewModel = new ViewModelProvider(this).get(MineViewModel.class);
        initView();
    }

    private void initView() {
        binding.top.ivBack.setOnClickListener(v -> finish());
        binding.top.tvTitle.setText("Friends");
        binding.top.ivRight.setImageResource(R.mipmap.ic_add);
        binding.recycle.setLayoutManager(new LinearLayoutManager(this));


        binding.top.ivRight.setOnClickListener(v -> {
            startActivity(new Intent(this, FriendsAddActivity.class));
        });
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        String uid = XShareCacheUtils.getInstance().getString("uid");
        mineViewModel.getFriends(uid, "");

        mineViewModel.friendsListLiveData.observe(this, new Observer<List<UserInfoBean>>() {
            @Override
            public void onChanged(List<UserInfoBean> userInfoBeans) {
                beanList = userInfoBeans;
                friendsListAdapter = new FriendsListAdapter(FriendsActivity.this, userInfoBeans);
                binding.recycle.setAdapter(friendsListAdapter);
            }
        });


        binding.ivSearch.setOnClickListener(v -> {

            if (binding.etSearch.getText().toString().isEmpty()) {
                Toast.makeText(getBaseContext(), "Please enter friend information", Toast.LENGTH_SHORT).show();
                return;
            }
            if (beanList != null && !beanList.isEmpty()) {
                List<UserInfoBean> list = new ArrayList<>();
                for (int i = 0; i < beanList.size(); i++) {
                    if (beanList.get(i).getUserName().contains(binding.etSearch.getText().toString()) ||
                            beanList.get(i).getNickName().contains(binding.etSearch.getText().toString())) {
                        list.add(beanList.get(i));
                    }
                }
                if (!list.isEmpty()) {
                    Toast.makeText(getBaseContext(), "No information available at the moment", Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(getBaseContext(), "No information available at the moment", Toast.LENGTH_SHORT).show();
            }

        });

    }

}
