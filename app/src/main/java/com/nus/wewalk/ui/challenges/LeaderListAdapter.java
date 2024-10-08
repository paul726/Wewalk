package com.nus.wewalk.ui.challenges;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nus.wewalk.R;

import java.util.List;

/**
 * Today's step count
 */
public class LeaderListAdapter extends RecyclerView.Adapter<LeaderListAdapter.ViewHolder> {

    private List<RankBean> dataList;
    private Context mContext;

    public LeaderListAdapter(Context mContext, List<RankBean> dataList) {
        this.dataList = dataList;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_lead_code, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RankBean rankBean = dataList.get(position);

        holder.mTvNum.setText(rankBean.getOrder());
        holder.mTvName.setText(rankBean.getUserName());
        if (!TextUtils.isEmpty(rankBean.getAvatar())) {
            Glide.with(mContext).load(rankBean.getAvatar())
                    .circleCrop().placeholder(R.mipmap.ic_head).into(holder.mIcHead);
        } else {
            holder.mIcHead.setImageResource(R.mipmap.ic_head);
        }

//        if (position == 0) {
//            holder.mLin.setBackgroundResource(R.drawable.bg_lead_line);
//        }
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTvNum;
        ImageView mIcHead;
        TextView mTvName;
        TextView mTvBuNum;
        LinearLayout mLin;

        ViewHolder(View itemView) {
            super(itemView);
            this.mTvNum = itemView.findViewById(R.id.tv_num);
            this.mIcHead = itemView.findViewById(R.id.ic_head);
            this.mTvName = itemView.findViewById(R.id.tv_name);
            this.mTvBuNum = itemView.findViewById(R.id.tv_bu_num);
            this.mLin = itemView.findViewById(R.id.lin);
        }
    }
}
