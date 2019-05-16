package com.xuranus.amx.log;

import de.robv.android.xposed.XposedBridge;

public class XposedBridgeLog extends XLog {
    @Override
    public void i(String str, Object... args) {
        XposedBridge.log(formatLog(str, args));
    }

    @Override
    public void e(String str, Object... args) {
        XposedBridge.log(formatLog(str, args));

    }

    @Override
    public void w(String str, Object... args) {
        XposedBridge.log(formatLog(str, args));
    }

    @Override
    public void v(String str, Object... args) {
        XposedBridge.log(formatLog(str, args));
    }

    @Override
    public void d(String str, Object... args) {
        XposedBridge.log(formatLog(str, args));
    }
}
