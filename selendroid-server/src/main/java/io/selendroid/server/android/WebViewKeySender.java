package io.selendroid.server.android;

import io.selendroid.server.ServerInstrumentation;
import io.selendroid.server.util.SelendroidLogger;
import android.app.Instrumentation;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.webkit.WebView;

public class WebViewKeySender extends InstrumentedKeySender {
  private final ServerInstrumentation serverInstrumentation;
  private final WebView webview;
  private volatile boolean done;

  public WebViewKeySender(ServerInstrumentation instrumentation, WebView webview) {
    super(instrumentation.getInstrumentation());
    this.webview = webview;
    this.serverInstrumentation = instrumentation;
  }

  /**
   * Sends key strokes to the given text to the element in focus within the webview.
   * 
   * Note: This assumes that the focus has been set before on the element at sake.
   * 
   * @param text
   */
  @Override
  public void send(final CharSequence text) {
    final KeyCharacterMap characterMap = KeyCharacterMap.load(KeyCharacterMap.VIRTUAL_KEYBOARD);

    long timeout =
        System.currentTimeMillis()
            + serverInstrumentation.getAndroidWait().getTimeoutInMillis();
    SelendroidLogger.info("Using timeout of " + timeout + " milli seconds.");

    done = false;

    instrumentation.runOnMainSync(new Runnable() {
      public void run() {
        for (int i = 0; i < text.length(); i++) {
          char c = text.charAt(i);
          int code = WebViewKeys.getKeyEventFromUnicodeKey(c);
          if (code != -1) {
            webview.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, code));
            webview.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, code));
          } else {
            KeyEvent[] arr = characterMap.getEvents(new char[] {c});
            if (arr != null) {
              for (int j = 0; j < arr.length; j++) {
                webview.dispatchKeyEvent(arr[j]);
              }
            }
          }
        }
        done = true;
      }
    });
  }
}
