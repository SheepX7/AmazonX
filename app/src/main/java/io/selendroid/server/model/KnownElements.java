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
package io.selendroid.server.model;

import android.view.View;
import io.selendroid.server.android.ViewHierarchyAnalyzer;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class KnownElements {
  private final Map<String, AndroidElement> cache = new HashMap<String, AndroidElement>();
  private final Map<View, AndroidNativeElement> nativeElementsByView =
      new WeakHashMap<View, AndroidNativeElement>();

  public String add(AndroidElement element) {
    if (cache.containsValue(element)) {
      return getCacheKey(element);
    }

    cache.put(element.id(), element);
    if (element instanceof AndroidNativeElement) {
      AndroidNativeElement nativeElement = (AndroidNativeElement) element;
      nativeElementsByView.put(nativeElement.getView(), nativeElement);
    }
    return element.id();
  }

  /**
   * Uses the generated Id to look up elements
   */
  public AndroidElement get(String elementId) {
    AndroidElement element = cache.get(elementId);

    if (element instanceof AndroidNativeElement) {
      if (!ViewHierarchyAnalyzer.getDefaultInstance().isViewChieldOfCurrentRootView(
          ((AndroidNativeElement) element).getView())) {
        return null;
      }
    }
    return element;
  }

  public AndroidElement get(Long elementId) {
    return get(elementId.toString());
  }

  /**
   * Uses the generated Id to look up elements
   */
  public boolean hasElement(String elementId) {
    return cache.containsKey(elementId);
  }

  public boolean hasElement(Long elementId) {
    return hasElement(elementId.toString());
  }

  public AndroidNativeElement getNativeElement(View view) {
    return nativeElementsByView.get(view);
  }

  public boolean hasNativeElement(View view) {
    return nativeElementsByView.containsKey(view);
  }

  public String getIdOfElement(AndroidElement element) {
    if (cache.containsValue(element)) {
      return getCacheKey(element);
    }
    return null;
  }

  public void clear() {
    cache.clear();
    nativeElementsByView.clear();
    System.gc();
  }


  private String getCacheKey(AndroidElement element) {
    for (Map.Entry<String, AndroidElement> entry : cache.entrySet()) {
      if (entry.getValue().equals(element)) {
        return entry.getKey();
      }
    }
    return null;
  }
}
