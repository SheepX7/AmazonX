package com.xuranus.amx.xposed.hookers.component;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.MutableContextWrapper;
import android.content.ServiceConnection;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.xuranus.amx.log.RemoteLog;
import com.xuranus.amx.log.XLog;
import com.xuranus.amx.view.desktop.DesktopCommand;
import com.xuranus.amx.xposed.C;
import com.xuranus.amx.xposed.api.IApplication;
import com.xuranus.amx.xposed.base.ActiviyHook;
import com.xuranus.amx.xposed.base.ApplicationHook;
import com.xuranus.amx.xposed.base.MethodHookParamWrap;
import com.xuranus.amx.xposed.util.ToastUtil;
import com.yanzhenjie.permission.AndPermission;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import io.selendroid.server.android.ViewHierarchyAnalyzer;

public class Applications extends ComponentHooker implements IApplication {

    private static Handler mH;
    private LoggerInitListener loggerInitListener;
    private DesktopCommand sCommandConsole;
    private Messenger mServerMessenger;
    private ApplicationHook plugin;


    public Applications(ApplicationHook activiyHook) {
        plugin = activiyHook;
    }


    private ServiceConnection mMessengerConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(final ComponentName name, final IBinder service) {
            mServerMessenger = new Messenger(service);
            if (loggerInitListener != null) {
                loggerInitListener.onInitLogger(new RemoteLog(mServerMessenger));
            }
        }

        @Override
        public void onServiceDisconnected(final ComponentName name) {
            mServerMessenger = null;
        }
    };



    @Override
    public void safeHook() {
        super.safeHook();
        onCreate();
    }


    private void initGlobalView(final Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.xuranus.amx","com.xuranus.amx.service.ConsoleService"));
        context.bindService(intent, mMessengerConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onCreate() {
        XposedHelpers.findAndHookMethod(C.Application, C.Method.ONCREATE, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                final MethodHookParamWrap wrapper = new MethodHookParamWrap(param);
                if (plugin != null) {
                    plugin.afterOnCreate((Application) wrapper.getContext());
                }
            }
        });
    }


    public interface LoggerInitListener {
        void onInitLogger(XLog logger);
    }

    public Applications setLoggerInitedListener(LoggerInitListener listener) {
        this.loggerInitListener = listener;
        return this;
    }

}
