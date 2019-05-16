package com.xuranus.amx;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

import com.xuranus.amx.service.ConsoleService;
import com.xuranus.amx.view.desktop.DesktopCommand;
import com.xuranus.amx.view.floatview.FloatWindow;
import com.xuranus.amx.view.floatview.MoveType;
import com.xuranus.amx.view.floatview.PermissionListener;
import com.xuranus.amx.view.floatview.Screen;
import com.xuranus.amx.view.floatview.ViewStateListener;
import com.xuranus.amx.view.log.LogView;
import com.yanzhenjie.permission.AndPermission;

public class AmazonX extends Application {

    private static AmazonX INSTANCE;
    private static LogView sCommandConsole;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        startService(new Intent(this, ConsoleService.class));
    }

    public void initGlobalView() {

        sCommandConsole = new DesktopCommand(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                AndPermission.with(this).overlay().start();
                return;
            }
        }

        //Activity中的方法,得到窗口管理器
        WindowManager mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        //设置悬浮窗布局属性
        WindowManager.LayoutParams mWindowLayoutParams = new WindowManager.LayoutParams();
        //设置类型,具体有哪些值可取在后面附上
        mWindowLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        //设置行为选项,具体有哪些值可取在后面附上
        mWindowLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        //设置悬浮窗的显示位置
        mWindowLayoutParams.gravity = Gravity.CENTER;
//        //设置悬浮窗的横竖屏,会影响屏幕方向,只要悬浮窗不消失,屏幕方向就会一直保持,可以强制屏幕横屏或竖屏
//        mWindowLayoutParams.screenOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        //设置 x 轴的偏移量
        mWindowLayoutParams.x = 0;
        //设置 y 轴的偏移量
        mWindowLayoutParams.y = 0;
        //如果悬浮窗图片为透明图片,需要设置该参数为 PixelFormat.RGBA_8888
        mWindowLayoutParams.format = PixelFormat.RGBA_8888;
        //设置悬浮窗的宽度
        mWindowLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        //设置悬浮窗的高度
        mWindowLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //设置悬浮窗的布局
        //加载显示悬浮窗
        mWindowManager.addView((View) sCommandConsole, mWindowLayoutParams);

    }

    private void initFloatButton() {
        ImageView imageView = new ImageView(getApplicationContext());
        float density = getApplicationContext().getResources().getDisplayMetrics().density;
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageResource(R.mipmap.icon);
        FloatWindow
                .with(getApplicationContext())
                .setView(imageView)
                .setWidth((int) (60 * density)) //设置悬浮控件宽高
                .setHeight((int) (60 * density))
                .setX(Screen.width, 0.8f)
                .setY(Screen.height, 0.3f)
                .setMoveType(MoveType.slide, 100, -100)
                .setMoveStyle(500, new BounceInterpolator())
                .setFilter(true, AmazonXActivity.class)
                .setViewStateListener(mViewStateListener)
                .setPermissionListener(mPermissionListener)
                .setDesktopShow(true)
                .build();

    }

    private ViewStateListener mViewStateListener = new ViewStateListener() {
        @Override
        public void onPositionUpdate(int x, int y) {
        }

        @Override
        public void onShow() {

        }

        @Override
        public void onHide() {

        }

        @Override
        public void onDismiss() {

        }

        @Override
        public void onMoveAnimStart() {

        }

        @Override
        public void onMoveAnimEnd() {

        }

        @Override
        public void onBackToDesktop() {

        }

    };

    private PermissionListener mPermissionListener = new PermissionListener() {
        @Override
        public void onSuccess() {

        }

        @Override
        public void onFail() {

        }
    };

    public static AmazonX getInstance() {
        return INSTANCE;
    }

    public LogView getConsole() {
        return sCommandConsole;
    }

}
