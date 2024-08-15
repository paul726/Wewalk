package com.nus.wewalk.utilities;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.nus.wewalk.R;

public class Utils {

    public static void hideStatusBar(Activity activity) {
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.TRANSPARENT);
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }



    /**
     * 拍照选择
     * @param context
     */
    public static void showPhotoDialog(final Activity context, final OnResult onCallbackResult) {
        final Dialog dialog = new Dialog(context,  R.style.dialogStyle);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_photo, null);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(view);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager m = context.getWindowManager();
        m.getDefaultDisplay().getMetrics(dm);
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes();
        p.width = (int) (dm.widthPixels);
        dialog.getWindow().setAttributes(p);
        dialog.show();
        TextView photograph_btn = (TextView) view.findViewById(R.id.photograph_btn);
        TextView select_photo_btn = (TextView) view.findViewById(R.id.select_photo_btn);
        TextView cancel = (TextView) view.findViewById(R.id.cancel_btn);
        //拍照选择
        photograph_btn.setOnClickListener(v -> {
            onCallbackResult.onSuccess("1", "1");
            dialog.dismiss();
        });
        // 从相册中选择照片
        select_photo_btn.setOnClickListener(v -> {
            onCallbackResult.onSuccess("2", "2");
            dialog.dismiss();
        });
        // 关闭alert对话框架
        cancel.setOnClickListener(v -> dialog.dismiss());
    }

    /**
     * 回掉
     */
    public interface OnResult {
        void onSuccess(String code, String id);
    }
}
