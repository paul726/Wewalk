package com.nus.wewalk.ui.challenges;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

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
        challengesViewModel.rankListLiveData.observe(getActivity(), new Observer<List<UserInfoBean>>() {
            @Override
            public void onChanged(List<UserInfoBean> userInfoBeans) {
//                leaderListAdapter = new LeaderListAdapter(getActivity(), list);
//                binding.recycle.setAdapter(leaderListAdapter);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
