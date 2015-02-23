package com.radicalninja.fontglyph;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

public class GlyphProperties {

    private static final String TAG = "GlyphProperties";
    private static final int INVALID = -1;

    private Glyph mGlyph;
    private Float mSize;
    private int mColorInt;

    public GlyphProperties(Context context, AttributeSet attrs) {
        readAttrs(context, attrs, null);
    }

    public GlyphProperties(Context context, AttributeSet attrs, Glyph defGlyph) {
        readAttrs(context, attrs, defGlyph);
    }

    private void readAttrs(Context context, AttributeSet attrs, Glyph defGlyph) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FontGlyph);

        int iconRes = a.getInt(R.styleable.FontGlyph_glyph, INVALID);
        if (iconRes != -1) {
            mGlyph = Glyph.valueOf(iconRes);
        } else if (defGlyph != null) {
            mGlyph = defGlyph;
        }

        float size = a.getDimension(R.styleable.FontGlyph_glyphSize, 0);
        if (size > 0) {
            mSize = size;
        }

        mColorInt = a.getColor(R.styleable.FontGlyph_glyphColor, INVALID);

        a.recycle();
    }

    public Glyph getGlyph() {
        return mGlyph;
    }

    public void setGlyph(Glyph glyph) {
        mGlyph = glyph;
    }

    public Float getSize() {
        return mSize;
    }

    public void setSize(Float size) {
        mSize = size;
    }

    public int getColorInt() {
        return mColorInt;
    }

    public void setColorInt(int colorInt) {
        mColorInt = colorInt;
    }
}
