package com.unity3d.player;

import android.content.Context;
import com.unity3d.player.C0047a;

public class AudioVolumeHandler implements C0047a.C0049b {

    /* renamed from: a */
    private C0047a f12a;

    AudioVolumeHandler(Context context) {
        C0047a aVar = new C0047a(context);
        this.f12a = aVar;
        aVar.mo183a(this);
    }

    /* renamed from: a */
    public final void mo28a() {
        this.f12a.mo182a();
        this.f12a = null;
    }

    public final native void onAudioVolumeChanged(int i);
}
