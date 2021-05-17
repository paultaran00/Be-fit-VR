package com.unity3d.player;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* renamed from: com.unity3d.player.f */
public final class C0061f extends Dialog implements TextWatcher, View.OnClickListener {

    /* renamed from: c */
    private static int f182c = 1627389952;

    /* renamed from: d */
    private static int f183d = -1;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public Context f184a = null;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public UnityPlayer f185b = null;

    /* renamed from: e */
    private int f186e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0061f(Context context, UnityPlayer unityPlayer, String str, int i, boolean z, boolean z2, boolean z3, String str2, int i2, boolean z4) {
        super(context);
        this.f184a = context;
        this.f185b = unityPlayer;
        Window window = getWindow();
        window.requestFeature(1);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 80;
        attributes.x = 0;
        attributes.y = 0;
        window.setAttributes(attributes);
        window.setBackgroundDrawable(new ColorDrawable(0));
        final View createSoftInputView = createSoftInputView();
        setContentView(createSoftInputView);
        window.setLayout(-1, -2);
        window.clearFlags(2);
        window.clearFlags(134217728);
        window.clearFlags(67108864);
        EditText editText = (EditText) findViewById(1057292289);
        m95a(editText, str, i, z, z2, z3, str2, i2);
        ((Button) findViewById(1057292290)).setOnClickListener(this);
        this.f186e = editText.getCurrentTextColor();
        mo212a(z4);
        this.f185b.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public final void onGlobalLayout() {
                if (createSoftInputView.isShown()) {
                    Rect rect = new Rect();
                    C0061f.this.f185b.getWindowVisibleDisplayFrame(rect);
                    int[] iArr = new int[2];
                    C0061f.this.f185b.getLocationOnScreen(iArr);
                    Point point = new Point(rect.left - iArr[0], rect.height() - createSoftInputView.getHeight());
                    Point point2 = new Point();
                    C0061f.this.getWindow().getWindowManager().getDefaultDisplay().getSize(point2);
                    int height = C0061f.this.f185b.getHeight() - point2.y;
                    int height2 = C0061f.this.f185b.getHeight() - point.y;
                    if (height2 != height + createSoftInputView.getHeight()) {
                        C0061f.this.f185b.reportSoftInputIsVisible(true);
                    } else {
                        C0061f.this.f185b.reportSoftInputIsVisible(false);
                    }
                    C0061f.this.f185b.reportSoftInputArea(new Rect(point.x, point.y, createSoftInputView.getWidth(), height2));
                }
            }
        });
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public final void onFocusChange(View view, boolean z) {
                if (z) {
                    C0061f.this.getWindow().setSoftInputMode(5);
                }
            }
        });
        editText.requestFocus();
    }

    /* renamed from: a */
    private static int m93a(int i, boolean z, boolean z2, boolean z3) {
        int i2 = 0;
        int i3 = (z ? 32768 : 524288) | (z2 ? 131072 : 0);
        if (z3) {
            i2 = 128;
        }
        int i4 = i3 | i2;
        if (i < 0 || i > 11) {
            return i4;
        }
        int[] iArr = {1, 16385, 12290, 17, 2, 3, 8289, 33, 1, 16417, 17, 8194};
        return (iArr[i] & 2) != 0 ? iArr[i] : iArr[i] | i4;
    }

    /* renamed from: a */
    private void m95a(EditText editText, String str, int i, boolean z, boolean z2, boolean z3, String str2, int i2) {
        editText.setImeOptions(6);
        editText.setText(str);
        editText.setHint(str2);
        editText.setHintTextColor(f182c);
        editText.setInputType(m93a(i, z, z2, z3));
        editText.setImeOptions(33554432);
        if (i2 > 0) {
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(i2)});
        }
        editText.addTextChangedListener(this);
        editText.setSelection(editText.getText().length());
        editText.setClickable(true);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m97a(String str, boolean z) {
        ((EditText) findViewById(1057292289)).setSelection(0, 0);
        this.f185b.reportSoftInputStr(str, 1, z);
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public String m98b() {
        EditText editText = (EditText) findViewById(1057292289);
        if (editText == null) {
            return null;
        }
        return editText.getText().toString().trim();
    }

    /* renamed from: a */
    public final String mo208a() {
        InputMethodSubtype currentInputMethodSubtype = ((InputMethodManager) this.f184a.getSystemService("input_method")).getCurrentInputMethodSubtype();
        if (currentInputMethodSubtype == null) {
            return null;
        }
        String locale = currentInputMethodSubtype.getLocale();
        if (locale != null && !locale.equals("")) {
            return locale;
        }
        String mode = currentInputMethodSubtype.getMode();
        String extraValue = currentInputMethodSubtype.getExtraValue();
        return mode + " " + extraValue;
    }

    /* renamed from: a */
    public final void mo209a(int i) {
        EditText editText = (EditText) findViewById(1057292289);
        if (editText == null) {
            return;
        }
        if (i > 0) {
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(i)});
            return;
        }
        editText.setFilters(new InputFilter[0]);
    }

    /* renamed from: a */
    public final void mo210a(int i, int i2) {
        int i3;
        EditText editText = (EditText) findViewById(1057292289);
        if (editText != null && editText.getText().length() >= (i3 = i2 + i)) {
            editText.setSelection(i, i3);
        }
    }

    /* renamed from: a */
    public final void mo211a(String str) {
        EditText editText = (EditText) findViewById(1057292289);
        if (editText != null) {
            editText.setText(str);
            editText.setSelection(str.length());
        }
    }

    /* renamed from: a */
    public final void mo212a(boolean z) {
        int i;
        EditText editText = (EditText) findViewById(1057292289);
        Button button = (Button) findViewById(1057292290);
        View findViewById = findViewById(1057292291);
        if (z) {
            i = 0;
            editText.setBackgroundColor(0);
            editText.setTextColor(0);
            editText.setCursorVisible(false);
            button.setClickable(false);
            button.setTextColor(0);
        } else {
            editText.setBackgroundColor(f183d);
            editText.setTextColor(this.f186e);
            editText.setCursorVisible(true);
            button.setClickable(true);
            button.setTextColor(this.f186e);
            i = f183d;
        }
        findViewById.setBackgroundColor(i);
    }

    public final void afterTextChanged(Editable editable) {
        this.f185b.reportSoftInputStr(editable.toString(), 0, false);
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* access modifiers changed from: protected */
    public final View createSoftInputView() {
        RelativeLayout relativeLayout = new RelativeLayout(this.f184a);
        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        relativeLayout.setBackgroundColor(f183d);
        relativeLayout.setId(1057292291);
        C00643 r1 = new EditText(this.f184a) {
            public final boolean onKeyPreIme(int i, KeyEvent keyEvent) {
                if (i == 4) {
                    C0061f fVar = C0061f.this;
                    fVar.m97a(fVar.m98b(), true);
                    return true;
                } else if (i == 84) {
                    return true;
                } else {
                    return super.onKeyPreIme(i, keyEvent);
                }
            }

            /* access modifiers changed from: protected */
            public final void onSelectionChanged(int i, int i2) {
                C0061f.this.f185b.reportSoftInputSelection(i, i2 - i);
            }

            public final void onWindowFocusChanged(boolean z) {
                super.onWindowFocusChanged(z);
                if (z) {
                    ((InputMethodManager) C0061f.this.f184a.getSystemService("input_method")).showSoftInput(this, 0);
                }
            }
        };
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(15);
        layoutParams.addRule(0, 1057292290);
        r1.setLayoutParams(layoutParams);
        r1.setId(1057292289);
        relativeLayout.addView(r1);
        Button button = new Button(this.f184a);
        button.setText(this.f184a.getResources().getIdentifier("ok", "string", "android"));
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(15);
        layoutParams2.addRule(11);
        button.setLayoutParams(layoutParams2);
        button.setId(1057292290);
        button.setBackgroundColor(0);
        relativeLayout.addView(button);
        ((EditText) relativeLayout.findViewById(1057292289)).setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 6) {
                    C0061f fVar = C0061f.this;
                    fVar.m97a(fVar.m98b(), false);
                }
                return false;
            }
        });
        relativeLayout.setPadding(16, 16, 16, 16);
        return relativeLayout;
    }

    public final void onBackPressed() {
        m97a(m98b(), true);
    }

    public final void onClick(View view) {
        m97a(m98b(), false);
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
