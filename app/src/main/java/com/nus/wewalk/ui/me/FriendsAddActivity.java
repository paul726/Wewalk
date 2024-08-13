package com.nus.wewalk.ui.me;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.nus.wewalk.databinding.ActivityFriendsBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Add friends
 */
public class FriendsAddActivity extends AppCompatActivity {

    private ActivityFriendsBinding binding;
    private FriendsAddAdapter friendsAddAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFriendsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
        setupRecyclerView();
    }
    private void initView() {
        binding.top.ivBack.setOnClickListener(v -> finish());
        binding.top.tvTitle.setText("Add friends");
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
        friendsAddAdapter = new FriendsAddAdapter(this, list);
        binding.recycle.setLayoutManager(new LinearLayoutManager(this));
        binding.recycle.setAdapter(friendsAddAdapter);
    }


}
