package com.radicalninja.fontglyphsample;

import android.app.Application;

import com.radicalninja.fontglyph.Glyph;
import com.radicalninja.fontglyph.GlyphUtils;

public class SampleApplication extends Application {

    private static Glyph[] glyphs = GlyphUtils.getAlphatizedGlyphs();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static Glyph[] getGlyphs() {
        return glyphs;
    }
}
