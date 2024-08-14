package com.nus.wewalk.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.nus.wewalk.R;
import com.nus.wewalk.databinding.FragmentDashboardBinding;
import com.nus.wewalk.utilities.XShareCacheUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DashboardFragment extends Fragment implements View.OnClickListener {

    private FragmentDashboardBinding binding;
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnMore.setOnClickListener(this);

        String name = XShareCacheUtils.getInstance().getString("name");
        binding.tvName.setText("Hi~" + name);
        binding.tvDate.setText(getDate());
        //同时数量
        dashboardViewModel.countNum();
        dashboardViewModel.countNum.observe(getActivity(), s -> {
            binding.tvNotification.setText("Notification " + s);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_more) {
            startActivity(new Intent(getActivity(), NotificActivity.class));
        }
    }

    /**
     * 获取时间
     */
    private String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);
        Date currentDate = new Date();
        return sdf.format(currentDate);
    }
}