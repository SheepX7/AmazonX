package com.xuranus.amx.xposed.hookers.component;

import com.xuranus.amx.log.XLog;
import com.xuranus.amx.xposed.hookers.BaseHooker;

public class ComponentHooker extends BaseHooker {


    @Override
    public void safeHook() {

    }

    @Override
    public void setLogger(XLog logger) {
        this.logger = logger;
    }
}
