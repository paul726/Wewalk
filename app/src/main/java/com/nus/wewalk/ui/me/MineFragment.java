package com.nus.wewalk.ui.me;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.luck.picture.lib.basic.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnResultCallbackListener;
import com.nus.wewalk.R;
import com.nus.wewalk.databinding.FragmentMineBinding;
import com.nus.wewalk.utilities.GlideEngine;
import com.nus.wewalk.utilities.SystemBarUtils;
import com.nus.wewalk.utilities.Utils;
import com.nus.wewalk.utilities.XImageFileCompressEngine;
import com.nus.wewalk.utilities.XImageUtils;
import com.nus.wewalk.utilities.XShareCacheUtils;

import java.util.ArrayList;

public class MineFragment extends Fragment implements View.OnClickListener {

    private FragmentMineBinding binding;
    private MineViewModel mineViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMineBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SystemBarUtils.setStatusBarColor(getActivity(), binding.statusBar);
        mineViewModel = new ViewModelProvider(getActivity()).get(MineViewModel.class);

        binding.btEdit.setOnClickListener(this);
        binding.btFriends.setOnClickListener(this);
        binding.btHelp.setOnClickListener(this);
        binding.btSet.setOnClickListener(this);
        binding.reBg.setOnClickListener(this);
        binding.reHead.setOnClickListener(this);
        //
        mineViewModel.getUserInfo();

        //数据展示
        mineViewModel.userInfoBeanLiveData.observe(getActivity(), new Observer<UserInfoBean>() {
            @Override
            public void onChanged(UserInfoBean loginBean) {
                if (loginBean != null) {
                    XShareCacheUtils.getInstance().putString("uid", loginBean.getUserId());
                    if (binding != null) {
                        binding.tvNickNames.setText(loginBean.getUserName());
                    }
                }
            }
        });
        //背景-头像展示:本地使用
        String head = XShareCacheUtils.getInstance().getString("head");
        String bg = XShareCacheUtils.getInstance().getString("bg");
        if (!TextUtils.isEmpty(head)) {
            XImageUtils.load(getActivity(), head, binding.ivHead);
        }
        if (!TextUtils.isEmpty(bg)) {
            XImageUtils.load(getActivity(), bg, binding.ivBg);
        }
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
        } else if (v.getId() == R.id.re_bg) {
            //bg
            Utils.showPhotoDialog(getActivity(), (code, id) -> {
                if (code.equals("1")) {
                    PictureSelector.create(this)
                            .openCamera(SelectMimeType.ofImage())
                            .forResult(new OnResultCallbackListener<LocalMedia>() {
                                @Override
                                public void onResult(ArrayList<LocalMedia> result) {
                                    for (LocalMedia media : result) {
                                        if (!TextUtils.isEmpty(media.getCompressPath())) {
                                            XImageUtils.load(getActivity(), media.getCompressPath(), binding.ivBg);
                                            XShareCacheUtils.getInstance().putString("bg", media.getCompressPath());
                                        } else if (!TextUtils.isEmpty(media.getRealPath())) {
                                            XImageUtils.load(getActivity(), media.getRealPath(), binding.ivBg);
                                            XShareCacheUtils.getInstance().putString("bg", media.getRealPath());
                                        } else if (!TextUtils.isEmpty(media.getPath())) {
                                            XImageUtils.load(getActivity(), media.getPath(), binding.ivBg);
                                            XShareCacheUtils.getInstance().putString("bg", media.getPath());
                                        }
                                    }
                                }

                                @Override
                                public void onCancel() {

                                }
                            });
                } else {
                    PictureSelector.create(this)
                            .openGallery(SelectMimeType.ofImage())
                            .setImageEngine(GlideEngine.createGlideEngine())
                            .forResult(new OnResultCallbackListener<LocalMedia>() {
                                @Override
                                public void onResult(ArrayList<LocalMedia> result) {
                                    for (LocalMedia media : result) {
                                        if (!TextUtils.isEmpty(media.getCompressPath())) {
                                            XImageUtils.load(getActivity(), media.getCompressPath(), binding.ivBg);
                                            XShareCacheUtils.getInstance().putString("bg", media.getCompressPath());
                                        } else if (!TextUtils.isEmpty(media.getRealPath())) {
                                            XImageUtils.load(getActivity(), media.getRealPath(), binding.ivBg);
                                            XShareCacheUtils.getInstance().putString("bg", media.getRealPath());
                                        } else if (!TextUtils.isEmpty(media.getPath())) {
                                            XImageUtils.load(getActivity(), media.getPath(), binding.ivBg);
                                            XShareCacheUtils.getInstance().putString("bg", media.getPath());
                                        }
                                    }
                                }

                                @Override
                                public void onCancel() {
                                }
                            });
                }
            });
        } else if (v.getId() == R.id.re_head) {
            //head
            Utils.showPhotoDialog(getActivity(), (code, id) -> {
                if (code.equals("1")) {
                    PictureSelector.create(this)
                            .openCamera(SelectMimeType.ofImage())
                            .forResult(new OnResultCallbackListener<LocalMedia>() {
                                @Override
                                public void onResult(ArrayList<LocalMedia> result) {
                                    for (LocalMedia media : result) {
                                        if (!TextUtils.isEmpty(media.getCompressPath())) {
                                            XImageUtils.load(getActivity(), media.getCompressPath(), binding.ivHead);
                                            XShareCacheUtils.getInstance().putString("head", media.getCompressPath());
                                        } else if (!TextUtils.isEmpty(media.getRealPath())) {
                                            XImageUtils.load(getActivity(), media.getRealPath(), binding.ivHead);
                                            XShareCacheUtils.getInstance().putString("head", media.getRealPath());
                                        } else if (!TextUtils.isEmpty(media.getPath())) {
                                            XImageUtils.load(getActivity(), media.getPath(), binding.ivHead);
                                            XShareCacheUtils.getInstance().putString("head", media.getPath());
                                        }
                                    }
                                }

                                @Override
                                public void onCancel() {
                                }
                            });
                } else {
                    PictureSelector.create(this)
                            .openGallery(SelectMimeType.ofImage())
                            .setImageEngine(GlideEngine.createGlideEngine())
                            .setMinSelectNum(1)
                            .setMaxSelectNum(1)
                            .forResult(new OnResultCallbackListener<LocalMedia>() {
                                @Override
                                public void onResult(ArrayList<LocalMedia> result) {
                                    for (LocalMedia media : result) {
                                        if (!TextUtils.isEmpty(media.getCompressPath())) {
                                            XImageUtils.load(getActivity(), media.getCompressPath(), binding.ivHead);
                                            XShareCacheUtils.getInstance().putString("head", media.getCompressPath());
                                        } else if (!TextUtils.isEmpty(media.getRealPath())) {
                                            XImageUtils.load(getActivity(), media.getRealPath(), binding.ivHead);
                                            XShareCacheUtils.getInstance().putString("head", media.getRealPath());
                                        } else if (!TextUtils.isEmpty(media.getPath())) {
                                            XImageUtils.load(getActivity(), media.getPath(), binding.ivHead);
                                            XShareCacheUtils.getInstance().putString("head", media.getPath());
                                        }
                                    }
                                }

                                @Override
                                public void onCancel() {
                                }
                            });
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}