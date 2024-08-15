package com.nus.wewalk.ui.dashboard;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.nus.wewalk.R;
import com.nus.wewalk.databinding.ActivityNotificBinding;
import com.nus.wewalk.ui.me.UserInfoBean;
import com.nus.wewalk.utilities.XShareCacheUtils;

import java.util.List;

/**
 * Notifications
 */
public class NotificActivity extends AppCompatActivity {

    private ActivityNotificBinding binding;
    private NotificationAdapter friendsListAdapter;
    private DashboardViewModel dashboardViewModel;
    private List<UserInfoBean> beanList;
    private int page = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        initView();
    }

    private void initView() {
        binding.top.ivBack.setOnClickListener(v -> finish());
        binding.top.tvTitle.setText("Notifications");
        binding.recycle.setLayoutManager(new LinearLayoutManager(this));
        setupRecyclerView();
    }

    private void setupRecyclerView() {

        dashboardViewModel.getNoticeList(page);

        dashboardViewModel.notifications.observe(this, new Observer<List<Notification>>() {
            @Override
            public void onChanged(List<Notification> notifications) {
                NotificationAdapter notificationAdapter = new NotificationAdapter(NotificActivity.this, notifications);
                binding.recycle.setAdapter(notificationAdapter);
            }
        });
    }

}
