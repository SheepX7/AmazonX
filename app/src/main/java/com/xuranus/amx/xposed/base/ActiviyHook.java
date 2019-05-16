package com.xuranus.amx.xposed.base;

import android.content.Context;
import android.os.Bundle;

import com.xuranus.amx.xposed.api.IActivity;

public interface ActiviyHook {


    void onActivityBeforeCreating(Context context, Bundle arguments);

    void onActivityAfterCreating(Context context, Bundle arguments);

    void onActivityBeforeResume(Context context);

    void onActivityAfterResume(Context context);


}
