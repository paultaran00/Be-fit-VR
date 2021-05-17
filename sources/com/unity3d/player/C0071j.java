package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.MediaController;

/* renamed from: com.unity3d.player.j */
public final class C0071j extends FrameLayout implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, SurfaceHolder.Callback, MediaController.MediaPlayerControl {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static boolean f207a = false;

    /* renamed from: b */
    private final Context f208b;

    /* renamed from: c */
    private final SurfaceView f209c;

    /* renamed from: d */
    private final SurfaceHolder f210d;

    /* renamed from: e */
    private final String f211e;

    /* renamed from: f */
    private final int f212f;

    /* renamed from: g */
    private final int f213g;

    /* renamed from: h */
    private final boolean f214h;

    /* renamed from: i */
    private final long f215i;

    /* renamed from: j */
    private final long f216j;

    /* renamed from: k */
    private final FrameLayout f217k;

    /* renamed from: l */
    private final Display f218l;

    /* renamed from: m */
    private int f219m;

    /* renamed from: n */
    private int f220n;

    /* renamed from: o */
    private int f221o;

    /* renamed from: p */
    private int f222p;

    /* renamed from: q */
    private MediaPlayer f223q;

    /* renamed from: r */
    private MediaController f224r;

    /* renamed from: s */
    private boolean f225s = false;

    /* renamed from: t */
    private boolean f226t = false;

    /* renamed from: u */
    private int f227u = 0;

    /* renamed from: v */
    private boolean f228v = false;

    /* renamed from: w */
    private boolean f229w = false;

    /* renamed from: x */
    private C0072a f230x;

    /* renamed from: y */
    private C0073b f231y;

    /* renamed from: z */
    private volatile int f232z = 0;

    /* renamed from: com.unity3d.player.j$a */
    public interface C0072a {
        /* renamed from: a */
        void mo263a(int i);
    }

    /* renamed from: com.unity3d.player.j$b */
    public class C0073b implements Runnable {

        /* renamed from: b */
        private C0071j f234b;

        /* renamed from: c */
        private boolean f235c = false;

        public C0073b(C0071j jVar) {
            this.f234b = jVar;
        }

        /* renamed from: a */
        public final void mo264a() {
            this.f235c = true;
        }

        public final void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
            if (!this.f235c) {
                if (C0071j.f207a) {
                    C0071j.m121b("Stopping the video player due to timeout.");
                }
                this.f234b.CancelOnPrepare();
            }
        }
    }

    protected C0071j(Context context, String str, int i, int i2, int i3, boolean z, long j, long j2, C0072a aVar) {
        super(context);
        this.f230x = aVar;
        this.f208b = context;
        this.f217k = this;
        SurfaceView surfaceView = new SurfaceView(context);
        this.f209c = surfaceView;
        SurfaceHolder holder = surfaceView.getHolder();
        this.f210d = holder;
        holder.addCallback(this);
        this.f217k.setBackgroundColor(i);
        this.f217k.addView(this.f209c);
        this.f218l = ((WindowManager) this.f208b.getSystemService("window")).getDefaultDisplay();
        this.f211e = str;
        this.f212f = i2;
        this.f213g = i3;
        this.f214h = z;
        this.f215i = j;
        this.f216j = j2;
        if (f207a) {
            m121b("fileName: " + this.f211e);
        }
        if (f207a) {
            m121b("backgroundColor: " + i);
        }
        if (f207a) {
            m121b("controlMode: " + this.f212f);
        }
        if (f207a) {
            m121b("scalingMode: " + this.f213g);
        }
        if (f207a) {
            m121b("isURL: " + this.f214h);
        }
        if (f207a) {
            m121b("videoOffset: " + this.f215i);
        }
        if (f207a) {
            m121b("videoLength: " + this.f216j);
        }
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    /* renamed from: a */
    private void m119a(int i) {
        this.f232z = i;
        C0072a aVar = this.f230x;
        if (aVar != null) {
            aVar.mo263a(this.f232z);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static void m121b(String str) {
        Log.i("Video", "VideoPlayer: " + str);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:17|18|19|20|21) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x007d */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m123c() {
        /*
            r8 = this;
            android.media.MediaPlayer r0 = r8.f223q
            if (r0 == 0) goto L_0x001c
            android.view.SurfaceHolder r1 = r8.f210d
            r0.setDisplay(r1)
            boolean r0 = r8.f228v
            if (r0 != 0) goto L_0x001b
            boolean r0 = f207a
            if (r0 == 0) goto L_0x0016
            java.lang.String r0 = "Resuming playback"
            m121b(r0)
        L_0x0016:
            android.media.MediaPlayer r0 = r8.f223q
            r0.start()
        L_0x001b:
            return
        L_0x001c:
            r0 = 0
            r8.m119a((int) r0)
            r8.doCleanUp()
            android.media.MediaPlayer r0 = new android.media.MediaPlayer     // Catch:{ Exception -> 0x00cc }
            r0.<init>()     // Catch:{ Exception -> 0x00cc }
            r8.f223q = r0     // Catch:{ Exception -> 0x00cc }
            boolean r1 = r8.f214h     // Catch:{ Exception -> 0x00cc }
            if (r1 == 0) goto L_0x003a
            android.content.Context r1 = r8.f208b     // Catch:{ Exception -> 0x00cc }
            java.lang.String r2 = r8.f211e     // Catch:{ Exception -> 0x00cc }
            android.net.Uri r2 = android.net.Uri.parse(r2)     // Catch:{ Exception -> 0x00cc }
            r0.setDataSource(r1, r2)     // Catch:{ Exception -> 0x00cc }
            goto L_0x008e
        L_0x003a:
            long r0 = r8.f216j     // Catch:{ Exception -> 0x00cc }
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L_0x005a
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ Exception -> 0x00cc }
            java.lang.String r1 = r8.f211e     // Catch:{ Exception -> 0x00cc }
            r0.<init>(r1)     // Catch:{ Exception -> 0x00cc }
            android.media.MediaPlayer r2 = r8.f223q     // Catch:{ Exception -> 0x00cc }
            java.io.FileDescriptor r3 = r0.getFD()     // Catch:{ Exception -> 0x00cc }
            long r4 = r8.f215i     // Catch:{ Exception -> 0x00cc }
            long r6 = r8.f216j     // Catch:{ Exception -> 0x00cc }
            r2.setDataSource(r3, r4, r6)     // Catch:{ Exception -> 0x00cc }
        L_0x0056:
            r0.close()     // Catch:{ Exception -> 0x00cc }
            goto L_0x008e
        L_0x005a:
            android.content.res.Resources r0 = r8.getResources()     // Catch:{ Exception -> 0x00cc }
            android.content.res.AssetManager r0 = r0.getAssets()     // Catch:{ Exception -> 0x00cc }
            java.lang.String r1 = r8.f211e     // Catch:{ IOException -> 0x007d }
            android.content.res.AssetFileDescriptor r0 = r0.openFd(r1)     // Catch:{ IOException -> 0x007d }
            android.media.MediaPlayer r1 = r8.f223q     // Catch:{ IOException -> 0x007d }
            java.io.FileDescriptor r2 = r0.getFileDescriptor()     // Catch:{ IOException -> 0x007d }
            long r3 = r0.getStartOffset()     // Catch:{ IOException -> 0x007d }
            long r5 = r0.getLength()     // Catch:{ IOException -> 0x007d }
            r1.setDataSource(r2, r3, r5)     // Catch:{ IOException -> 0x007d }
            r0.close()     // Catch:{ IOException -> 0x007d }
            goto L_0x008e
        L_0x007d:
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ Exception -> 0x00cc }
            java.lang.String r1 = r8.f211e     // Catch:{ Exception -> 0x00cc }
            r0.<init>(r1)     // Catch:{ Exception -> 0x00cc }
            android.media.MediaPlayer r1 = r8.f223q     // Catch:{ Exception -> 0x00cc }
            java.io.FileDescriptor r2 = r0.getFD()     // Catch:{ Exception -> 0x00cc }
            r1.setDataSource(r2)     // Catch:{ Exception -> 0x00cc }
            goto L_0x0056
        L_0x008e:
            android.media.MediaPlayer r0 = r8.f223q     // Catch:{ Exception -> 0x00cc }
            android.view.SurfaceHolder r1 = r8.f210d     // Catch:{ Exception -> 0x00cc }
            r0.setDisplay(r1)     // Catch:{ Exception -> 0x00cc }
            android.media.MediaPlayer r0 = r8.f223q     // Catch:{ Exception -> 0x00cc }
            r1 = 1
            r0.setScreenOnWhilePlaying(r1)     // Catch:{ Exception -> 0x00cc }
            android.media.MediaPlayer r0 = r8.f223q     // Catch:{ Exception -> 0x00cc }
            r0.setOnBufferingUpdateListener(r8)     // Catch:{ Exception -> 0x00cc }
            android.media.MediaPlayer r0 = r8.f223q     // Catch:{ Exception -> 0x00cc }
            r0.setOnCompletionListener(r8)     // Catch:{ Exception -> 0x00cc }
            android.media.MediaPlayer r0 = r8.f223q     // Catch:{ Exception -> 0x00cc }
            r0.setOnPreparedListener(r8)     // Catch:{ Exception -> 0x00cc }
            android.media.MediaPlayer r0 = r8.f223q     // Catch:{ Exception -> 0x00cc }
            r0.setOnVideoSizeChangedListener(r8)     // Catch:{ Exception -> 0x00cc }
            android.media.MediaPlayer r0 = r8.f223q     // Catch:{ Exception -> 0x00cc }
            r1 = 3
            r0.setAudioStreamType(r1)     // Catch:{ Exception -> 0x00cc }
            android.media.MediaPlayer r0 = r8.f223q     // Catch:{ Exception -> 0x00cc }
            r0.prepareAsync()     // Catch:{ Exception -> 0x00cc }
            com.unity3d.player.j$b r0 = new com.unity3d.player.j$b     // Catch:{ Exception -> 0x00cc }
            r0.<init>(r8)     // Catch:{ Exception -> 0x00cc }
            r8.f231y = r0     // Catch:{ Exception -> 0x00cc }
            java.lang.Thread r0 = new java.lang.Thread     // Catch:{ Exception -> 0x00cc }
            com.unity3d.player.j$b r1 = r8.f231y     // Catch:{ Exception -> 0x00cc }
            r0.<init>(r1)     // Catch:{ Exception -> 0x00cc }
            r0.start()     // Catch:{ Exception -> 0x00cc }
            return
        L_0x00cc:
            r0 = move-exception
            boolean r1 = f207a
            if (r1 == 0) goto L_0x00e9
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "error: "
            r1.<init>(r2)
            java.lang.String r2 = r0.getMessage()
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            m121b(r0)
        L_0x00e9:
            r0 = 2
            r8.m119a((int) r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.C0071j.m123c():void");
    }

    /* renamed from: d */
    private void m124d() {
        if (!isPlaying()) {
            m119a(1);
            if (f207a) {
                m121b("startVideoPlayback");
            }
            updateVideoLayout();
            if (!this.f228v) {
                start();
            }
        }
    }

    public final void CancelOnPrepare() {
        m119a(2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo239a() {
        return this.f228v;
    }

    public final boolean canPause() {
        return true;
    }

    public final boolean canSeekBackward() {
        return true;
    }

    public final boolean canSeekForward() {
        return true;
    }

    /* access modifiers changed from: protected */
    public final void destroyPlayer() {
        if (f207a) {
            m121b("destroyPlayer");
        }
        if (!this.f228v) {
            pause();
        }
        doCleanUp();
    }

    /* access modifiers changed from: protected */
    public final void doCleanUp() {
        C0073b bVar = this.f231y;
        if (bVar != null) {
            bVar.mo264a();
            this.f231y = null;
        }
        MediaPlayer mediaPlayer = this.f223q;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.f223q = null;
        }
        this.f221o = 0;
        this.f222p = 0;
        this.f226t = false;
        this.f225s = false;
    }

    public final int getAudioSessionId() {
        MediaPlayer mediaPlayer = this.f223q;
        if (mediaPlayer == null) {
            return 0;
        }
        return mediaPlayer.getAudioSessionId();
    }

    public final int getBufferPercentage() {
        if (this.f214h) {
            return this.f227u;
        }
        return 100;
    }

    public final int getCurrentPosition() {
        MediaPlayer mediaPlayer = this.f223q;
        if (mediaPlayer == null) {
            return 0;
        }
        return mediaPlayer.getCurrentPosition();
    }

    public final int getDuration() {
        MediaPlayer mediaPlayer = this.f223q;
        if (mediaPlayer == null) {
            return 0;
        }
        return mediaPlayer.getDuration();
    }

    public final boolean isPlaying() {
        boolean z = this.f226t && this.f225s;
        MediaPlayer mediaPlayer = this.f223q;
        return mediaPlayer == null ? !z : mediaPlayer.isPlaying() || !z;
    }

    public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        if (f207a) {
            m121b("onBufferingUpdate percent:" + i);
        }
        this.f227u = i;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        if (f207a) {
            m121b("onCompletion called");
        }
        destroyPlayer();
        m119a(3);
    }

    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 || (this.f212f == 2 && i != 0 && !keyEvent.isSystem())) {
            destroyPlayer();
            m119a(3);
            return true;
        }
        MediaController mediaController = this.f224r;
        return mediaController != null ? mediaController.onKeyDown(i, keyEvent) : super.onKeyDown(i, keyEvent);
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        if (f207a) {
            m121b("onPrepared called");
        }
        C0073b bVar = this.f231y;
        if (bVar != null) {
            bVar.mo264a();
            this.f231y = null;
        }
        int i = this.f212f;
        if (i == 0 || i == 1) {
            MediaController mediaController = new MediaController(this.f208b);
            this.f224r = mediaController;
            mediaController.setMediaPlayer(this);
            this.f224r.setAnchorView(this);
            this.f224r.setEnabled(true);
            Context context = this.f208b;
            if (context instanceof Activity) {
                this.f224r.setSystemUiVisibility(((Activity) context).getWindow().getDecorView().getSystemUiVisibility());
            }
            this.f224r.show();
        }
        this.f226t = true;
        if (1 != 0 && this.f225s) {
            m124d();
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (this.f212f == 2 && action == 0) {
            destroyPlayer();
            m119a(3);
            return true;
        }
        MediaController mediaController = this.f224r;
        return mediaController != null ? mediaController.onTouchEvent(motionEvent) : super.onTouchEvent(motionEvent);
    }

    public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        if (f207a) {
            m121b("onVideoSizeChanged called " + i + "x" + i2);
        }
        if (i != 0 && i2 != 0) {
            this.f225s = true;
            this.f221o = i;
            this.f222p = i2;
            if (this.f226t && 1 != 0) {
                m124d();
            }
        } else if (f207a) {
            m121b("invalid video width(" + i + ") or height(" + i2 + ")");
        }
    }

    public final void pause() {
        MediaPlayer mediaPlayer = this.f223q;
        if (mediaPlayer != null) {
            if (this.f229w) {
                mediaPlayer.pause();
            }
            this.f228v = true;
        }
    }

    public final void seekTo(int i) {
        MediaPlayer mediaPlayer = this.f223q;
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(i);
        }
    }

    public final void start() {
        if (f207a) {
            m121b("Start");
        }
        MediaPlayer mediaPlayer = this.f223q;
        if (mediaPlayer != null) {
            if (this.f229w) {
                mediaPlayer.start();
            }
            this.f228v = false;
        }
    }

    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        if (f207a) {
            m121b("surfaceChanged called " + i + " " + i2 + "x" + i3);
        }
        if (this.f219m != i2 || this.f220n != i3) {
            this.f219m = i2;
            this.f220n = i3;
            if (this.f229w) {
                updateVideoLayout();
            }
        }
    }

    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (f207a) {
            m121b("surfaceCreated called");
        }
        this.f229w = true;
        m123c();
    }

    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (f207a) {
            m121b("surfaceDestroyed called");
        }
        this.f229w = false;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004d, code lost:
        if (r5 <= r3) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0053, code lost:
        r0 = (int) (((float) r1) * r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005c, code lost:
        if (r5 >= r3) goto L_0x004f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void updateVideoLayout() {
        /*
            r8 = this;
            boolean r0 = f207a
            if (r0 == 0) goto L_0x0009
            java.lang.String r0 = "updateVideoLayout"
            m121b(r0)
        L_0x0009:
            android.media.MediaPlayer r0 = r8.f223q
            if (r0 != 0) goto L_0x000e
            return
        L_0x000e:
            int r0 = r8.f219m
            if (r0 == 0) goto L_0x0016
            int r0 = r8.f220n
            if (r0 != 0) goto L_0x0034
        L_0x0016:
            android.content.Context r0 = r8.f208b
            java.lang.String r1 = "window"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.view.WindowManager r0 = (android.view.WindowManager) r0
            android.util.DisplayMetrics r1 = new android.util.DisplayMetrics
            r1.<init>()
            android.view.Display r0 = r0.getDefaultDisplay()
            r0.getMetrics(r1)
            int r0 = r1.widthPixels
            r8.f219m = r0
            int r0 = r1.heightPixels
            r8.f220n = r0
        L_0x0034:
            int r0 = r8.f219m
            int r1 = r8.f220n
            boolean r2 = r8.f225s
            if (r2 == 0) goto L_0x0064
            int r2 = r8.f221o
            float r3 = (float) r2
            int r4 = r8.f222p
            float r5 = (float) r4
            float r3 = r3 / r5
            float r5 = (float) r0
            float r6 = (float) r1
            float r5 = r5 / r6
            int r6 = r8.f213g
            r7 = 1
            if (r6 != r7) goto L_0x0057
            int r2 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r2 > 0) goto L_0x0053
        L_0x004f:
            float r1 = (float) r0
            float r1 = r1 / r3
            int r1 = (int) r1
            goto L_0x006d
        L_0x0053:
            float r0 = (float) r1
            float r0 = r0 * r3
            int r0 = (int) r0
            goto L_0x006d
        L_0x0057:
            r7 = 2
            if (r6 != r7) goto L_0x005f
            int r2 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r2 < 0) goto L_0x0053
            goto L_0x004f
        L_0x005f:
            if (r6 != 0) goto L_0x006d
            r0 = r2
            r1 = r4
            goto L_0x006d
        L_0x0064:
            boolean r2 = f207a
            if (r2 == 0) goto L_0x006d
            java.lang.String r2 = "updateVideoLayout: Video size is not known yet"
            m121b(r2)
        L_0x006d:
            int r2 = r8.f219m
            if (r2 != r0) goto L_0x0075
            int r2 = r8.f220n
            if (r2 == r1) goto L_0x00a0
        L_0x0075:
            boolean r2 = f207a
            if (r2 == 0) goto L_0x0092
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "frameWidth = "
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r3 = "; frameHeight = "
            r2.append(r3)
            r2.append(r1)
            java.lang.String r2 = r2.toString()
            m121b(r2)
        L_0x0092:
            android.widget.FrameLayout$LayoutParams r2 = new android.widget.FrameLayout$LayoutParams
            r3 = 17
            r2.<init>(r0, r1, r3)
            android.widget.FrameLayout r0 = r8.f217k
            android.view.SurfaceView r1 = r8.f209c
            r0.updateViewLayout(r1, r2)
        L_0x00a0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.C0071j.updateVideoLayout():void");
    }
}
