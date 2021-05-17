package com.unity3d.player;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

/* renamed from: com.unity3d.player.e */
public final class C0059e extends Fragment {

    /* renamed from: a */
    private final IPermissionRequestCallbacks f174a;

    /* renamed from: b */
    private final Activity f175b;

    /* renamed from: c */
    private final Looper f176c;

    /* renamed from: com.unity3d.player.e$a */
    class C0060a implements Runnable {

        /* renamed from: b */
        private IPermissionRequestCallbacks f178b;

        /* renamed from: c */
        private String f179c;

        /* renamed from: d */
        private int f180d;

        /* renamed from: e */
        private boolean f181e;

        C0060a(IPermissionRequestCallbacks iPermissionRequestCallbacks, String str, int i, boolean z) {
            this.f178b = iPermissionRequestCallbacks;
            this.f179c = str;
            this.f180d = i;
            this.f181e = z;
        }

        public final void run() {
            int i = this.f180d;
            if (i == -1) {
                if (this.f181e) {
                    this.f178b.onPermissionDenied(this.f179c);
                } else {
                    this.f178b.onPermissionDeniedAndDontAskAgain(this.f179c);
                }
            } else if (i == 0) {
                this.f178b.onPermissionGranted(this.f179c);
            }
        }
    }

    public C0059e() {
        this.f174a = null;
        this.f175b = null;
        this.f176c = null;
    }

    public C0059e(Activity activity, IPermissionRequestCallbacks iPermissionRequestCallbacks) {
        this.f174a = iPermissionRequestCallbacks;
        this.f175b = activity;
        this.f176c = Looper.myLooper();
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestPermissions(getArguments().getStringArray("PermissionNames"), 96489);
    }

    public final void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 96489) {
            if (strArr.length == 0) {
                requestPermissions(getArguments().getStringArray("PermissionNames"), 96489);
                return;
            }
            int i2 = 0;
            while (i2 < strArr.length && i2 < iArr.length) {
                if (!(this.f174a == null || this.f175b == null || this.f176c == null)) {
                    String str = strArr[i2] == null ? "<null>" : strArr[i2];
                    new Handler(this.f176c).post(new C0060a(this.f174a, str, iArr[i2], this.f175b.shouldShowRequestPermissionRationale(str)));
                }
                i2++;
            }
            FragmentTransaction beginTransaction = getActivity().getFragmentManager().beginTransaction();
            beginTransaction.remove(this);
            beginTransaction.commit();
        }
    }
}
