package com.oculus.utilities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

class BatteryReceiver extends BroadcastReceiver {
    private static final boolean DEBUG = false;
    private static final String TAG = "UnityBatteryReceiver";
    public static IntentFilter filter;
    public static BatteryReceiver receiver;
    private int batteryLevel = 0;
    private int batteryStatus = 0;
    private int batteryTemperature = 0;

    private static native void dispatchEvent(int i, int i2, int i3);

    BatteryReceiver() {
    }

    private void processIntent(Intent intent) {
        int i = 0;
        if (intent.getBooleanExtra("present", false)) {
            int intExtra = intent.getIntExtra("status", 0);
            int intExtra2 = intent.getIntExtra("level", -1);
            int intExtra3 = intent.getIntExtra("scale", -1);
            int intExtra4 = intent.getIntExtra("temperature", 0);
            if (intExtra2 >= 0 && intExtra3 > 0) {
                i = (intExtra2 * 100) / intExtra3;
            }
            if (intExtra != this.batteryStatus || i != this.batteryLevel || intExtra4 != this.batteryTemperature) {
                this.batteryStatus = intExtra;
                this.batteryLevel = i;
                this.batteryTemperature = intExtra4;
                dispatchEvent(intExtra, i, intExtra4);
            }
        }
    }

    private static void startReceiver(Activity activity) {
        Log.d(TAG, "Registering battery receiver");
        if (filter == null) {
            filter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
        }
        if (receiver == null) {
            receiver = new BatteryReceiver();
        }
        activity.registerReceiver(receiver, filter);
        receiver.processIntent(activity.getIntent());
    }

    private static void stopReceiver(Context context) {
        Log.d(TAG, "Unregistering battery receiver");
        context.unregisterReceiver(receiver);
    }

    public void onReceive(Context context, Intent intent) {
        processIntent(intent);
    }
}
