package com.xuranus.amx.xposed.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

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
        MainHooker.logger.e("onActivity After Creating");
        ToastUtil.toastLong(context, "=================================" + context.getClass().getSimpleName());
        try {
            Class HomeActivity = Class.forName("com.amazon.mShop.home.web.MShopWebGatewayActivity");
            ToastUtil.toastLong(context, "目标：", HomeActivity.getClass().getSimpleName());

            if (HomeActivity.isInstance(context)) {
                ToastUtil.toastLong(context, "111111111");

                context.startActivity(new Intent(context, Class.forName("com.amazon.mShop.debug.DebugSettingsActivity")));
            }

        } catch (ClassNotFoundException e) {
            ToastUtil.toastLong(context, "错误：%s", e.toString());
            e.printStackTrace();
        }

        instrumentation = new JUnitRunnerServerInstrumentation(context,
                new InstrumentationArguments(arguments));

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
