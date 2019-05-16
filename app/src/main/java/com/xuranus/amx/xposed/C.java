package com.xuranus.amx.xposed;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.lang.reflect.Method;

public class C {

    public static final Class Activty = Activity.class;
    public static final Class Application = android.app.Application.class;
    public static final Class Bundle = android.os.Bundle.class;



    public static class Method {
        public static final String ONCREATE = "onCreate";
    }


}
