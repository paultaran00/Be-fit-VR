package com.oculus.utilities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.util.Log;

class HeadsetReceiver extends BroadcastReceiver {
    private static final boolean DEBUG = false;
    private static final String TAG = "HeadsetReceiver";
    public static IntentFilter filter;
    public static HeadsetReceiver receiver;

    private static native void stateChanged(int i);

    HeadsetReceiver() {
    }

    private static void startReceiver(Activity activity) {
        stateChanged(getCurrentHeadsetState(activity));
        Log.d(TAG, "Registering headset receiver");
        if (filter == null) {
            filter = new IntentFilter("android.intent.action.HEADSET_PLUG");
        }
        if (receiver == null) {
            receiver = new HeadsetReceiver();
        }
        activity.registerReceiver(receiver, filter);
    }

    private static void stopReceiver(Context context) {
        Log.d(TAG, "Unregistering headset receiver");
        context.unregisterReceiver(receiver);
    }

    private static int getCurrentHeadsetState(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        boolean z = audioManager != null && audioManager.isWiredHeadsetOn();
        Log.d(TAG, "getCurrentHeadsetState: " + z);
        return z ? 1 : 0;
    }

    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "!$$$$$$! headsetReceiver::onReceive");
        if (intent.hasExtra("state")) {
            stateChanged(intent.getIntExtra("state", 0));
        }
    }
}
