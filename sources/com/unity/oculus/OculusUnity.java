package com.unity.oculus;

import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.ViewGroup;
import com.unity3d.player.UnityPlayer;
import java.util.Locale;

public class OculusUnity {
    Activity activity;
    SurfaceView glView;
    UnityPlayer player;

    private native void initComplete(Surface surface);

    public void destroyOculus() {
    }

    public void pauseOculus() {
    }

    public void resumeOculus() {
    }

    public void initOculus() {
        Log.d("Unity", "initOculus Java!");
        Activity activity2 = UnityPlayer.currentActivity;
        this.activity = activity2;
        activity2.runOnUiThread(new Runnable() {
            public final void run() {
                OculusUnity.this.lambda$initOculus$0$OculusUnity();
            }
        });
    }

    public /* synthetic */ void lambda$initOculus$0$OculusUnity() {
        ViewGroup viewGroup = (ViewGroup) this.activity.findViewById(16908290);
        this.player = null;
        int i = 0;
        while (true) {
            if (i >= viewGroup.getChildCount()) {
                break;
            } else if (viewGroup.getChildAt(i) instanceof UnityPlayer) {
                this.player = (UnityPlayer) viewGroup.getChildAt(i);
                break;
            } else {
                i++;
            }
        }
        if (this.player == null) {
            Log.e("Unity", "Failed to find UnityPlayer view!");
            return;
        }
        this.glView = null;
        for (int i2 = 0; i2 < this.player.getChildCount(); i2++) {
            if (this.player.getChildAt(0) instanceof SurfaceView) {
                this.glView = (SurfaceView) this.player.getChildAt(0);
            }
        }
        if (this.glView == null) {
            Log.e("Unity", "Failed to find GlView!");
        }
        Log.d("Unity", "Oculus UI thread done.");
        initComplete(this.glView.getHolder().getSurface());
    }

    public static void loadLibrary(String str) {
        Log.d("Unity", "loading library " + str);
        System.loadLibrary(str);
    }

    public static boolean getLowOverheadMode() {
        try {
            Activity activity2 = UnityPlayer.currentActivity;
            return activity2.getPackageManager().getApplicationInfo(activity2.getPackageName(), 128).metaData.getBoolean("com.unity.xr.oculus.LowOverheadMode");
        } catch (Exception unused) {
            Log.d("Unity", "Oculus XR Plugin init error");
            return false;
        }
    }

    public static boolean getIsOnOculusHardware() {
        return Build.MANUFACTURER.toLowerCase(Locale.ENGLISH).contains("oculus");
    }
}
