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

import java.lang.reflect.Field;

/**
 * @deprecated Please use new implemented extension mechanism
 * @see <a href="https://github.com/selendroid/selendroid-extension">extension mechanism Docu</a> 
 */
public class FindRId implements NativeExecuteScript {

  private ServerInstrumentation serverInstrumentation;

  public FindRId(ServerInstrumentation serverInstrumentation) {
    this.serverInstrumentation = serverInstrumentation;
  }

  @Override
  public Object executeScript(JSONArray args) {
    Class rClazz;
    try {
      rClazz = serverInstrumentation.getInstrumentation().getTargetContext().getClassLoader().loadClass(
          serverInstrumentation.getInstrumentation().getTargetContext().getPackageName() +".R$id"
      );
    } catch (ClassNotFoundException e) {
      SelendroidLogger.error("Cannot find id", e);
      return "";
    }
    String using = null;
    try {
      using = args.getString(0);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    for (Field field : rClazz.getFields()) {
      if (field.getName().equalsIgnoreCase(using)) {
        try {
          return field.getInt(null);
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }
    return "";
  }
}
