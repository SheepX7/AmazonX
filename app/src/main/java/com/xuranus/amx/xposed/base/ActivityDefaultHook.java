package com.xuranus.amx.xposed.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import com.xuranus.amx.log.LogFactory;
import com.xuranus.amx.log.XposedBridgeLog;
import com.xuranus.amx.xposed.MainHooker;
import com.xuranus.amx.xposed.util.ToastUtil;

import java.util.List;

import io.selendroid.server.DefaultServerInstrumentation;
import io.selendroid.server.InstrumentationArguments;
import io.selendroid.server.JUnitRunnerServerInstrumentation;
import io.selendroid.server.android.ViewHierarchyAnalyzer;

public class ActivityDefaultHook implements ActiviyHook {


    private DefaultServerInstrumentation delegateInstrumentation;
    private JUnitRunnerServerInstrumentation instrumentation;


    @Override
    public void onActivityBeforeCreating(Context context, Bundle arguments) {

    }

    @Override
    public void onActivityAfterCreating(Context context, Bundle arguments) {

        LogFactory.getInstance().createXposedLog().i("Activity %s onCreate",
                context.getClass().getSimpleName());
        instrumentation = new JUnitRunnerServerInstrumentation(context, null);

        instrumentation.onCreate();
        instrumentation.startServer();
    }

    @Override
    public void onActivityBeforeResume(Context context) {

    }

    @Override
    public void onActivityAfterResume(Context context) {
        
    }


}
