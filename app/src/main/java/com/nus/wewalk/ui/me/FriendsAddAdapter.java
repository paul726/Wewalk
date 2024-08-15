package com.nus.wewalk.ui.me;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nus.wewalk.R;

import java.util.List;

/**
 * Friends
 */
public class FriendsAddAdapter extends RecyclerView.Adapter<FriendsAddAdapter.ViewHolder> {

    private List<UserInfoBean> dataList;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public FriendsAddAdapter(Context mContext, List<UserInfoBean> dataList) {
        this.dataList = dataList;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_friends_add, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        UserInfoBean userInfoBean = dataList.get(position);
        holder.mTvName.setText(userInfoBean.getUserName());
        if (!TextUtils.isEmpty(userInfoBean.getAvatar())) {
            Glide.with(mContext).load(userInfoBean.getAvatar())
                    .circleCrop().placeholder(R.mipmap.ic_head).into(holder.mIcHead);
        }else {
            holder.mIcHead.setImageResource(R.mipmap.ic_head);
        }
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onSuccess(userInfoBean, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mIcHead;
        TextView mTvName;

        ViewHolder(View itemView) {
            super(itemView);
            this.mIcHead = itemView.findViewById(R.id.ic_head);
            this.mTvName = itemView.findViewById(R.id.tv_name);
        }
    }

    public interface OnItemClickListener {
        void onSuccess(UserInfoBean data, int pos);
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
