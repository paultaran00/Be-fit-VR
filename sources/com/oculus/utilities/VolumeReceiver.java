package com.oculus.utilities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.util.Log;

class VolumeReceiver extends BroadcastReceiver {
    private static final boolean DEBUG = false;
    private static String STREAM_TYPE = "android.media.EXTRA_VOLUME_STREAM_TYPE";
    private static String STREAM_VALUE = "android.media.EXTRA_VOLUME_STREAM_VALUE";
    private static final String TAG = "UnityVolumeReceiver";
    private static String VOLUME_CHANGED_ACTION = "android.media.VOLUME_CHANGED_ACTION";
    private static IntentFilter filter;
    private static VolumeReceiver receiver;

    private static native void volumeChanged(int i);

    VolumeReceiver() {
    }

    private static void startReceiver(Context context) {
        Log.d(TAG, "Registering volume receiver");
        if (filter == null) {
            IntentFilter intentFilter = new IntentFilter();
            filter = intentFilter;
            intentFilter.addAction(VOLUME_CHANGED_ACTION);
        }
        if (receiver == null) {
            receiver = new VolumeReceiver();
        }
        context.registerReceiver(receiver, filter);
        int streamVolume = ((AudioManager) context.getSystemService("audio")).getStreamVolume(3);
        Log.d(TAG, "startVolumeReceiver: " + streamVolume);
    }

    private static void stopReceiver(Context context) {
        Log.d(TAG, "Unregistering volume receiver");
        context.unregisterReceiver(receiver);
    }

    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "OnReceive VOLUME_CHANGED_ACTION");
        int intValue = ((Integer) intent.getExtras().get(STREAM_TYPE)).intValue();
        int intValue2 = ((Integer) intent.getExtras().get(STREAM_VALUE)).intValue();
        if (intValue == 3) {
            volumeChanged(intValue2);
            return;
        }
        Log.d(TAG, "skipping volume change from stream " + intValue);
    }
}
