package com.kukugtu.transpartnotificationbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.kukugtu.tnotificationbar.TransparentNotificationBarActivity;
import com.kukugtu.tnotificationbar.TransparentUtil;

public class MainActivity extends TransparentNotificationBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int colorRED = (int) (Math.random() * 255);
        int colorGREEN = (int) (Math.random() * 255);
        int colorBLUE = (int) (Math.random() * 255);
        findViewById(R.id.view).setBackgroundColor(Color.rgb(colorRED, colorGREEN, colorBLUE));
        findViewById(R.id.view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });

        //根据背景颜色的深浅，设置通知栏文字透明度
        if ((colorBLUE + colorGREEN + colorRED) > (255 * 3/2)) {
            TransparentUtil.setStatusBarLightMode(this, false);
        } else {
            TransparentUtil.setStatusBarLightMode(this, true);
        }


        //设置让出通知栏
        View view = findViewById(R.id.relativeLayout);
        TransparentUtil.setStatusBarLeave(view, this);


    }

    //本页面是否支持滑动返回，true支持，false不支持
    @Override
    protected boolean getSupportSwipeBack() {
        return true;
    }
}
