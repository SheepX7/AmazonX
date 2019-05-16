package com.xuranus.amx.log.event;

import java.util.List;

public interface EventLogger<E extends EventLogger.Event> {

    public interface Event {
        void end();
    }

    public interface Reporter<E> {

        void report(List<E> list);
    }

    void mark(String str);

    E start(String str);
}
