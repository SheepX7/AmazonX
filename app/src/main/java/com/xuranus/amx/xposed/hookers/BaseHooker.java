package com.xuranus.amx.xposed.hookers;

import com.xuranus.amx.log.LogFactory;
import com.xuranus.amx.log.XLog;

public abstract class BaseHooker implements Hooker {

    protected XLog logger = LogFactory.getInstance().createLog(LogFactory.TYPE_XBRIDGE_LOG, "xxx");

    public abstract void safeHook();

    @Override
    public void hook() {
        safeHook();
    }
}
