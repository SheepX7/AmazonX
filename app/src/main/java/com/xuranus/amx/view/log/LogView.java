package com.xuranus.amx.view.log;

import com.xuranus.amx.log.XLog;

public interface LogView {

    enum LEVEL{
        DEBUG, INFO, WARNING, VERBOSE, ERRO
    }

    void appendLog(String tag, LEVEL level, String str);

    XLog getLogger();
}
