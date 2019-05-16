package com.xuranus.amx.xposed.hookers;

import com.xuranus.amx.log.XLog;

public interface Hooker {

    void hook();

    void setLogger(XLog logger);
}
