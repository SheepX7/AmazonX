package com.xuranus.amx.log;

import com.xuranus.amx.view.log.LogView;

public class ConsoleLog extends XLog {

    private LogView mLogView;

    public ConsoleLog(LogView logView, String tag) {
        this.TAG = tag;
        this.mLogView = logView;
    }

    @Override
    public void i(String str, Object... args) {
        mLogView.appendLog(TAG, LogView.LEVEL.INFO, formatLog(str, args));
    }

    @Override
    public void e(String str, Object... args) {
        mLogView.appendLog(TAG, LogView.LEVEL.ERRO, formatLog(str, args));
    }

    @Override
    public void w(String str, Object... args) {
        mLogView.appendLog(TAG, LogView.LEVEL.WARNING, formatLog(str, args));

    }

    @Override
    public void v(String str, Object... args) {
        mLogView.appendLog(TAG, LogView.LEVEL.VERBOSE, formatLog(str, args));
    }

    @Override
    public void d(String str, Object... args) {
        mLogView.appendLog(TAG, LogView.LEVEL.DEBUG, formatLog(str, args));
    }

    @Override
    public String formatLog(String str, Object... args) {
        return super.formatLog(getFunctionName() + "-" + str, args);
    }

    private String getFunctionName() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();

        if (sts == null) {
            return null;
        }

        for (StackTraceElement st:sts) {
            if (st.isNativeMethod()) {
                continue;
            }

            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }

            if (st.getClassName().equals(this.getClass().getName())) {
                continue;
            }

            return "["+Thread.currentThread().getId()+": "+st.getFileName()+":"+st.getLineNumber()+"]";
        }

        return null;
    }




}
