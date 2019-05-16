package com.xuranus.amx.log.event;

import android.util.Log;

import com.xuranus.amx.latency.ClockProvider;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LatencyEventLogger implements EventLogger<LatencyEvent> {
    private static final String TAG = LatencyEventLogger.class.getSimpleName();
    private ClockProvider mClock;
    private final Map<String, LatencyEvent> mEvents = new ConcurrentHashMap();

    protected LatencyEventLogger(ClockProvider clock) {
        this.mClock = clock;
    }

    public LatencyEvent start(String eventName) {
        LatencyEvent e = (LatencyEvent) this.mEvents.get(eventName);
        if (e == null) {
            e = LatencyEvent.Builder.withNameAndClock(eventName, this.mClock).build();
            this.mEvents.put(eventName, e);
            return e;
        }
        Log.w(TAG, "Event is already started: " + eventName);
        return e;
    }

    public void end(String eventName) {
        LatencyEvent e = (LatencyEvent) this.mEvents.get(eventName);
        if (e != null) {
            e.end();
        } else {
            Log.w(TAG, "Event isn't started yet: " + eventName);
        }
    }

    public void mark(String eventName) {
        mark(eventName, false);
    }

    public void mark(String eventName, boolean isLoggingEndEvent) {
        if (this.mEvents.containsKey(eventName)) {
            Log.w(TAG, "Event is already existing: " + eventName);
            return;
        }
        this.mEvents.put(eventName, LatencyEvent.Builder.withNameAndClock(eventName, this.mClock).setDuration(0).setIsLoggingEndEvent(isLoggingEndEvent).build());
    }

    public void markWithStartTime(String eventName, long startTime, boolean isLoggingEndEvent) {
        if (this.mEvents.containsKey(eventName)) {
            Log.w(TAG, "Event is already existing: " + eventName);
            return;
        }
        this.mEvents.put(eventName, LatencyEvent.Builder.withNameAndClock(eventName, this.mClock).setDuration(0).setStartTimeSince1970(startTime).setIsLoggingEndEvent(isLoggingEndEvent).build());
    }

    public void dump(Reporter<LatencyEvent> reporter) {
        Log.d(TAG, "dump events");
        if (reporter != null) {
            reporter.report(new ArrayList(this.mEvents.values()));
        }
        this.mEvents.clear();
    }

}
