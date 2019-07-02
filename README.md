# TNotificationBar
Transparent Notification Bar

思路为在中间

继承于TransparentNotificationBarActivity，即可实现透明状态栏

布局文件是对于整个屏幕，通常需要湖区通知栏高度并让出通知栏
对于API23+，以及部分MIUI，Flyme可以实现修改通知栏文字颜色

//设置文字颜色黑白
TransparentUtil.setStatusBarLightMode(this, true);
//获取状态栏高度，主要用于让出状态栏高度
TransparentUtil.getStatusHeight(this);