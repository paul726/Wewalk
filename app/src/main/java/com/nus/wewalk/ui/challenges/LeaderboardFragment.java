package com.nus.wewalk.ui.challenges;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.nus.wewalk.databinding.FragmentLeaderBinding;
import com.nus.wewalk.utilities.SystemBarUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Leaderboard
 */
public class LeaderboardFragment extends Fragment {

    private FragmentLeaderBinding binding;
    private LeaderListAdapter leaderListAdapter;

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
        setupRecyclerView();
    }


    private void setupRecyclerView() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        leaderListAdapter = new LeaderListAdapter(getActivity(),list );
        binding.recycle.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recycle.setAdapter(leaderListAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
