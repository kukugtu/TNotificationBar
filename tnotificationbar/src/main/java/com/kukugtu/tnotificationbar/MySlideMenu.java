package com.kukugtu.tnotificationbar;

import android.content.Context;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by kukugtu on 2018/3/21 0021 12:46.
 */

public class MySlideMenu extends SlidingPaneLayout {
    private float slidingLength = 50;
    private float downX;

    public MySlideMenu(Context context) {
        super(context, null);
    }

    public MySlideMenu(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public MySlideMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO 自动生成的构造函数存根
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        //判断动作
        switch (arg0.getAction()) {
            case MotionEvent.ACTION_DOWN://按下记录第一个点
                downX = arg0.getX();
                break;
            case MotionEvent.ACTION_MOVE://
                break;
            case MotionEvent.ACTION_UP:
                downX = 0;
                break;
        }

        //当滑动长度小于标准时，取消这次滑动事件
        if (downX > slidingLength) {
            MotionEvent cancel = MotionEvent.obtain(arg0);
            cancel.setAction(MotionEvent.ACTION_CANCEL);
            return super.onInterceptTouchEvent(cancel);
        } else {
            return super.onInterceptTouchEvent(arg0);
        }
    }
}