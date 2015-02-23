package com.radicalninja.fontglyph;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;

public class GlyphButton extends Button {

    private static final String TAG = "GlyphButton";

    private GlyphProperties mProperties;
    private String mText;

    public GlyphButton(Context context) {
        super(context);
        init(null);
    }

    public GlyphButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public GlyphButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        mProperties = new GlyphProperties(getContext(), attrs);
        if (mProperties.getGlyph() != null) {
            setIcon(mProperties.getGlyph());
        }
    }

    public void setIcon(Glyph icon) {
        setText(TextUtils.concat(GlyphUtils.getGlyphSpan(getContext(), mProperties), " ", getText()));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        Log.d(TAG, "Text: " + text.toString());
    }
}
