package org.fmod;

import android.media.AudioTrack;
import android.util.Log;
import java.nio.ByteBuffer;

public class FMODAudioDevice implements Runnable {

    /* renamed from: h */
    private static int f258h = 0;

    /* renamed from: i */
    private static int f259i = 1;

    /* renamed from: j */
    private static int f260j = 2;

    /* renamed from: k */
    private static int f261k = 3;

    /* renamed from: a */
    private volatile Thread f262a = null;

    /* renamed from: b */
    private volatile boolean f263b = false;

    /* renamed from: c */
    private AudioTrack f264c = null;

    /* renamed from: d */
    private boolean f265d = false;

    /* renamed from: e */
    private ByteBuffer f266e = null;

    /* renamed from: f */
    private byte[] f267f = null;

    /* renamed from: g */
    private volatile C0082a f268g;

    private native int fmodGetInfo(int i);

    private native int fmodProcess(ByteBuffer byteBuffer);

    private void releaseAudioTrack() {
        AudioTrack audioTrack = this.f264c;
        if (audioTrack != null) {
            if (audioTrack.getState() == 1) {
                this.f264c.stop();
            }
            this.f264c.release();
            this.f264c = null;
        }
        this.f266e = null;
        this.f267f = null;
        this.f265d = false;
    }

    public synchronized void close() {
        stop();
    }

    /* access modifiers changed from: package-private */
    public native int fmodProcessMicData(ByteBuffer byteBuffer, int i);

    public boolean isRunning() {
        return this.f262a != null && this.f262a.isAlive();
    }

    public void run() {
        int i = 3;
        while (this.f263b) {
            if (!this.f265d && i > 0) {
                releaseAudioTrack();
                int fmodGetInfo = fmodGetInfo(f258h);
                int round = Math.round(((float) AudioTrack.getMinBufferSize(fmodGetInfo, 12, 2)) * 1.1f) & -4;
                int fmodGetInfo2 = fmodGetInfo(f259i);
                int fmodGetInfo3 = fmodGetInfo(f260j) * fmodGetInfo2 * 4;
                AudioTrack audioTrack = new AudioTrack(3, fmodGetInfo, 12, 2, fmodGetInfo3 > round ? fmodGetInfo3 : round, 1);
                this.f264c = audioTrack;
                boolean z = audioTrack.getState() == 1;
                this.f265d = z;
                if (z) {
                    ByteBuffer allocateDirect = ByteBuffer.allocateDirect(fmodGetInfo2 * 2 * 2);
                    this.f266e = allocateDirect;
                    this.f267f = new byte[allocateDirect.capacity()];
                    this.f264c.play();
                    i = 3;
                } else {
                    Log.e("FMOD", "AudioTrack failed to initialize (status " + this.f264c.getState() + ")");
                    releaseAudioTrack();
                    i += -1;
                }
            }
            if (this.f265d) {
                if (fmodGetInfo(f261k) == 1) {
                    fmodProcess(this.f266e);
                    ByteBuffer byteBuffer = this.f266e;
                    byteBuffer.get(this.f267f, 0, byteBuffer.capacity());
                    this.f264c.write(this.f267f, 0, this.f266e.capacity());
                    this.f266e.position(0);
                } else {
                    releaseAudioTrack();
                }
            }
        }
        releaseAudioTrack();
    }

    public synchronized void start() {
        if (this.f262a != null) {
            stop();
        }
        this.f262a = new Thread(this, "FMODAudioDevice");
        this.f262a.setPriority(10);
        this.f263b = true;
        this.f262a.start();
        if (this.f268g != null) {
            this.f268g.mo285b();
        }
    }

    public synchronized int startAudioRecord(int i, int i2, int i3) {
        if (this.f268g == null) {
            this.f268g = new C0082a(this, i, i2);
            this.f268g.mo285b();
        }
        return this.f268g.mo284a();
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:1:0x0001 */
    /* JADX WARNING: Removed duplicated region for block: B:1:0x0001 A[LOOP:0: B:1:0x0001->B:16:0x0001, LOOP_START, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void stop() {
        /*
            r1 = this;
            monitor-enter(r1)
        L_0x0001:
            java.lang.Thread r0 = r1.f262a     // Catch:{ all -> 0x001c }
            if (r0 == 0) goto L_0x0011
            r0 = 0
            r1.f263b = r0     // Catch:{ all -> 0x001c }
            java.lang.Thread r0 = r1.f262a     // Catch:{ InterruptedException -> 0x0001 }
            r0.join()     // Catch:{ InterruptedException -> 0x0001 }
            r0 = 0
            r1.f262a = r0     // Catch:{ InterruptedException -> 0x0001 }
            goto L_0x0001
        L_0x0011:
            org.fmod.a r0 = r1.f268g     // Catch:{ all -> 0x001c }
            if (r0 == 0) goto L_0x001a
            org.fmod.a r0 = r1.f268g     // Catch:{ all -> 0x001c }
            r0.mo286c()     // Catch:{ all -> 0x001c }
        L_0x001a:
            monitor-exit(r1)
            return
        L_0x001c:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fmod.FMODAudioDevice.stop():void");
    }

    public synchronized void stopAudioRecord() {
        if (this.f268g != null) {
            this.f268g.mo286c();
            this.f268g = null;
        }
    }
}
