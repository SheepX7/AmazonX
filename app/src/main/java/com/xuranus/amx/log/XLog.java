package com.xuranus.amx.log;

public abstract class XLog {

    protected boolean DEBUG = true;
    protected String TAG = "XLog";

    public abstract void i(String str, Object... args);
    public abstract void e(String str, Object... args);
    public abstract void w(String str, Object... args);
    public abstract void v(String str, Object... args);
    public abstract void d(String str, Object... args);

    public String formatLog(String str, Object... args){
        return String.format(str, args);
    }

}
