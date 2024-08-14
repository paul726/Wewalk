package com.nus.wewalk.utilities;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.widget.LinearLayout;

public class SystemBarUtils {

    public static void setStatusBarColor(Activity context, View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            XSystemBarTintManager systemBarTintManager = new XSystemBarTintManager(context);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            layoutParams.height = systemBarTintManager.getConfig().getStatusBarHeight();
            view.setLayoutParams(layoutParams);
        }
    }
}