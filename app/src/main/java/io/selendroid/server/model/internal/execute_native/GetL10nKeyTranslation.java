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
package io.selendroid.server.model.internal.execute_native;

import io.selendroid.server.ServerInstrumentation;
import io.selendroid.server.util.SelendroidLogger;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.res.Resources;

public class GetL10nKeyTranslation implements NativeExecuteScript {

  private ServerInstrumentation serverInstrumentation;

  public GetL10nKeyTranslation(ServerInstrumentation serverInstrumentation) {
    this.serverInstrumentation = serverInstrumentation;
  }

  @Override
  public Object executeScript(JSONArray args) {
    try {
      return getLocalizedString(args.getString(0));
    } catch (JSONException e) {
      SelendroidLogger.error("Cannot get key translation", e);
      return "";
    }
  }

  private String getLocalizedString(String l10nKey) {
    Activity currentActivity = serverInstrumentation.getCurrentActivity();
    int resourceId =
        currentActivity.getResources().getIdentifier(l10nKey, "string",
            currentActivity.getPackageName());
    try {
      return currentActivity.getResources().getString(resourceId);
    } catch (Resources.NotFoundException e) {
      return "";
    }
  }
}
