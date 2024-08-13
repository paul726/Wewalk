package com.nus.wewalk.ui.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.nus.wewalk.R;
import com.nus.wewalk.databinding.FragmentMineBinding;
import com.nus.wewalk.ui.login.LoginActivity;

public class MineFragment extends Fragment implements View.OnClickListener {

    private FragmentMineBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MineViewModel mineViewModel =
                new ViewModelProvider(this).get(MineViewModel.class);

        binding = FragmentMineBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btEdit.setOnClickListener(this);
        binding.btFriends.setOnClickListener(this);
        binding.btHelp.setOnClickListener(this);
        binding.btSet.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_edit) {
            startActivity(new Intent(getActivity(), PersonActivity.class));
        } else if (v.getId() == R.id.bt_friends) {
            //friends
            startActivity(new Intent(getActivity(), FriendsActivity.class));
        } else if (v.getId() == R.id.bt_help) {
            //help
            startActivity(new Intent(getActivity(), SupportActivity.class));
        } else if (v.getId() == R.id.bt_set) {
            //set
            startActivity(new Intent(getActivity(), SetActivity.class));
        }
    }
}