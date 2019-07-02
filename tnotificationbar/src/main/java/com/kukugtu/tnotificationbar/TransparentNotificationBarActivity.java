package com.kukugtu.tnotificationbar;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

/**
 * Create By Kukugtu on 2019/7/2 2:22 PM
 * Description: 透明状态栏Activity
 * UpdateUser:
 * UpdateDate:
 * UpdateRemark: 更新说明
 */
public abstract class TransparentNotificationBarActivity extends AppCompatActivity
        implements SlidingPaneLayout.PanelSlideListener {


    private MySlideMenu slidingPaneLayout;
    private View rootView;
    private boolean isOnrestart = false;

    @Override
    public void setContentView(View view) {
        rootView = view;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TransparentUtil.setTranspartVeiw(this);
        }
        super.setContentView(rootView);
    }

    @Override
    public void setContentView(int layoutResID) {
        rootView = LayoutInflater.from(this).inflate(layoutResID, null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TransparentUtil.setTranspartVeiw(this);
        }
        super.setContentView(rootView);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        isOnrestart = true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (!isOnrestart) {
                isOnrestart = false;
                initSwipeBackFinish();
            }
        }
    }

    private void initSwipeBackFinish() {
        if (getSupportSwipeBack()) {
            slidingPaneLayout = new MySlideMenu(this);
            try {
                Field f_overHang = SlidingPaneLayout.class.getDeclaredField("mOverhangSize");
                f_overHang.setAccessible(true);
                f_overHang.set(slidingPaneLayout, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            slidingPaneLayout.setPanelSlideListener(this);
            slidingPaneLayout.setSliderFadeColor(getResources().getColor(android.R.color.transparent));

            View leftView = new View(this);
            leftView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            slidingPaneLayout.addView(leftView, 0);
            ViewGroup decor = (ViewGroup) rootView.getParent();
            ViewGroup decorChild = (ViewGroup) decor.getChildAt(0);
            decorChild.setBackground(rootView.getBackground());
            decor.removeView(decorChild);
            slidingPaneLayout.addView(decorChild, 1);
            decor.addView(slidingPaneLayout, 0);

        }
    }


    protected boolean getSupportSwipeBack() {
        return false;
    }

    @Override
    public void onPanelSlide(@NonNull View view, float v) {

    }

    @Override
    public void onPanelOpened(@NonNull View view) {
        finish();
        if (getSupportSwipeBack()) {
            this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        }
    }

    @Override
    public void onPanelClosed(@NonNull View view) {

    }
}
