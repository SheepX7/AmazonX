package com.xuranus.amx.log;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

public class RemoteLog extends XLog {

    private Messenger messenger;
    public static final String REMOTE_TAG = "remote";
    public RemoteLog(Messenger messenger) {
        this.messenger = messenger;
    }

    @Override
    public void i(String str, Object... args) {
        try {
            messenger.send(createMessage(str, args));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void e(String str, Object... args) {
        try {
            messenger.send(createMessage(str, args));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void w(String str, Object... args) {
        try {
            messenger.send(createMessage(str, args));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void v(String str, Object... args) {
        try {
            messenger.send(createMessage(str, args));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void d(String str, Object... args) {
        try {
            messenger.send(createMessage(str, args));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public Message createMessage(String str, Object... args) {
        Message msg = Message.obtain(null, 1);
        Bundle bundle = new Bundle();
        bundle.putString(REMOTE_TAG, formatLog(str, args));
        msg.setData(bundle);
        return msg;
    }
}
