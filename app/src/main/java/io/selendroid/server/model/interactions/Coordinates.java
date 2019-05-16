/*
Copyright 2012 Selenium committers
Copyright 2012 Software Freedom Conservancy

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package io.selendroid.server.model.interactions;

import io.selendroid.server.android.internal.Point;

/**
 * Provides coordinates of an element for advanced interactions. Note that some coordinates (such as
 * screen coordinates) are evaluated lazily since the element may have to be scrolled into view.
 */
public interface Coordinates {
  Point getLocationOnScreen();

  Point getLocationInViewPort();

  Point getLocationInDOM();

  Object getAuxiliary();
}
