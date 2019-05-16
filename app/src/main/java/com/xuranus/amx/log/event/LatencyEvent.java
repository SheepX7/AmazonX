package com.xuranus.amx.log.event;

import android.annotation.SuppressLint;
import android.support.v4.util.Preconditions;

import com.xuranus.amx.latency.ClockProvider;

import java.util.Arrays;

public class LatencyEvent implements EventLogger.Event {
    public static final LatencyEvent NO_OP_EVENT = new LatencyEvent("NO-OP", -1, 0, false, false, ClockProvider.NO_OP) {
        public void end() {
        }
    };
    private static final String TAG = LatencyEvent.class.getSimpleName();
    private ClockProvider mClock;
    private long mDuration;
    private final boolean mIsLoggingEndEvent;
    private final boolean mIsStartTimeSince1970;
    private final String mName;
    private long mStartTime;

    public static final class Builder {
        private ClockProvider mClock;
        private long mDuration = -1;
        private boolean mIsLoggingEndEvent;
        private boolean mIsStartTimeSince1970;
        private final String mName;
        private long mStartTime;

        private Builder(String name, ClockProvider clock) {
            this.mName = name;
            this.mClock = clock;
        }

        public static Builder withNameAndClock(String name, ClockProvider clock) {
            return new Builder(name, clock);
        }

        public Builder setStartTimeSince1970(long startTime) {
            this.mStartTime = startTime;
            this.mIsStartTimeSince1970 = true;
            return this;
        }

        public Builder setDuration(long duration) {
            this.mDuration = duration;
            return this;
        }

        public Builder setIsLoggingEndEvent(boolean isLoggingEndEvent) {
            this.mIsLoggingEndEvent = isLoggingEndEvent;
            return this;
        }

        public LatencyEvent build() {
            if (this.mIsStartTimeSince1970 && this.mDuration != 0) {
                throw new IllegalArgumentException("isStartTimeSince1970 cannot be true if duration is not zero");
            } else if (!this.mIsLoggingEndEvent || this.mDuration == 0) {
                if (this.mStartTime == 0) {
                    this.mStartTime = this.mClock.elapsedRealtime();
                }
                if (this.mName != null && !this.mName.isEmpty()) {
                    return new LatencyEvent(this.mName, this.mDuration, this.mStartTime, this.mIsStartTimeSince1970, this.mIsLoggingEndEvent, this.mClock);
                }
                throw new NullPointerException("Name cannot be null");
            } else {
                throw new IllegalArgumentException("Logging end event must be zero duration");
            }
        }
    }

    @SuppressLint("RestrictedApi")
    private LatencyEvent(String name, long duration, long startTime, boolean isStartTimeSince1970, boolean isLoggingEndEvent, ClockProvider clock) {
        this.mName = name;
        this.mDuration = duration;
        this.mStartTime = startTime;
        this.mIsStartTimeSince1970 = isStartTimeSince1970;
        this.mIsLoggingEndEvent = isLoggingEndEvent;
        Preconditions.checkNotNull(clock, "clock cannot be null");
        this.mClock = clock;
    }

    public void end() {
        if (this.mDuration == -1) {
            this.mDuration = this.mClock.elapsedRealtime() - this.mStartTime;
        }
    }

    public String toString() {
        return "event " + this.mName + " starts @ " + this.mStartTime + ", duration is " + this.mDuration;
    }

    public int compareTo(LatencyEvent another) {
        long x = this.mStartTime;
        long y = another.mStartTime;
        if (x < y) {
            return -1;
        }
        return x == y ? 0 : 1;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LatencyEvent)) {
            return false;
        }
        LatencyEvent another = (LatencyEvent) obj;
        if (this.mName.equals(another.mName) && this.mStartTime == another.mStartTime && this.mDuration == another.mDuration) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.mName, Long.valueOf(this.mStartTime), Long.valueOf(this.mDuration)});
    }

    public String getEventName() {
        return this.mName;
    }

    public long getStartTime() {
        return this.mStartTime;
    }

    public void setStartTime(long startTime) {
        this.mStartTime = startTime;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public boolean isStartTimeSince1970() {
        return this.mIsStartTimeSince1970;
    }

    public boolean isLoggingEndEvent() {
        return this.mIsLoggingEndEvent;
    }

}
