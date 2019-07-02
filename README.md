继承于TransparentNotificationBarActivity，即可实现透明状态栏

布局文件是对于整个屏幕，通常需要让出通知栏高度
TransparentUtil.setStatusBarLeave(view, this);

对于API23+，以及部分MIUI，Flyme可以实现修改通知栏文字颜色
//设置文字颜色黑白
TransparentUtil.setStatusBarLightMode(this, true);

重写getSupportSwipeBack方法用于标识是否支持滑动返回
true可以滑动返回，false不可以滑动返回
@Override
protected boolean getSupportSwipeBack() {
    return true;
}
