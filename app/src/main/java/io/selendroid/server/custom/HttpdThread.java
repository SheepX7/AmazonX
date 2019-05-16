package io.selendroid.server.custom;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;

import com.xuranus.amx.xposed.util.ToastUtil;

import io.selendroid.server.common.exceptions.SelendroidException;
import io.selendroid.server.util.SelendroidLogger;

public class HttpdThread extends Thread{

    private final CustomAndroidServer server;
    private Application application = null;
    private Looper looper;
    private PowerManager.WakeLock wakeLock;
    private Handler mHandler;
    private int port;

    public HttpdThread(Application application, int serverPort) {
        this.application = application;
        // Create the server but absolutely do not start it here
        server = new CustomAndroidServer(application, serverPort);
        this.mHandler = new Handler(application.getMainLooper());
        this.port = serverPort;
    }

    @Override
    public void run() {
        post("startServer == port;%s", port);
        Looper.prepare();
        looper = Looper.myLooper();
        startServer();
        Looper.loop();
    }

    public CustomAndroidServer getServer() {
        return server;
    }

    @SuppressLint("InvalidWakeLockTag")
    private void startServer() {
        PowerManager pm = (PowerManager) application.getSystemService(Context.POWER_SERVICE);
        this.wakeLock =
                pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "Selendroid");
        try {
            startAndroidServer(
                    this.server, wakeLock);
        } catch (Exception e) {
            SelendroidLogger.error("Error starting httpd.", e);

            throw new SelendroidException("Httpd failed to start!");
        }
    }

    public void stopLooping() {
        if (looper == null) {
            return;
        }
        looper.quit();
    }


    protected int parseServerPort(String port) {
        int parsedServerPort;
        try {
            parsedServerPort = Integer.parseInt(port);
        } catch (NumberFormatException e) {
            SelendroidLogger.info("Failed to parse server port, defaulting to 8080");
            parsedServerPort = 8080;
        }
        if (!isValidPort(parsedServerPort)) {
            SelendroidLogger.info("Invalid port " + parsedServerPort + ", defaulting to " + 4444);
        }
        return parsedServerPort;
    }

    protected static void startAndroidServer(
            CustomAndroidServer server,
            PowerManager.WakeLock wakeLock
    ) {
        try {
            wakeLock.acquire();
        } catch (SecurityException e) {
        }

        server.start();

        SelendroidLogger.info("Started selendroid http server on port " + server.getPort());
    }

    private boolean isValidPort(int port) {
        return port >= 1024 && port <= 65535;
    }


    private void post(final String str, final Object ... value) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                ToastUtil.toastLong(application, str, value);
            }
        });
    }
}
