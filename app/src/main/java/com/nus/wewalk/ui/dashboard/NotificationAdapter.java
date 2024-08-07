package com.nus.wewalk.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nus.wewalk.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private List<Notification> notifications = new ArrayList<>();

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {
        Notification notification = notifications.get(position);
        holder.tvTitle.setText(notification.getTitle());
        holder.tvMessage.setText(notification.getMessage());

        // Set icon based on notification type
        int iconResId;
        switch (notification.getType()) {
            case STEP_REMINDER:
                iconResId = R.drawable.ic_step_reminder;
                break;
            case ACHIEVEMENT:
                iconResId = R.drawable.ic_achievement;
                break;
            case FRIEND_ACTIVITY:
                iconResId = R.drawable.ic_friend_activity;
                break;
            case SYSTEM_UPDATE:
                iconResId = R.drawable.ic_system_update;
                break;
            case CHALLENGE_INVITATION:
                iconResId = R.drawable.ic_challenge_invitation;
                break;
            default:
                iconResId = R.drawable.ic_notification_default;
        }
        holder.ivIcon.setImageResource(iconResId);
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
        notifyDataSetChanged();
    }

    class NotificationViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        TextView tvTitle, tvMessage;

        NotificationViewHolder(View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.ivNotificationIcon);
            tvTitle = itemView.findViewById(R.id.tvNotificationTitle);
            tvMessage = itemView.findViewById(R.id.tvNotificationMessage);
        }
    }
}
