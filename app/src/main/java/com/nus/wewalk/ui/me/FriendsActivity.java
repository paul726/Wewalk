package com.nus.wewalk.ui.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.nus.wewalk.R;
import com.nus.wewalk.databinding.ActivityFriendsBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Friends
 */
public class FriendsActivity extends AppCompatActivity {

    private ActivityFriendsBinding binding;
    private FriendsListAdapter friendsListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFriendsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        binding.top.ivBack.setOnClickListener(v -> finish());
        binding.top.tvTitle.setText("Friends");
        binding.top.ivRight.setImageResource(R.mipmap.ic_add);
        binding.top.ivRight.setOnClickListener(v -> {
            startActivity(new Intent(this, FriendsAddActivity.class));
        });
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        friendsListAdapter = new FriendsListAdapter(this, list);
        binding.recycle.setLayoutManager(new LinearLayoutManager(this));
        binding.recycle.setAdapter(friendsListAdapter);
    }

}
