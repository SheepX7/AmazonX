package com.xuranus.amx.xposed.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {


    public static void toastLong(Context context, String str, Object... args) {
        Toast.makeText(context, String.format(str, args), Toast.LENGTH_LONG).show();
    }


}
