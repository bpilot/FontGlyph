package com.radicalninja.fontglyphsample;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;

import java.util.HashMap;
import java.util.Map;

public class PaletteTheme {

    public static final int[] THEMES = {
            R.style.Palette_Red, R.style.Palette_Red_Secondary,
            R.style.Palette_Pink, R.style.Palette_Pink_Secondary,
            R.style.Palette_Purple, R.style.Palette_Purple_Secondary,
            R.style.Palette_DeepPurple, R.style.Palette_DeepPurple_Secondary,
            R.style.Palette_Indigo, R.style.Palette_Indigo_Secondary,
            R.style.Palette_Blue, R.style.Palette_Blue_Secondary,
            R.style.Palette_LightBlue, R.style.Palette_LightBlue_Secondary,
            R.style.Palette_Cyan, R.style.Palette_Cyan_Secondary,
            R.style.Palette_Teal, R.style.Palette_Teal_Secondary,
            R.style.Palette_Green, R.style.Palette_Green_Secondary,
            R.style.Palette_LightGreen, R.style.Palette_LightGreen_Secondary,
            R.style.Palette_Lime, R.style.Palette_Lime_Secondary,
            R.style.Palette_Yellow, R.style.Palette_Yellow_Secondary,
            R.style.Palette_Amber, R.style.Palette_Amber_Secondary,
            R.style.Palette_Orange, R.style.Palette_Orange_Secondary,
            R.style.Palette_DeepOrange, R.style.Palette_DeepOrange_Secondary,
            R.style.Palette_Brown, R.style.Palette_Grey, R.style.Palette_BlueGrey
    };

    private static PaletteTheme appDefault;
    private static Map<Integer, PaletteTheme> palettes;

    private int colorPrimary, colorPrimaryDark, colorAccent;

    public static int getTheme(int index) {
        if (index > THEMES.length) {
            return THEMES[(index / THEMES.length) + (index % THEMES.length)];
        } else {
            return THEMES[index];
        }
    }

    public static PaletteTheme getPalette(Context context, int themeResId) {
        if (palettes == null) {
            palettes = new HashMap<>();
        }
        PaletteTheme theme = palettes.get(themeResId);
        if (theme == null) {
            theme = new PaletteTheme(context, themeResId);
        }
        return theme;
    }

    private PaletteTheme(int colorPrimary, int colorPrimaryDark, int colorAccent) {
        this.colorPrimary = colorPrimary;
        this.colorPrimaryDark = colorPrimaryDark;
        this.colorAccent = colorAccent;
    }

    public PaletteTheme(Context context, int themeResId) {
        int[] attrs = {R.attr.colorPrimary, R.attr.colorPrimaryDark, R.attr.colorAccent};
        TypedArray a;

        // Theme colors
        if (appDefault == null) {
            a = context.getTheme().obtainStyledAttributes(attrs);
            int appPrimary = a.getColor(0, Color.BLACK);
            int appPrimaryDark = a.getColor(1, Color.BLACK);
            int appAccent = a.getColor(2, Color.BLACK);
            appDefault = new PaletteTheme(appPrimary, appPrimaryDark, appAccent);
            a.recycle();
        }

        a = context.obtainStyledAttributes(themeResId, attrs);
        colorPrimary = a.getColor(0, appDefault.getColorPrimary());
        colorPrimaryDark = a.getColor(1, appDefault.getColorPrimaryDark());
        colorAccent = a.getColor(2, appDefault.getColorAccent());

        a.recycle();

        palettes.put(themeResId, this);
    }

    public int getColorPrimary() {
        return colorPrimary;
    }

    public void setColorPrimary(int colorPrimary) {
        this.colorPrimary = colorPrimary;
    }

    public int getColorPrimaryDark() {
        return colorPrimaryDark;
    }

    public void setColorPrimaryDark(int colorPrimaryDark) {
        this.colorPrimaryDark = colorPrimaryDark;
    }

    public int getColorAccent() {
        return colorAccent;
    }

    public void setColorAccent(int colorAccent) {
        this.colorAccent = colorAccent;
    }
}
