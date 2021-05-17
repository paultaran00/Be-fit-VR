package com.unity3d.player;

import android.util.Log;

/* renamed from: com.unity3d.player.d */
final class C0058d {

    /* renamed from: a */
    protected static boolean f173a = false;

    protected static void Log(int i, String str) {
        if (!f173a) {
            if (i == 6) {
                Log.e("Unity", str);
            }
            if (i == 5) {
                Log.w("Unity", str);
            }
        }
    }
}
