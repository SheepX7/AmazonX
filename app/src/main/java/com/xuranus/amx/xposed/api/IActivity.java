package com.xuranus.amx.xposed.api;

import com.xuranus.amx.xposed.base.ActiviyHook;

public interface IActivity {

    void onActivityCreating();

    void onActivityResume();

}
