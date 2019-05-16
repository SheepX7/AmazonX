package com.xuranus.amx.latency;

public interface ClockProvider {
    public static final ClockProvider NO_OP = new ClockProvider() {
        public long elapsedRealtime() {
            return 0;
        }
    };

    long elapsedRealtime();
}
