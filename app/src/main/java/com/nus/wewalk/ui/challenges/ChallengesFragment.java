package com.nus.wewalk.ui.challenges;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.nus.wewalk.databinding.FragmentChallengesBinding;

public class ChallengesFragment extends Fragment {

    private FragmentChallengesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ChallengesViewModel homeViewModel =
                new ViewModelProvider(this).get(ChallengesViewModel.class);

        binding = FragmentChallengesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}