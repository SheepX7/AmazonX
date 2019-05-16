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
package io.selendroid.server.android;

import java.util.*;

import android.app.Activity;

public class ActivitiesReporter {
  private Activity currentActivity;
  private Activity backgroundActivity;
  private int backgroundTask;
  private final Map<Activity, Integer> liveActivities = new HashMap<Activity, Integer>();
  private int lastAssignedId;

  public Set<Activity> getActivities() {
    return new HashSet<Activity>(liveActivities.keySet());
  }

  public Activity getCurrentActivity() {
    return currentActivity;
  }

  /**
   * Records the given {@code Activity} and assigns it an ID.
   */
  public void wasCreated(Activity activity) {
    liveActivities.put(activity, ++lastAssignedId);
  }

  public void wasResumed(Activity activity) {
    currentActivity = activity;
  }

  public void wasDestroyed(Activity activity) {
    liveActivities.remove(activity);

    if (currentActivity == activity) {
      currentActivity = null;
    }
  }

  public void setBackgroundActivity(Activity activity) {
    backgroundActivity = activity;
  }

  public Activity getBackgroundActivity() {
    return backgroundActivity;
  }

}
