/*
 * Copyright 2012-2014 eBay Software Foundation and selendroid committers.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.selendroid.server;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import io.selendroid.server.extension.ExtensionLoader;
import io.selendroid.server.model.ExternalStorage;
import io.selendroid.server.util.Intents;
import io.selendroid.server.util.SelendroidLogger;

/**
 * A lightweight instrumentation without server
 * It's used for starting the app and running bootstrap only, where you don't want to interact with the app further
 */
public class LightweightInstrumentation extends Instrumentation {
    @Override
    public void onCreate(Bundle arguments) {
        SelendroidLogger.info("LightweightInstrumentation.onCreate");
        final Context context = getTargetContext();
        final InstrumentationArguments args = new InstrumentationArguments(arguments);
        final ExtensionLoader extensionLoader;
        Handler handler = new Handler();

        if (args.isLoadExtensions()) {
            extensionLoader = new ExtensionLoader(context, ExternalStorage.getExtensionDex().getAbsolutePath());
            String bootstrapClassNames = args.getBootstrapClassNames();
            if (bootstrapClassNames != null && !bootstrapClassNames.isEmpty()) {
                extensionLoader.runBeforeApplicationCreateBootstrap(this, bootstrapClassNames.split(","));
            }
        } else {
            extensionLoader = null;
        }

        // Queue bootstrapping and starting of the main activity on the main thread.
        handler.post(new Runnable() {
            @Override
            public void run() {
                UncaughtExceptionHandling.clearCrashLogFile();
                UncaughtExceptionHandling.setGlobalExceptionHandler();

                if (args.isLoadExtensions() && args.getBootstrapClassNames() != null) {
                    extensionLoader.runAfterApplicationCreateBootstrap(
                        LightweightInstrumentation.this,
                        args.getBootstrapClassNames().split(","));
                }

                if (args.getServiceClassName() != null) {
                    Intent serviceIntent = Intents.createStartServiceIntent(
                            context, args.getServiceClassName(), args.getIntentAction());
                    context.startService(serviceIntent);
                } else {
                    // Start the new activity
                    String activity = args.getActivityClassName();
                    Intent intent = null;
                    if (activity != null) {
                        intent = Intents.createStartActivityIntent(context, activity);
                    } else if (args.getIntentUri() != null) {
                        intent = Intents.createUriIntent(args.getIntentAction(), args.getIntentUri());
                    }
                    if (intent != null) {
                        context.startActivity(intent);
                    }
                }
            }
        });
        SelendroidLogger.info("LightweightInstrumentation initialized");
    }
}
