package com.xuranus.amx.xposed.base;

import android.app.Application;

public interface ApplicationHook {
    void afterOnCreate(Application application);
}
