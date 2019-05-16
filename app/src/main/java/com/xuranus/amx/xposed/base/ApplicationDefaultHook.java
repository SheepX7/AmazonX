package com.xuranus.amx.xposed.base;

import android.app.Application;
import android.os.Handler;

import com.xuranus.amx.xposed.util.ToastUtil;

import io.selendroid.server.InstrumentationArguments;
import io.selendroid.server.JUnitRunnerServerInstrumentation;
import io.selendroid.server.custom.HttpdThread;


public class ApplicationDefaultHook implements ApplicationHook{


    private Handler mHandler;
    private JUnitRunnerServerInstrumentation instrumentation;

    @Override
    public void afterOnCreate(final Application application) {
        ToastUtil.toastLong(application, "ApplicationDefaultHook#afterOnCreate Hook");
        mHandler = new Handler(application.getMainLooper());

        // test thread

    }
}
