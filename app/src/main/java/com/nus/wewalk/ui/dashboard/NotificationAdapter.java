package com.nus.wewalk.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nus.wewalk.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private List<Notification> notifications;
    private Context context;

    public NotificationAdapter(Context context, List<Notification> notifications) {
        this.notifications = notifications;
        this.context = context;
    }

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notification, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {
        Notification notification = notifications.get(position);
        holder.tvMessage.setText(notification.getNoticeContent());
        // Set icon based on notification type
        if (TextUtils.equals("1", notification.getNoticeType())) {
            holder.ivIcon.setImageResource(R.mipmap.ic_rctx);
            holder.tvTitle.setText("Daily reminders");
            holder.mReBg.setBackgroundResource(R.drawable.bg_lead_loe);
        } else {
            holder.ivIcon.setImageResource(R.mipmap.ic_xitsxx);
            holder.tvTitle.setText("System notifications");
            holder.mReBg.setBackgroundResource(R.drawable.bg_orange_loe);
        }
        holder.itemView.setOnClickListener(v -> {
            context.startActivity(new Intent(context, NotificInfoActivity.class).putExtra("id", notification.getNoticeId()+""));
        });
    }

    @Override
    public int getItemCount() {
        return notifications == null ? 0 : notifications.size();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        TextView tvTitle, tvMessage;
        ImageView mIvRead;
        RelativeLayout mReBg;

        NotificationViewHolder(View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.ivNotificationIcon);
            tvTitle = itemView.findViewById(R.id.tvNotificationTitle);
            tvMessage = itemView.findViewById(R.id.tvNotificationMessage);
            this.mIvRead = itemView.findViewById(R.id.iv_read);
            this.mReBg = itemView.findViewById(R.id.re_bg);
        }
    }
}
