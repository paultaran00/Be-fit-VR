package com.unity3d.player;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;

public class HFPStatus {

    /* renamed from: a */
    private Context f16a;

    /* renamed from: b */
    private BroadcastReceiver f17b = null;

    /* renamed from: c */
    private Intent f18c = null;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public boolean f19d = false;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public AudioManager f20e = null;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public int f21f = C0009a.f23a;

    /* renamed from: com.unity3d.player.HFPStatus$a */
    enum C0009a {
        ;

        static {
            f26d = new int[]{1, 2, 3};
        }
    }

    public HFPStatus(Context context) {
        this.f16a = context;
        this.f20e = (AudioManager) context.getSystemService("audio");
        initHFPStatusJni();
    }

    private final native void deinitHFPStatusJni();

    private final native void initHFPStatusJni();

    /* renamed from: a */
    public final void mo50a() {
        deinitHFPStatusJni();
    }

    /* access modifiers changed from: protected */
    public boolean getHFPStat() {
        return this.f21f == C0009a.f24b;
    }

    /* access modifiers changed from: protected */
    public void requestHFPStat() {
        C00081 r0 = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                int intExtra = intent.getIntExtra("android.media.extra.SCO_AUDIO_STATE", -1);
                if (intExtra == 0) {
                    if (HFPStatus.this.f19d) {
                        HFPStatus.this.f20e.setMode(0);
                    }
                    boolean unused = HFPStatus.this.f19d = false;
                } else if (intExtra == 1) {
                    int unused2 = HFPStatus.this.f21f = C0009a.f24b;
                    if (!HFPStatus.this.f19d) {
                        HFPStatus.this.f20e.stopBluetoothSco();
                    } else {
                        HFPStatus.this.f20e.setMode(3);
                    }
                } else if (intExtra == 2) {
                    if (HFPStatus.this.f21f == C0009a.f24b) {
                        boolean unused3 = HFPStatus.this.f19d = true;
                    } else {
                        int unused4 = HFPStatus.this.f21f = C0009a.f25c;
                    }
                }
            }
        };
        this.f17b = r0;
        this.f18c = this.f16a.registerReceiver(r0, new IntentFilter("android.media.ACTION_SCO_AUDIO_STATE_UPDATED"));
        try {
            this.f20e.startBluetoothSco();
        } catch (NullPointerException unused) {
            C0058d.Log(5, "startBluetoothSco() failed. no bluetooth device connected.");
        }
    }
}
