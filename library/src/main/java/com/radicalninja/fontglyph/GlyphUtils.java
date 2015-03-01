package com.radicalninja.fontglyph;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;

public class GlyphUtils {

    private static final String TAG = "FontIcon";

    public static final Glyph DEFAULT_ICON = Glyph.EXCLAMATION_TRIANGLE;

    private static Typeface mTypeface;

    public static Typeface getTypeface(Context context) {

        if (mTypeface == null) {
            try {
                mTypeface = Typeface.createFromAsset(context.getAssets(), Glyph.FONT_ASSET_FILENAME);
            } catch (Exception e) {
                Log.e(TAG, "Could not get typeface: " + e.getMessage());
                mTypeface = Typeface.DEFAULT;
            }
        }
        return mTypeface;
    }

    public static String getChar(Glyph icon) {
        return Character.toString((char)(icon.value + Glyph._BASE));
    }

    public static Spannable getGlyphSpan(Context context, GlyphProperties properties) {
        SpannableString string = new SpannableString(getChar(properties.getGlyph()));
        GlyphSpan span = new GlyphSpan(getTypeface(context));
        string.setSpan(span, 0, string.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return string;
    }

}
