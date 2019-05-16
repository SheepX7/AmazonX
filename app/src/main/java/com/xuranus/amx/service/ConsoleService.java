package com.xuranus.amx.service;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

import com.xuranus.amx.AmazonX;
import com.xuranus.amx.AmazonXActivity;
import com.xuranus.amx.R;
import com.xuranus.amx.log.LogFactory;
import com.xuranus.amx.log.RemoteLog;
import com.xuranus.amx.log.XLog;
import com.xuranus.amx.view.desktop.DesktopCommand;
import com.xuranus.amx.view.floatview.FloatWindow;
import com.xuranus.amx.view.floatview.MoveType;
import com.xuranus.amx.view.floatview.Screen;

public class ConsoleService extends IntentService {

    private XLog log = LogFactory.getInstance().createLog(LogFactory.TYPE_COMMON_LOG, "ConsoleService");
    @SuppressLint("HandlerLeak")
    private Messenger mMessenger = new Messenger(new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg != null) {
                sCommandConsole.getLogger().i(msg.getData().getString(RemoteLog.REMOTE_TAG));
                log.i(msg.getData().getString(RemoteLog.REMOTE_TAG));
            }
        }
    });

    private static volatile DesktopCommand sCommandConsole;
    private float density;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public ConsoleService() {
        super("ConsoleService");
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
        initFloatButton();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //initGlobalView();

        for (;;) {
            if (sCommandConsole != null) {
                sCommandConsole.getLogger().i("current time :%s", System.currentTimeMillis());
                try {
                    Thread.sleep(360000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initFloatButton() {
        ImageView imageView = new ImageView(AmazonX.getInstance());
        sCommandConsole = new DesktopCommand(AmazonX.getInstance());
        density = getApplicationContext().getResources().getDisplayMetrics().density;
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(R.mipmap.icon);
        FloatWindow
                .with(AmazonX.getInstance())
                .setView(imageView)
                .setWidth((int) (60 * density)) //设置悬浮控件宽高
                .setHeight((int) (60 * density))
                .setY(Screen.height, 0.3f)
                .setTag("guide")
                .setX(30)
                .setMoveType(MoveType.back, 30, 30)
                .setMoveStyle(500, new BounceInterpolator())
                .setFilter(false, AmazonXActivity.class, ConsoleService.class)
                .setDesktopShow(true)
                .build();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatWindow.destroy("guide");
                FloatWindow
                        .with(AmazonX.getInstance())
                        .setView(sCommandConsole)
                        .setWidth(Screen.width, 1f) //设置悬浮控件宽高
                        .setY(Screen.height, 0.3f)
                        .setTag("console")
                        .setGravity(Gravity.TOP)
                        .setMoveType(MoveType.vertical, 0, 0)
                        .setFilter(false)
                        .setDesktopShow(true)
                        .build();
            }
        });
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
