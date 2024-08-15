package com.nus.wewalk.utilities;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * 自定义Android垂直ProgressBar
 */
public class VerticalProgressBar extends ProgressBar {

    public VerticalProgressBar(Context context) {
        super(context);
    }

    public VerticalProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VerticalProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.rotate(-90);
        canvas.translate(-getHeight(), 0);
        super.onDraw(canvas);
    }
}