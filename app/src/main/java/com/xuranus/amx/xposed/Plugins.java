package com.xuranus.amx.xposed;

import com.xuranus.amx.log.XLog;
import com.xuranus.amx.xposed.hookers.Hooker;

import java.util.ArrayList;
import java.util.List;

public class Plugins {


    private List<Hooker> mHookers;

    public Plugins(Builder builder) {
        this.mHookers = builder.mHookers;
    }

    public void hook() {
        if (mHookers == null || mHookers.size() == 0) {
            return;
        }

        for (Hooker hooker: mHookers) {
            hooker.hook();
        }
    }


    public static class Builder {

        private List<Hooker> mHookers;
        private XLog mLogger;

        Builder() {
            mHookers = new ArrayList<>();
        }

        /**
         * Registe Hooker ref @class Hooker
         * @param hooker
         * @return
         */

        public Builder register(Hooker hooker) {
            if (!mHookers.contains(hooker)) {
                mHookers.add(hooker);
            }
            return this;
        }

        public Plugins build() {
            return new Plugins(this);
        }
    }
}
