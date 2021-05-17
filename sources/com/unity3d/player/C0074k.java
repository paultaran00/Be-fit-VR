package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import com.unity3d.player.C0071j;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: com.unity3d.player.k */
final class C0074k {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public UnityPlayer f236a = null;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public Context f237b = null;

    /* renamed from: c */
    private C0081a f238c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public final Semaphore f239d = new Semaphore(0);
    /* access modifiers changed from: private */

    /* renamed from: e */
    public final Lock f240e = new ReentrantLock();
    /* access modifiers changed from: private */

    /* renamed from: f */
    public C0071j f241f = null;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public int f242g = 2;

    /* renamed from: h */
    private boolean f243h = false;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public boolean f244i = false;

    /* renamed from: com.unity3d.player.k$a */
    public interface C0081a {
        /* renamed from: a */
        void mo129a();
    }

    C0074k(UnityPlayer unityPlayer) {
        this.f236a = unityPlayer;
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public void m134d() {
        C0071j jVar = this.f241f;
        if (jVar != null) {
            this.f236a.removeViewFromPlayer(jVar);
            this.f244i = false;
            this.f241f.destroyPlayer();
            this.f241f = null;
            C0081a aVar = this.f238c;
            if (aVar != null) {
                aVar.mo129a();
            }
        }
    }

    /* renamed from: a */
    public final void mo266a() {
        this.f240e.lock();
        C0071j jVar = this.f241f;
        if (jVar != null) {
            if (this.f242g == 0) {
                jVar.CancelOnPrepare();
            } else if (this.f244i) {
                boolean a = jVar.mo239a();
                this.f243h = a;
                if (!a) {
                    this.f241f.pause();
                }
            }
        }
        this.f240e.unlock();
    }

    /* renamed from: a */
    public final boolean mo267a(Context context, String str, int i, int i2, int i3, boolean z, long j, long j2, C0081a aVar) {
        this.f240e.lock();
        this.f238c = aVar;
        this.f237b = context;
        this.f239d.drainPermits();
        this.f242g = 2;
        final String str2 = str;
        final int i4 = i;
        final int i5 = i2;
        final int i6 = i3;
        final boolean z2 = z;
        final long j3 = j;
        final long j4 = j2;
        runOnUiThread(new Runnable() {
            public final void run() {
                if (C0074k.this.f241f != null) {
                    C0058d.Log(5, "Video already playing");
                    int unused = C0074k.this.f242g = 2;
                    C0074k.this.f239d.release();
                    return;
                }
                C0071j unused2 = C0074k.this.f241f = new C0071j(C0074k.this.f237b, str2, i4, i5, i6, z2, j3, j4, new C0071j.C0072a() {
                    /* renamed from: a */
                    public final void mo263a(int i) {
                        C0074k.this.f240e.lock();
                        int unused = C0074k.this.f242g = i;
                        if (i == 3 && C0074k.this.f244i) {
                            C0074k.this.runOnUiThread(new Runnable() {
                                public final void run() {
                                    C0074k.this.m134d();
                                    C0074k.this.f236a.resume();
                                }
                            });
                        }
                        if (i != 0) {
                            C0074k.this.f239d.release();
                        }
                        C0074k.this.f240e.unlock();
                    }
                });
                if (C0074k.this.f241f != null) {
                    C0074k.this.f236a.addView(C0074k.this.f241f);
                }
            }
        });
        boolean z3 = false;
        try {
            this.f240e.unlock();
            this.f239d.acquire();
            this.f240e.lock();
            if (this.f242g != 2) {
                z3 = true;
            }
        } catch (InterruptedException unused) {
        }
        runOnUiThread(new Runnable() {
            public final void run() {
                C0074k.this.f236a.pause();
            }
        });
        runOnUiThread((!z3 || this.f242g == 3) ? new Runnable() {
            public final void run() {
                C0074k.this.m134d();
                C0074k.this.f236a.resume();
            }
        } : new Runnable() {
            public final void run() {
                if (C0074k.this.f241f != null) {
                    C0074k.this.f236a.addViewToPlayer(C0074k.this.f241f, true);
                    boolean unused = C0074k.this.f244i = true;
                    C0074k.this.f241f.requestFocus();
                }
            }
        });
        this.f240e.unlock();
        return z3;
    }

    /* renamed from: b */
    public final void mo268b() {
        this.f240e.lock();
        C0071j jVar = this.f241f;
        if (jVar != null && this.f244i && !this.f243h) {
            jVar.start();
        }
        this.f240e.unlock();
    }

    /* renamed from: c */
    public final void mo269c() {
        this.f240e.lock();
        C0071j jVar = this.f241f;
        if (jVar != null) {
            jVar.updateVideoLayout();
        }
        this.f240e.unlock();
    }

    /* access modifiers changed from: protected */
    public final void runOnUiThread(Runnable runnable) {
        Context context = this.f237b;
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(runnable);
        } else {
            C0058d.Log(5, "Not running from an Activity; Ignoring execution request...");
        }
    }
}
