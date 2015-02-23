package com.radicalninja.fontglyph;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class FontGlyph extends TextView {

    private static final String TAG = "FontIcon";

    private GlyphProperties mProperties;

    public FontGlyph(Context context) {

        super(context);
        init(null);
    }

    public FontGlyph(Context context, AttributeSet attrs) {

        super(context, attrs);
        init(attrs);
    }

    public FontGlyph(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        this.setTypeface(GlyphUtils.getTypeface(getContext()));

        mProperties = new GlyphProperties(getContext(), attrs, GlyphUtils.DEFAULT_ICON);

        setIcon(mProperties.getGlyph());
    }

    public Glyph getIcon() {
        return mProperties.getGlyph();
    }

    public void setIcon(Glyph icon) {
        this.setText(GlyphUtils.getChar(icon));
    }

}
