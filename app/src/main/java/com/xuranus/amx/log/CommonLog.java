package com.xuranus.amx.log;

import android.text.TextUtils;
import android.util.Log;

public class CommonLog extends XLog {



    public CommonLog(String tag) {
        if (!TextUtils.isEmpty(tag)) {
            TAG = tag;
        }
    }

    @Override
    public void i(String str, Object... args) {
        if (DEBUG) {
            Log.i(TAG, formatLog(str, args));
        }
    }

    @Override
    public void e(String str, Object... args) {
        if (DEBUG) {
            Log.e(TAG, formatLog(str, args));
        }
    }

    @Override
    public void w(String str, Object... args) {
        if (DEBUG) {
            Log.w(TAG, formatLog(str, args));
        }
    }

    @Override
    public void v(String str, Object... args) {
        if (DEBUG) {
            Log.v(TAG, formatLog(str, args));
        }
    }

    @Override
    public void d(String str, Object... args) {
        if (DEBUG) {
            Log.d(TAG, formatLog(str, args));
        }
    }

    @Override
    public String formatLog(String str, Object... args) {
        StringBuilder builder = new StringBuilder();
        builder.append(str);
        return String.format(builder.toString(), args);
    }
}
