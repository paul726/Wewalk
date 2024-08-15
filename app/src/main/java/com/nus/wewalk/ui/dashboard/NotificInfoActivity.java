package com.nus.wewalk.ui.dashboard;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.nus.wewalk.R;
import com.nus.wewalk.databinding.ActivityNotificBinding;
import com.nus.wewalk.databinding.ActivityNotificInfoBinding;
import com.nus.wewalk.net.ApiResponse;
import com.nus.wewalk.ui.me.UserInfoBean;

import java.util.List;

/**
 * Notifications
 */
public class NotificInfoActivity extends AppCompatActivity {

    private ActivityNotificInfoBinding binding;
    private DashboardViewModel dashboardViewModel;
    private String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        initView();
    }

    private void initView() {
        binding.top.ivBack.setOnClickListener(v -> finish());
        binding.top.tvTitle.setText("Notifications");
        id = getIntent().getStringExtra("id");

        dashboardViewModel.getNoticeInfo(id);

        dashboardViewModel.notificationIndoData.observe(this, new Observer<Notification>() {
            @Override
            public void onChanged(Notification notification) {
                if (notification != null) {
                    binding.tvNotificationMessage.setText(notification.getNoticeContent());
                    // Set icon based on notification type
                    if (TextUtils.equals("1", notification.getNoticeType())) {
                        binding.ivNotificationIcon.setImageResource(R.mipmap.ic_rctx);
                        binding.tvNotificationTitle.setText("Daily reminders");
                        binding.reBg.setBackgroundResource(R.drawable.bg_lead_loe);
                    } else {
                        binding.ivNotificationIcon.setImageResource(R.mipmap.ic_xitsxx);
                        binding.tvNotificationTitle.setText("System notifications");
                        binding.reBg.setBackgroundResource(R.drawable.bg_orange_loe);
                    }
                }
            }
        });
    }

}
