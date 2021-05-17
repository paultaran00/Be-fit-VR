package org.fmod;

import android.media.AudioRecord;
import android.util.Log;
import java.nio.ByteBuffer;

/* renamed from: org.fmod.a */
final class C0082a implements Runnable {

    /* renamed from: a */
    private final FMODAudioDevice f269a;

    /* renamed from: b */
    private final ByteBuffer f270b;

    /* renamed from: c */
    private final int f271c;

    /* renamed from: d */
    private final int f272d;

    /* renamed from: e */
    private final int f273e = 2;

    /* renamed from: f */
    private volatile Thread f274f;

    /* renamed from: g */
    private volatile boolean f275g;

    /* renamed from: h */
    private AudioRecord f276h;

    /* renamed from: i */
    private boolean f277i;

    C0082a(FMODAudioDevice fMODAudioDevice, int i, int i2) {
        this.f269a = fMODAudioDevice;
        this.f271c = i;
        this.f272d = i2;
        this.f270b = ByteBuffer.allocateDirect(AudioRecord.getMinBufferSize(i, i2, 2));
    }

    /* renamed from: d */
    private void m145d() {
        AudioRecord audioRecord = this.f276h;
        if (audioRecord != null) {
            if (audioRecord.getState() == 1) {
                this.f276h.stop();
            }
            this.f276h.release();
            this.f276h = null;
        }
        this.f270b.position(0);
        this.f277i = false;
    }

    /* renamed from: a */
    public final int mo284a() {
        return this.f270b.capacity();
    }

    /* renamed from: b */
    public final void mo285b() {
        if (this.f274f != null) {
            mo286c();
        }
        this.f275g = true;
        this.f274f = new Thread(this);
        this.f274f.start();
    }

    /* renamed from: c */
    public final void mo286c() {
        while (this.f274f != null) {
            this.f275g = false;
            try {
                this.f274f.join();
                this.f274f = null;
            } catch (InterruptedException unused) {
            }
        }
    }

    public final void run() {
        int i = 3;
        while (this.f275g) {
            if (!this.f277i && i > 0) {
                m145d();
                AudioRecord audioRecord = new AudioRecord(1, this.f271c, this.f272d, this.f273e, this.f270b.capacity());
                this.f276h = audioRecord;
                int state = audioRecord.getState();
                boolean z = true;
                if (state != 1) {
                    z = false;
                }
                this.f277i = z;
                if (z) {
                    this.f270b.position(0);
                    this.f276h.startRecording();
                    i = 3;
                } else {
                    Log.e("FMOD", "AudioRecord failed to initialize (status " + this.f276h.getState() + ")");
                    i += -1;
                    m145d();
                }
            }
            if (this.f277i && this.f276h.getRecordingState() == 3) {
                AudioRecord audioRecord2 = this.f276h;
                ByteBuffer byteBuffer = this.f270b;
                this.f269a.fmodProcessMicData(this.f270b, audioRecord2.read(byteBuffer, byteBuffer.capacity()));
                this.f270b.position(0);
            }
        }
        m145d();
    }
}
