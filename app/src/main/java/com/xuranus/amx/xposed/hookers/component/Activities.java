package com.xuranus.amx.xposed.hookers.component;

import android.content.Context;
import android.os.Bundle;

import com.xuranus.amx.xposed.C;
import com.xuranus.amx.xposed.api.IActivity;
import com.xuranus.amx.xposed.base.ActiviyHook;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class Activities extends ComponentHooker implements IActivity{


    private ActiviyHook plugin;

    public Activities(ActiviyHook activiyHook) {
        plugin = activiyHook;
    }


    @Override
    public void onActivityCreating() {
        XposedHelpers.findAndHookMethod(C.Activty, "onCreate", C.Bundle ,new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                plugin.onActivityBeforeCreating((Context) param.thisObject, (Bundle) param.args[0]);
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                plugin.onActivityAfterCreating((Context) param.thisObject, (Bundle) param.args[0]);
            }
        });
    }

    @Override
    public void onActivityResume() {
        XposedHelpers.findAndHookMethod(C.Activty, "onResume", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                plugin.onActivityBeforeResume((Context) param.thisObject);
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                plugin.onActivityAfterResume((Context) param.thisObject);
            }
        });
    }

    @Override
    public void safeHook() {
        super.safeHook();
        onActivityCreating();
        onActivityResume();
    }


}
