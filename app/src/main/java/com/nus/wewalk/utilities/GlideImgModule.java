package com.nus.wewalk.utilities;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;

import java.io.InputStream;

/**
 * Glide加载https图片处理
 */
@GlideModule
public class GlideImgModule extends AppGlideModule {

    //处理图片加载错误问题
    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        super.registerComponents(context, glide, registry);
        //设置请求方式为okhttp 并设置okhttpClient的证书及超时时间
        OkHttpUrlLoader.Factory factory = new OkHttpUrlLoader.Factory(UnsafeOkHttpClient.getUnsafeOkHttpClient());
        registry.replace(GlideUrl.class, InputStream.class, factory);
    }
}