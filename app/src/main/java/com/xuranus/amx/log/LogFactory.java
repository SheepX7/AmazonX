package com.xuranus.amx.log;

import android.content.Context;

import com.xuranus.amx.AmazonX;
import com.xuranus.amx.view.desktop.DesktopCommand;
import com.xuranus.amx.view.log.LogView;

import de.robv.android.xposed.XposedBridge;

public class LogFactory {

    public static LogFactory INSTANCE;
    private XLog mLog;
    public static final int TYPE_COMMON_LOG = 0;
    public static final int TYPE_CONSOLE_LOG = 1;
    public static final int TYPE_XBRIDGE_LOG = 2;

    public static LogFactory getInstance() {
        if (INSTANCE == null) {
            synchronized (LogFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LogFactory();
                }
            }
        }
        return INSTANCE;
    }

    public XLog createLog(int type, String tag) {
        switch (type) {
            case TYPE_COMMON_LOG:
                mLog = new CommonLog(tag);
                break;
            case TYPE_CONSOLE_LOG: {
                LogView logView = AmazonX.getInstance().getConsole();
                if (logView != null) {
                     return AmazonX.getInstance().getConsole().getLogger();
                }
                break;
            }
            case TYPE_XBRIDGE_LOG:
                mLog = new XposedBridgeLog();
        }
        return mLog;
    }


    /**
     * temp func
     * @param type
     * @param context
     * @return
     */
    public XLog createLog(int type, Context context) {
        LogView logView = new DesktopCommand(context);
        return logView.getLogger();
    }

}
