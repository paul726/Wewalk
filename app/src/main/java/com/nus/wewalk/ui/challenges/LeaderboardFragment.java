package com.nus.wewalk.ui.challenges;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.nus.wewalk.R;
import com.nus.wewalk.databinding.FragmentLeaderBinding;
import com.nus.wewalk.ui.me.MineViewModel;
import com.nus.wewalk.ui.me.UserInfoBean;
import com.nus.wewalk.utilities.SystemBarUtils;
import com.nus.wewalk.utilities.XShareCacheUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Leaderboard
 */
public class LeaderboardFragment extends Fragment {

    private FragmentLeaderBinding binding;
    private LeaderListAdapter leaderListAdapter;
    private ChallengesViewModel challengesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLeaderBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SystemBarUtils.setStatusBarColor(getActivity(), binding.statusBar);
        challengesViewModel = new ViewModelProvider(getActivity()).get(ChallengesViewModel.class);
        binding.recycle.setLayoutManager(new LinearLayoutManager(getActivity()));

        //数据请求
        String uid = XShareCacheUtils.getInstance().getString("uid");
        challengesViewModel.getFriendStepRank(uid);
        //
        challengesViewModel.rankListLiveData.observe(getActivity(), new Observer<List<RankBean>>() {
            @Override
            public void onChanged(List<RankBean> rankBeanList) {
                if (rankBeanList != null && !rankBeanList.isEmpty()) {
                    if (rankBeanList.get(0) != null) {
                        binding.tvOneName.setText(rankBeanList.get(0).getUserName());
                        binding.tvOneNum.setText(rankBeanList.get(0).getSteps());
                        Glide.with(getActivity()).load(rankBeanList.get(0).getAvatar())
                                .circleCrop().placeholder(R.mipmap.ic_head).into(binding.ivOne);
                    }
                    if (rankBeanList.size() > 1 && rankBeanList.get(1) != null) {
                        binding.tvTwoName.setText(rankBeanList.get(1).getUserName());
                        binding.tvTwoNum.setText(rankBeanList.get(1).getSteps());
                        Glide.with(getActivity()).load(rankBeanList.get(1).getAvatar())
                                .circleCrop().placeholder(R.mipmap.ic_head).into(binding.ivTwo);
                    }
                    if (rankBeanList.size() > 2 && rankBeanList.get(2) != null) {
                        binding.tvThreeName.setText(rankBeanList.get(2).getUserName());
                        binding.tvThreeNun.setText(rankBeanList.get(2).getSteps());
                        Glide.with(getActivity()).load(rankBeanList.get(2).getAvatar())
                                .circleCrop().placeholder(R.mipmap.ic_head).into(binding.ivThree);
                    }
                    if (rankBeanList.size() > 3) {
                        rankBeanList = rankBeanList.subList(3, rankBeanList.size());
                        leaderListAdapter = new LeaderListAdapter(getActivity(), rankBeanList);
                        binding.recycle.setAdapter(leaderListAdapter);
                    }
                } else {
                    Toast.makeText(getActivity(), "No data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
