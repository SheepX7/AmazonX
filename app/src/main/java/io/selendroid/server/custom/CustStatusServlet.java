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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.selendroid.server.common.ServerDetails;
import io.selendroid.server.common.http.HttpRequest;
import io.selendroid.server.common.http.HttpResponse;
import io.selendroid.server.common.http.HttpServlet;


public class CustStatusServlet implements HttpServlet {
  private JSONArray apps = null;

  public CustStatusServlet() {
  }

  @Override
  public void handleHttpRequest(HttpRequest httpRequest, HttpResponse httpResponse)
      throws Exception {
    if (!"/wd/hub/status".equals(httpRequest.uri())) {
      return;
    }
    if (!"GET".equalsIgnoreCase(httpRequest.method())) {
      httpResponse.setStatus(404).end();
      return;
    }

    JSONObject result = createDetailedStatusResponse();

    httpResponse.setContentType("application/json").setStatus(200).setContent(result.toString())
        .end();
  }

  private JSONObject createDetailedStatusResponse() throws JSONException {
    JSONObject build = new JSONObject();
    build.put("version", "1.0.1");
    build.put("browserName", "selendroid");

    JSONObject os = new JSONObject();
    os.put("arch", "111");
    os.put("name", "tes");
    os.put("version", 111);

    JSONObject json = new JSONObject();
    json.put("build", build);
    json.put("os", os);

    JSONArray devices = null;

    devices = new JSONArray();


    json.put("supportedDevices", devices);


    apps = new JSONArray();

    json.put("supportedApps", apps);
    JSONObject result = new JSONObject();
    result.put("status", 0);
    result.put("value", json);
    return result;
  }
}
