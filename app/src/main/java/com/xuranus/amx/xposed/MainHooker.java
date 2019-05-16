package com.xuranus.amx.xposed;

import android.content.Context;
import android.text.TextUtils;

import com.xuranus.amx.log.LogFactory;
import com.xuranus.amx.log.XLog;
import com.xuranus.amx.xposed.base.ActivityDefaultHook;
import com.xuranus.amx.xposed.base.ApplicationDefaultHook;
import com.xuranus.amx.xposed.hookers.GlobelHooker;
import com.xuranus.amx.xposed.hookers.component.Activities;
import com.xuranus.amx.xposed.hookers.component.Applications;
import com.xuranus.amx.xposed.util.ToastUtil;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class MainHooker implements IXposedHookLoadPackage {

    public static XLog logger = LogFactory.getInstance().createLog(LogFactory.TYPE_XBRIDGE_LOG, "Xpose");
    public static MainHooker INSTANCE;


    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        if (!lpparam.packageName.equals("cn.amazon.mShop.android")){
            return;
        }

        logger.i("开始劫持");
        new Plugins.Builder()
                .register(new Activities(new ActivityDefaultHook()))
                .register(new Applications(new ApplicationDefaultHook())
                        .setLoggerInitedListener(new Applications.LoggerInitListener() {
                    @Override
                    public void onInitLogger(XLog logger) {
                        MainHooker.this.logger = logger;
                    }
                })).register(new GlobelHooker())
                .build()
                .hook();


    }


    public static XLog getLogger() {
        return logger;
    }

}
