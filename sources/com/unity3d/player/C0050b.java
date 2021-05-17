package com.unity3d.player;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Range;
import android.util.Size;
import android.util.SizeF;
import android.view.Surface;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* renamed from: com.unity3d.player.b */
public final class C0050b {

    /* renamed from: b */
    private static CameraManager f134b;

    /* renamed from: c */
    private static String[] f135c;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public static Semaphore f136e = new Semaphore(1);

    /* renamed from: A */
    private CameraCaptureSession.CaptureCallback f137A = new CameraCaptureSession.CaptureCallback() {
        public final void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
            C0050b.this.m60a(captureRequest.getTag());
        }

        public final void onCaptureFailed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureFailure captureFailure) {
            C0058d.Log(5, "Camera2: Capture session failed " + captureRequest.getTag() + " reason " + captureFailure.getReason());
            C0050b.this.m60a(captureRequest.getTag());
        }

        public final void onCaptureSequenceAborted(CameraCaptureSession cameraCaptureSession, int i) {
        }

        public final void onCaptureSequenceCompleted(CameraCaptureSession cameraCaptureSession, int i, long j) {
        }
    };

    /* renamed from: B */
    private final CameraDevice.StateCallback f138B = new CameraDevice.StateCallback() {
        public final void onClosed(CameraDevice cameraDevice) {
            C0050b.f136e.release();
        }

        public final void onDisconnected(CameraDevice cameraDevice) {
            C0058d.Log(5, "Camera2: CameraDevice disconnected.");
            C0050b.this.m58a(cameraDevice);
            C0050b.f136e.release();
        }

        public final void onError(CameraDevice cameraDevice, int i) {
            C0058d.Log(6, "Camera2: Error opeining CameraDevice " + i);
            C0050b.this.m58a(cameraDevice);
            C0050b.f136e.release();
        }

        public final void onOpened(CameraDevice cameraDevice) {
            CameraDevice unused = C0050b.this.f142d = cameraDevice;
            C0050b.f136e.release();
        }
    };

    /* renamed from: C */
    private final ImageReader.OnImageAvailableListener f139C = new ImageReader.OnImageAvailableListener() {
        public final void onImageAvailable(ImageReader imageReader) {
            if (C0050b.f136e.tryAcquire()) {
                Image acquireNextImage = imageReader.acquireNextImage();
                if (acquireNextImage != null) {
                    Image.Plane[] planes = acquireNextImage.getPlanes();
                    if (acquireNextImage.getFormat() == 35 && planes != null && planes.length == 3) {
                        C0057c h = C0050b.this.f141a;
                        ByteBuffer buffer = planes[0].getBuffer();
                        ByteBuffer buffer2 = planes[1].getBuffer();
                        ByteBuffer buffer3 = planes[2].getBuffer();
                        h.mo32a(buffer, buffer2, buffer3, planes[0].getRowStride(), planes[1].getRowStride(), planes[1].getPixelStride());
                    } else {
                        C0058d.Log(6, "Camera2: Wrong image format.");
                    }
                    if (C0050b.this.f156s != null) {
                        C0050b.this.f156s.close();
                    }
                    Image unused = C0050b.this.f156s = acquireNextImage;
                }
                C0050b.f136e.release();
            }
        }
    };

    /* renamed from: D */
    private final SurfaceTexture.OnFrameAvailableListener f140D = new SurfaceTexture.OnFrameAvailableListener() {
        public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
            C0050b.this.f141a.mo31a(surfaceTexture);
        }
    };
    /* access modifiers changed from: private */

    /* renamed from: a */
    public C0057c f141a = null;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public CameraDevice f142d;

    /* renamed from: f */
    private HandlerThread f143f;

    /* renamed from: g */
    private Handler f144g;

    /* renamed from: h */
    private Rect f145h;

    /* renamed from: i */
    private Rect f146i;

    /* renamed from: j */
    private int f147j;

    /* renamed from: k */
    private int f148k;

    /* renamed from: l */
    private float f149l = -1.0f;

    /* renamed from: m */
    private float f150m = -1.0f;

    /* renamed from: n */
    private int f151n;

    /* renamed from: o */
    private int f152o;

    /* renamed from: p */
    private boolean f153p = false;
    /* access modifiers changed from: private */

    /* renamed from: q */
    public Range f154q;
    /* access modifiers changed from: private */

    /* renamed from: r */
    public ImageReader f155r = null;
    /* access modifiers changed from: private */

    /* renamed from: s */
    public Image f156s;
    /* access modifiers changed from: private */

    /* renamed from: t */
    public CaptureRequest.Builder f157t;
    /* access modifiers changed from: private */

    /* renamed from: u */
    public CameraCaptureSession f158u = null;
    /* access modifiers changed from: private */

    /* renamed from: v */
    public Object f159v = new Object();

    /* renamed from: w */
    private int f160w;

    /* renamed from: x */
    private SurfaceTexture f161x;
    /* access modifiers changed from: private */

    /* renamed from: y */
    public Surface f162y = null;

    /* renamed from: z */
    private int f163z = C0056a.f171c;

    /* renamed from: com.unity3d.player.b$a */
    private enum C0056a {
        ;

        static {
            f172d = new int[]{1, 2, 3};
        }
    }

    protected C0050b(C0057c cVar) {
        this.f141a = cVar;
        m76g();
    }

    /* renamed from: a */
    public static int m49a(Context context) {
        return m69c(context).length;
    }

    /* renamed from: a */
    public static int m50a(Context context, int i) {
        try {
            return ((Integer) m62b(context).getCameraCharacteristics(m69c(context)[i]).get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
        } catch (CameraAccessException e) {
            C0058d.Log(6, "Camera2: CameraAccessException " + e);
            return 0;
        }
    }

    /* renamed from: a */
    private static int m51a(Range[] rangeArr, int i) {
        int i2 = -1;
        double d = Double.MAX_VALUE;
        for (int i3 = 0; i3 < rangeArr.length; i3++) {
            int intValue = ((Integer) rangeArr[i3].getLower()).intValue();
            int intValue2 = ((Integer) rangeArr[i3].getUpper()).intValue();
            float f = (float) i;
            if (f + 0.1f > ((float) intValue) && f - 0.1f < ((float) intValue2)) {
                return i;
            }
            double min = (double) ((float) Math.min(Math.abs(i - intValue), Math.abs(i - intValue2)));
            if (min < d) {
                i2 = i3;
                d = min;
            }
        }
        return ((Integer) (i > ((Integer) rangeArr[i2].getUpper()).intValue() ? rangeArr[i2].getUpper() : rangeArr[i2].getLower())).intValue();
    }

    /* renamed from: a */
    private static Rect m52a(Size[] sizeArr, double d, double d2) {
        Size[] sizeArr2 = sizeArr;
        double d3 = Double.MAX_VALUE;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < sizeArr2.length; i3++) {
            int width = sizeArr2[i3].getWidth();
            int height = sizeArr2[i3].getHeight();
            double abs = Math.abs(Math.log(d / ((double) width))) + Math.abs(Math.log(d2 / ((double) height)));
            if (abs < d3) {
                i = width;
                i2 = height;
                d3 = abs;
            }
        }
        return new Rect(0, 0, i, i2);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m58a(CameraDevice cameraDevice) {
        synchronized (this.f159v) {
            this.f158u = null;
        }
        cameraDevice.close();
        this.f142d = null;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m60a(Object obj) {
        if (obj == "Focus") {
            this.f153p = false;
            synchronized (this.f159v) {
                if (this.f158u != null) {
                    try {
                        this.f157t.set(CaptureRequest.CONTROL_AF_TRIGGER, 0);
                        this.f157t.setTag("Regular");
                        this.f158u.setRepeatingRequest(this.f157t.build(), this.f137A, this.f144g);
                    } catch (CameraAccessException e) {
                        C0058d.Log(6, "Camera2: CameraAccessException " + e);
                    }
                }
            }
        } else if (obj == "Cancel focus") {
            synchronized (this.f159v) {
                if (this.f158u != null) {
                    m82j();
                }
            }
        }
    }

    /* renamed from: a */
    private static Size[] m61a(CameraCharacteristics cameraCharacteristics) {
        StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (streamConfigurationMap == null) {
            C0058d.Log(6, "Camera2: configuration map is not available.");
            return null;
        }
        Size[] outputSizes = streamConfigurationMap.getOutputSizes(35);
        if (outputSizes == null || outputSizes.length == 0) {
            return null;
        }
        return outputSizes;
    }

    /* renamed from: b */
    private static CameraManager m62b(Context context) {
        if (f134b == null) {
            f134b = (CameraManager) context.getSystemService("camera");
        }
        return f134b;
    }

    /* renamed from: b */
    private void m64b(CameraCharacteristics cameraCharacteristics) {
        int intValue = ((Integer) cameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF)).intValue();
        this.f148k = intValue;
        if (intValue > 0) {
            Rect rect = (Rect) cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
            this.f146i = rect;
            float width = ((float) rect.width()) / ((float) this.f146i.height());
            float width2 = ((float) this.f145h.width()) / ((float) this.f145h.height());
            if (width2 > width) {
                this.f151n = 0;
                this.f152o = (int) ((((float) this.f146i.height()) - (((float) this.f146i.width()) / width2)) / 2.0f);
            } else {
                this.f152o = 0;
                this.f151n = (int) ((((float) this.f146i.width()) - (((float) this.f146i.height()) * width2)) / 2.0f);
            }
            this.f147j = Math.min(this.f146i.width(), this.f146i.height()) / 20;
        }
    }

    /* renamed from: b */
    public static boolean m66b(Context context, int i) {
        try {
            return ((Integer) m62b(context).getCameraCharacteristics(m69c(context)[i]).get(CameraCharacteristics.LENS_FACING)).intValue() == 0;
        } catch (CameraAccessException e) {
            C0058d.Log(6, "Camera2: CameraAccessException " + e);
            return false;
        }
    }

    /* renamed from: c */
    public static boolean m68c(Context context, int i) {
        try {
            return ((Integer) m62b(context).getCameraCharacteristics(m69c(context)[i]).get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF)).intValue() > 0;
        } catch (CameraAccessException e) {
            C0058d.Log(6, "Camera2: CameraAccessException " + e);
            return false;
        }
    }

    /* renamed from: c */
    private static String[] m69c(Context context) {
        if (f135c == null) {
            try {
                f135c = m62b(context).getCameraIdList();
            } catch (CameraAccessException e) {
                C0058d.Log(6, "Camera2: CameraAccessException " + e);
                f135c = new String[0];
            }
        }
        return f135c;
    }

    /* renamed from: d */
    public static int m70d(Context context, int i) {
        try {
            CameraCharacteristics cameraCharacteristics = m62b(context).getCameraCharacteristics(m69c(context)[i]);
            float[] fArr = (float[]) cameraCharacteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS);
            SizeF sizeF = (SizeF) cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_PHYSICAL_SIZE);
            if (fArr.length > 0) {
                return (int) ((fArr[0] * 36.0f) / sizeF.getWidth());
            }
        } catch (CameraAccessException e) {
            C0058d.Log(6, "Camera2: CameraAccessException " + e);
        }
        return 0;
    }

    /* renamed from: e */
    public static int[] m73e(Context context, int i) {
        try {
            Size[] a = m61a(m62b(context).getCameraCharacteristics(m69c(context)[i]));
            if (a == null) {
                return null;
            }
            int[] iArr = new int[(a.length * 2)];
            for (int i2 = 0; i2 < a.length; i2++) {
                int i3 = i2 * 2;
                iArr[i3] = a[i2].getWidth();
                iArr[i3 + 1] = a[i2].getHeight();
            }
            return iArr;
        } catch (CameraAccessException e) {
            C0058d.Log(6, "Camera2: CameraAccessException " + e);
            return null;
        }
    }

    /* renamed from: g */
    private void m76g() {
        HandlerThread handlerThread = new HandlerThread("CameraBackground");
        this.f143f = handlerThread;
        handlerThread.start();
        this.f144g = new Handler(this.f143f.getLooper());
    }

    /* renamed from: h */
    private void m79h() {
        this.f143f.quit();
        try {
            this.f143f.join(4000);
            this.f143f = null;
            this.f144g = null;
        } catch (InterruptedException e) {
            this.f143f.interrupt();
            C0058d.Log(6, "Camera2: Interrupted while waiting for the background thread to finish " + e);
        }
    }

    /* renamed from: i */
    private void m81i() {
        try {
            if (!f136e.tryAcquire(4, TimeUnit.SECONDS)) {
                C0058d.Log(5, "Camera2: Timeout waiting to lock camera for closing.");
                return;
            }
            this.f142d.close();
            try {
                if (!f136e.tryAcquire(4, TimeUnit.SECONDS)) {
                    C0058d.Log(5, "Camera2: Timeout waiting to close camera.");
                }
            } catch (InterruptedException e) {
                C0058d.Log(6, "Camera2: Interrupted while waiting to close camera " + e);
            }
            this.f142d = null;
            f136e.release();
        } catch (InterruptedException e2) {
            C0058d.Log(6, "Camera2: Interrupted while trying to lock camera for closing " + e2);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: j */
    public void m82j() {
        try {
            if (this.f148k != 0 && this.f149l >= 0.0f && this.f149l <= 1.0f && this.f150m >= 0.0f) {
                if (this.f150m <= 1.0f) {
                    this.f153p = true;
                    int max = Math.max(this.f147j + 1, Math.min((int) ((((float) (this.f146i.width() - (this.f151n * 2))) * this.f149l) + ((float) this.f151n)), (this.f146i.width() - this.f147j) - 1));
                    int max2 = Math.max(this.f147j + 1, Math.min((int) ((((double) (this.f146i.height() - (this.f152o * 2))) * (1.0d - ((double) this.f150m))) + ((double) this.f152o)), (this.f146i.height() - this.f147j) - 1));
                    this.f157t.set(CaptureRequest.CONTROL_AF_REGIONS, new MeteringRectangle[]{new MeteringRectangle(max - this.f147j, max2 - this.f147j, this.f147j * 2, this.f147j * 2, 999)});
                    this.f157t.set(CaptureRequest.CONTROL_AF_MODE, 1);
                    this.f157t.set(CaptureRequest.CONTROL_AF_TRIGGER, 1);
                    this.f157t.setTag("Focus");
                    this.f158u.capture(this.f157t.build(), this.f137A, this.f144g);
                    return;
                }
            }
            this.f157t.set(CaptureRequest.CONTROL_AF_MODE, 4);
            this.f157t.setTag("Regular");
            if (this.f158u != null) {
                this.f158u.setRepeatingRequest(this.f157t.build(), this.f137A, this.f144g);
            }
        } catch (CameraAccessException e) {
            C0058d.Log(6, "Camera2: CameraAccessException " + e);
        }
    }

    /* renamed from: k */
    private void m83k() {
        try {
            if (this.f158u != null) {
                this.f158u.stopRepeating();
                this.f157t.set(CaptureRequest.CONTROL_AF_TRIGGER, 2);
                this.f157t.set(CaptureRequest.CONTROL_AF_MODE, 0);
                this.f157t.setTag("Cancel focus");
                this.f158u.capture(this.f157t.build(), this.f137A, this.f144g);
            }
        } catch (CameraAccessException e) {
            C0058d.Log(6, "Camera2: CameraAccessException " + e);
        }
    }

    /* renamed from: a */
    public final Rect mo186a() {
        return this.f145h;
    }

    /* renamed from: a */
    public final boolean mo187a(float f, float f2) {
        if (this.f148k <= 0) {
            return false;
        }
        if (!this.f153p) {
            this.f149l = f;
            this.f150m = f2;
            synchronized (this.f159v) {
                if (!(this.f158u == null || this.f163z == C0056a.f170b)) {
                    m83k();
                }
            }
            return true;
        }
        C0058d.Log(5, "Camera2: Setting manual focus point already started.");
        return false;
    }

    /* renamed from: a */
    public final boolean mo188a(Context context, int i, int i2, int i3, int i4, int i5) {
        try {
            CameraCharacteristics cameraCharacteristics = f134b.getCameraCharacteristics(m69c(context)[i]);
            if (((Integer) cameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).intValue() == 2) {
                C0058d.Log(5, "Camera2: only LEGACY hardware level is supported.");
                return false;
            }
            Size[] a = m61a(cameraCharacteristics);
            if (!(a == null || a.length == 0)) {
                this.f145h = m52a(a, (double) i2, (double) i3);
                Range[] rangeArr = (Range[]) cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
                if (rangeArr == null || rangeArr.length == 0) {
                    C0058d.Log(6, "Camera2: target FPS ranges are not avialable.");
                } else {
                    int a2 = m51a(rangeArr, i4);
                    this.f154q = new Range(Integer.valueOf(a2), Integer.valueOf(a2));
                    try {
                        if (!f136e.tryAcquire(4, TimeUnit.SECONDS)) {
                            C0058d.Log(5, "Camera2: Timeout waiting to lock camera for opening.");
                            return false;
                        }
                        try {
                            f134b.openCamera(m69c(context)[i], this.f138B, this.f144g);
                            try {
                                if (!f136e.tryAcquire(4, TimeUnit.SECONDS)) {
                                    C0058d.Log(5, "Camera2: Timeout waiting to open camera.");
                                    return false;
                                }
                                f136e.release();
                                this.f160w = i5;
                                m64b(cameraCharacteristics);
                                return this.f142d != null;
                            } catch (InterruptedException e) {
                                C0058d.Log(6, "Camera2: Interrupted while waiting to open camera " + e);
                            }
                        } catch (CameraAccessException e2) {
                            C0058d.Log(6, "Camera2: CameraAccessException " + e2);
                            f136e.release();
                            return false;
                        }
                    } catch (InterruptedException e3) {
                        C0058d.Log(6, "Camera2: Interrupted while trying to lock camera for opening " + e3);
                        return false;
                    }
                }
            }
            return false;
        } catch (CameraAccessException e4) {
            C0058d.Log(6, "Camera2: CameraAccessException " + e4);
            return false;
        }
    }

    /* renamed from: b */
    public final void mo189b() {
        if (this.f142d != null) {
            mo192e();
            m81i();
            this.f137A = null;
            this.f162y = null;
            this.f161x = null;
            Image image = this.f156s;
            if (image != null) {
                image.close();
                this.f156s = null;
            }
            ImageReader imageReader = this.f155r;
            if (imageReader != null) {
                imageReader.close();
                this.f155r = null;
            }
        }
        m79h();
    }

    /* renamed from: c */
    public final void mo190c() {
        List list;
        if (this.f155r == null) {
            ImageReader newInstance = ImageReader.newInstance(this.f145h.width(), this.f145h.height(), 35, 2);
            this.f155r = newInstance;
            newInstance.setOnImageAvailableListener(this.f139C, this.f144g);
            this.f156s = null;
            if (this.f160w != 0) {
                SurfaceTexture surfaceTexture = new SurfaceTexture(this.f160w);
                this.f161x = surfaceTexture;
                surfaceTexture.setDefaultBufferSize(this.f145h.width(), this.f145h.height());
                this.f161x.setOnFrameAvailableListener(this.f140D, this.f144g);
                this.f162y = new Surface(this.f161x);
            }
        }
        try {
            if (this.f158u == null) {
                CameraDevice cameraDevice = this.f142d;
                if (this.f162y != null) {
                    list = Arrays.asList(new Surface[]{this.f162y, this.f155r.getSurface()});
                } else {
                    list = Arrays.asList(new Surface[]{this.f155r.getSurface()});
                }
                cameraDevice.createCaptureSession(list, new CameraCaptureSession.StateCallback() {
                    public final void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
                        C0058d.Log(6, "Camera2: CaptureSession configuration failed.");
                    }

                    public final void onConfigured(CameraCaptureSession cameraCaptureSession) {
                        if (C0050b.this.f142d != null) {
                            synchronized (C0050b.this.f159v) {
                                CameraCaptureSession unused = C0050b.this.f158u = cameraCaptureSession;
                                try {
                                    CaptureRequest.Builder unused2 = C0050b.this.f157t = C0050b.this.f142d.createCaptureRequest(1);
                                    if (C0050b.this.f162y != null) {
                                        C0050b.this.f157t.addTarget(C0050b.this.f162y);
                                    }
                                    C0050b.this.f157t.addTarget(C0050b.this.f155r.getSurface());
                                    C0050b.this.f157t.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, C0050b.this.f154q);
                                    C0050b.this.m82j();
                                } catch (CameraAccessException e) {
                                    C0058d.Log(6, "Camera2: CameraAccessException " + e);
                                }
                            }
                        }
                    }
                }, this.f144g);
            } else if (this.f163z == C0056a.f170b) {
                this.f158u.setRepeatingRequest(this.f157t.build(), this.f137A, this.f144g);
            }
            this.f163z = C0056a.f169a;
        } catch (CameraAccessException e) {
            C0058d.Log(6, "Camera2: CameraAccessException " + e);
        }
    }

    /* renamed from: d */
    public final void mo191d() {
        synchronized (this.f159v) {
            if (this.f158u != null) {
                try {
                    this.f158u.stopRepeating();
                    this.f163z = C0056a.f170b;
                } catch (CameraAccessException e) {
                    C0058d.Log(6, "Camera2: CameraAccessException " + e);
                }
            }
        }
    }

    /* renamed from: e */
    public final void mo192e() {
        synchronized (this.f159v) {
            if (this.f158u != null) {
                try {
                    this.f158u.abortCaptures();
                } catch (CameraAccessException e) {
                    C0058d.Log(6, "Camera2: CameraAccessException " + e);
                }
                this.f158u.close();
                this.f158u = null;
                this.f163z = C0056a.f171c;
            }
        }
    }
}
