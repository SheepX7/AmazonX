package com.xuranus.amx.splash;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.xuranus.amx.AmazonXActivity;
import com.xuranus.amx.log.event.LatencyEventLogger;

public class SplashActivity extends AmazonXActivity {
    public static final int JUMP_TIMEOUT = 3000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                jugeJumptoMainHomeActivity();
            }
        }, JUMP_TIMEOUT);
    }

    private void jugeJumptoMainHomeActivity() {

    }
}
