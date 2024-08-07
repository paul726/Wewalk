package com.nus.wewalk.ui.dashboard;

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

import com.nus.wewalk.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private DashboardViewModel dashboardViewModel;

    private NotificationAdapter notificationAdapter;

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
        setupRecyclerView();
        observeViewModel();
    }

    private void setupRecyclerView() {
        notificationAdapter = new NotificationAdapter();
        binding.rvNotifications.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvNotifications.setAdapter(notificationAdapter);
    }

    private void observeViewModel() {
        dashboardViewModel.stepCount.observe(getViewLifecycleOwner(), steps -> {
            binding.tvStepCount.setText(String.valueOf(steps));
            binding.progressSteps.setProgress(steps);
            binding.progressSteps.setMax(dashboardViewModel.getDailyStepGoal());
        });

        dashboardViewModel.calories.observe(getViewLifecycleOwner(), calories -> {
            binding.tvCalories.setText("Calories: " + calories + " kcal");
        });

        dashboardViewModel.distance.observe(getViewLifecycleOwner(), distance -> {
            binding.tvDistance.setText("Distance: " + String.format("%.2f", distance) + " km");
        });

        dashboardViewModel.notifications.observe(getViewLifecycleOwner(), notifications -> {
            notificationAdapter.setNotifications(notifications);
        });

        dashboardViewModel.loadDashboardData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}