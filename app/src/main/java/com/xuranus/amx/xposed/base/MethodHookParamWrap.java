package com.xuranus.amx.xposed.base;

import android.content.Context;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;

public class MethodHookParamWrap {

    private XC_MethodHook.MethodHookParam mParam;

    public MethodHookParamWrap(XC_MethodHook.MethodHookParam param) {
        this.mParam = param;
    }

    public Context getContext() {
        if (mParam == null) {
            return null;
        }

        if (!(mParam.thisObject instanceof Context)) {
            XposedBridge.log("Cannot parse MethodHookParam.thisObject to Context");
            throw new IllegalArgumentException("Cannot parse MethodHookParam.thisObject to Context");
        }

        return (Context) mParam.thisObject;
    }
}
