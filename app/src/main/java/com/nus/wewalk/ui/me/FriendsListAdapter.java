package com.nus.wewalk.ui.me;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nus.wewalk.R;

import java.util.List;

/**
 * Friends
 */
public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.ViewHolder> {

    private List<String> dataList;
    private Context mContext;

    public FriendsListAdapter(Context mContext, List<String> dataList) {
        this.dataList = dataList;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_friends_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
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
}
