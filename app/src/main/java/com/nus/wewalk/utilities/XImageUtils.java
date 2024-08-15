package com.nus.wewalk.utilities;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

/**
 * 图片加载
 */
public class XImageUtils {

    public static void load(Context context, String imageUrl, ImageView imageView, int defaultDrawable) {
        Glide.with(context).load(imageUrl).apply(new RequestOptions().centerCrop().placeholder(defaultDrawable).error(defaultDrawable).diskCacheStrategy(DiskCacheStrategy.ALL)).disallowHardwareConfig().into(imageView);
    }

    public static void load(Context context, String imageUrl, ImageView imageView) {
        Glide.with(context).load(imageUrl).apply(new RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)).disallowHardwareConfig().into(imageView);
    }

    public static void loadFit(Context context, String imageUrl, ImageView imageView) {
        Glide.with(context).load(imageUrl).apply(new RequestOptions().fitCenter().diskCacheStrategy(DiskCacheStrategy.ALL)).disallowHardwareConfig().into(imageView);
    }

    public static void loadNoCache1(Context context, String imageUrl, ImageView imageView) {
        Glide.with(context).load(imageUrl).apply(new RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(false)).into(imageView);
    }

    public static void loadFitImage(Context context, int imageUrl, ImageView imageView) {
        Glide.with(context).load(imageUrl).apply(new RequestOptions().fitCenter().diskCacheStrategy(DiskCacheStrategy.ALL)).disallowHardwareConfig().into(imageView);
    }

    public static void loadRoundImage(Context context, String imageUrl, ImageView imageView) {
        Glide.with(context).load(imageUrl).apply(new RequestOptions().centerCrop().bitmapTransform(new RoundedCorners(20))
                .diskCacheStrategy(DiskCacheStrategy.ALL)).disallowHardwareConfig().into(imageView);
    }
}