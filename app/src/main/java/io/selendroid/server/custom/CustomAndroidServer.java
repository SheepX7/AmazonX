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
package io.selendroid.server.custom;

import android.app.Application;

import io.selendroid.server.AndroidServlet;
import io.selendroid.server.ServerInstrumentation;
import io.selendroid.server.common.StatusServlet;
import io.selendroid.server.common.http.HttpServer;
import io.selendroid.server.inspector.InspectorServlet;
import io.selendroid.server.model.Factories;
import io.selendroid.server.model.SelendroidDriver;

public class CustomAndroidServer {
  private int driverPort = 8080;
  private HttpServer webServer;

  public CustomAndroidServer(Application androidInstrumentation, int port) {
    driverPort = port;
    webServer = new HttpServer(driverPort);
    init(androidInstrumentation);
  }

  protected void init(Application androidInstrumentation) {
    webServer.addHandler(new CustStatusServlet());
  }

  public void start() {
    webServer.start();
  }

  public void stop() {
    webServer.stop();
  }

  public int getPort() {
    return webServer.getPort();
  }
}
