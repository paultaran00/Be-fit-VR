package com.unity3d.player;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;

/* renamed from: com.unity3d.player.a */
final class C0047a {

    /* renamed from: a */
    private final Context f126a;

    /* renamed from: b */
    private final AudioManager f127b;

    /* renamed from: c */
    private C0048a f128c;

    /* renamed from: com.unity3d.player.a$a */
    private class C0048a extends ContentObserver {

        /* renamed from: b */
        private final C0049b f130b;

        /* renamed from: c */
        private final AudioManager f131c;

        /* renamed from: d */
        private final int f132d = 3;

        /* renamed from: e */
        private int f133e;

        public C0048a(Handler handler, AudioManager audioManager, int i, C0049b bVar) {
            super(handler);
            this.f131c = audioManager;
            this.f130b = bVar;
            this.f133e = audioManager.getStreamVolume(3);
        }

        public final boolean deliverSelfNotifications() {
            return super.deliverSelfNotifications();
        }

        public final void onChange(boolean z, Uri uri) {
            int streamVolume;
            AudioManager audioManager = this.f131c;
            if (audioManager != null && this.f130b != null && (streamVolume = audioManager.getStreamVolume(this.f132d)) != this.f133e) {
                this.f133e = streamVolume;
                this.f130b.onAudioVolumeChanged(streamVolume);
            }
        }
    }

    /* renamed from: com.unity3d.player.a$b */
    public interface C0049b {
        void onAudioVolumeChanged(int i);
    }

    public C0047a(Context context) {
        this.f126a = context;
        this.f127b = (AudioManager) context.getSystemService("audio");
    }

    /* renamed from: a */
    public final void mo182a() {
        if (this.f128c != null) {
            this.f126a.getContentResolver().unregisterContentObserver(this.f128c);
            this.f128c = null;
        }
    }

    /* renamed from: a */
    public final void mo183a(C0049b bVar) {
        this.f128c = new C0048a(new Handler(), this.f127b, 3, bVar);
        this.f126a.getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, this.f128c);
    }
}
