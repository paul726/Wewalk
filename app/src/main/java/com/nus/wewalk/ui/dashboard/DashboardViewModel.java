package com.nus.wewalk.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DashboardViewModel extends ViewModel {

    MutableLiveData<Integer> stepCount = new MutableLiveData<>();
    MutableLiveData<Integer> calories = new MutableLiveData<>();
    MutableLiveData<Double> distance = new MutableLiveData<>();
    MutableLiveData<List<Notification>> notifications = new MutableLiveData<>();
    private final int dailyStepGoal = 10000;

    public int getDailyStepGoal() {
        return dailyStepGoal;
    }

    public void loadDashboardData() {
        // Mock data generation
        Random random = new Random();
        int steps = random.nextInt(dailyStepGoal);
        stepCount.setValue(steps);
        calories.setValue((int)(steps * 0.04)); // Assuming 0.04 calories per step
        distance.setValue(steps * 0.0008); // Assuming 0.8 meters per step

        List<Notification> notificationList = Arrays.asList(
                new Notification(Notification.Type.STEP_REMINDER, "Almost there!", "You're " + (dailyStepGoal - steps) + " steps away from your daily goal."),
                new Notification(Notification.Type.ACHIEVEMENT, "New Badge Earned", "Congratulations! You've earned the 'Early Bird' badge."),
                new Notification(Notification.Type.FRIEND_ACTIVITY, "John completed a challenge", "Your friend John just completed the 'Weekend Warrior' challenge."),
                new Notification(Notification.Type.SYSTEM_UPDATE, "App Update Available", "A new version of WeWalk is available with exciting new features!"),
                new Notification(Notification.Type.CHALLENGE_INVITATION, "New Challenge", "Join the 'Step Up September' challenge and compete with friends!")
        );
        notifications.setValue(notificationList);
    }
}