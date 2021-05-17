package com.unity3d.player;

/* renamed from: com.unity3d.player.i */
final class C0070i {

    /* renamed from: a */
    private static boolean f202a = false;

    /* renamed from: b */
    private boolean f203b = false;

    /* renamed from: c */
    private boolean f204c = false;

    /* renamed from: d */
    private boolean f205d = true;

    /* renamed from: e */
    private boolean f206e = false;

    C0070i() {
    }

    /* renamed from: a */
    static void m108a() {
        f202a = true;
    }

    /* renamed from: b */
    static void m109b() {
        f202a = false;
    }

    /* renamed from: c */
    static boolean m110c() {
        return f202a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo229a(boolean z) {
        this.f203b = z;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo230b(boolean z) {
        this.f205d = z;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final void mo231c(boolean z) {
        this.f206e = z;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public final void mo232d(boolean z) {
        this.f204c = z;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public final boolean mo233d() {
        return this.f205d;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public final boolean mo234e() {
        return this.f206e;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public final boolean mo235f() {
        return f202a && this.f203b && !this.f205d && !this.f204c;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public final boolean mo236g() {
        return this.f204c;
    }

    public final String toString() {
        return super.toString();
    }
}
