package com.radicalninja.fontglyphsample.listener;

import com.radicalninja.fontglyph.Glyph;

public interface AppNavListener {

    public void loadIconDetails(Glyph glyph, int themeResId);

    public void goBack();

}
