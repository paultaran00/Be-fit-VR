package com.unity3d.player;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.Camera;

public class Camera2Wrapper implements C0057c {

    /* renamed from: a */
    private Context f13a;

    /* renamed from: b */
    private C0050b f14b = null;

    /* renamed from: c */
    private final int f15c = 100;

    public Camera2Wrapper(Context context) {
        this.f13a = context;
        initCamera2Jni();
    }

    /* renamed from: a */
    private static int m3a(float f) {
        return (int) Math.min(Math.max((f * 2000.0f) - 0.0040893555f, -900.0f), 900.0f);
    }

    private final native void deinitCamera2Jni();

    private final native void initCamera2Jni();

    private final native void nativeFrameReady(Object obj, Object obj2, Object obj3, int i, int i2, int i3);

    private final native void nativeSurfaceTextureReady(Object obj);

    /* renamed from: a */
    public final void mo30a() {
        deinitCamera2Jni();
        closeCamera2();
    }

    /* renamed from: a */
    public final void mo31a(Object obj) {
        nativeSurfaceTextureReady(obj);
    }

    /* renamed from: a */
    public final void mo32a(Object obj, Object obj2, Object obj3, int i, int i2, int i3) {
        nativeFrameReady(obj, obj2, obj3, i, i2, i3);
    }

    /* access modifiers changed from: protected */
    public void closeCamera2() {
        C0050b bVar = this.f14b;
        if (bVar != null) {
            bVar.mo189b();
        }
        this.f14b = null;
    }

    /* access modifiers changed from: protected */
    public int getCamera2Count() {
        if (PlatformSupport.LOLLIPOP_SUPPORT) {
            return C0050b.m49a(this.f13a);
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public int getCamera2FocalLengthEquivalent(int i) {
        if (PlatformSupport.LOLLIPOP_SUPPORT) {
            return C0050b.m70d(this.f13a, i);
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public int[] getCamera2Resolutions(int i) {
        if (PlatformSupport.LOLLIPOP_SUPPORT) {
            return C0050b.m73e(this.f13a, i);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public int getCamera2SensorOrientation(int i) {
        if (PlatformSupport.LOLLIPOP_SUPPORT) {
            return C0050b.m50a(this.f13a, i);
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public Object getCameraFocusArea(float f, float f2) {
        int a = m3a(f);
        int a2 = m3a(1.0f - f2);
        return new Camera.Area(new Rect(a - 100, a2 - 100, a + 100, a2 + 100), 1000);
    }

    /* access modifiers changed from: protected */
    public Rect getFrameSizeCamera2() {
        C0050b bVar = this.f14b;
        return bVar != null ? bVar.mo186a() : new Rect();
    }

    /* access modifiers changed from: protected */
    public boolean initializeCamera2(int i, int i2, int i3, int i4, int i5) {
        if (!PlatformSupport.LOLLIPOP_SUPPORT || this.f14b != null || UnityPlayer.currentActivity == null) {
            return false;
        }
        C0050b bVar = new C0050b(this);
        this.f14b = bVar;
        return bVar.mo188a(this.f13a, i, i2, i3, i4, i5);
    }

    /* access modifiers changed from: protected */
    public boolean isCamera2AutoFocusPointSupported(int i) {
        if (PlatformSupport.LOLLIPOP_SUPPORT) {
            return C0050b.m68c(this.f13a, i);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean isCamera2FrontFacing(int i) {
        if (PlatformSupport.LOLLIPOP_SUPPORT) {
            return C0050b.m66b(this.f13a, i);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void pauseCamera2() {
        C0050b bVar = this.f14b;
        if (bVar != null) {
            bVar.mo191d();
        }
    }

    /* access modifiers changed from: protected */
    public boolean setAutoFocusPoint(float f, float f2) {
        C0050b bVar;
        if (!PlatformSupport.LOLLIPOP_SUPPORT || (bVar = this.f14b) == null) {
            return false;
        }
        return bVar.mo187a(f, f2);
    }

    /* access modifiers changed from: protected */
    public void startCamera2() {
        C0050b bVar = this.f14b;
        if (bVar != null) {
            bVar.mo190c();
        }
    }

    /* access modifiers changed from: protected */
    public void stopCamera2() {
        C0050b bVar = this.f14b;
        if (bVar != null) {
            bVar.mo192e();
        }
    }
}
