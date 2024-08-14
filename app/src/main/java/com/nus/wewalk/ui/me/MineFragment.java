package com.nus.wewalk.ui.me;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.nus.wewalk.MainActivity;
import com.nus.wewalk.R;
import com.nus.wewalk.databinding.FragmentMineBinding;
import com.nus.wewalk.ui.login.LoginActivity;
import com.nus.wewalk.ui.login.data.LoginBean;
import com.nus.wewalk.utilities.UserInstance;
import com.nus.wewalk.utilities.XShareCacheUtils;

public class MineFragment extends Fragment implements View.OnClickListener {

    private FragmentMineBinding binding;
    private MineViewModel mineViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mineViewModel = new ViewModelProvider(this).get(MineViewModel.class);
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
        mineViewModel.getUserInfo();

        //数据展示
        mineViewModel.userInfoBeanLiveData.observe(getActivity(), new Observer<UserInfoBean>() {
            @Override
            public void onChanged(UserInfoBean loginBean) {
                binding.tvNickName.setText(loginBean.getUserName());
                XShareCacheUtils.getInstance().putString("uid", loginBean.getUserId());
            }
        });
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