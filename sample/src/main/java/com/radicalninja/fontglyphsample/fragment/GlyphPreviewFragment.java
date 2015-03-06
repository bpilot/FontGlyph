package com.radicalninja.fontglyphsample.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.radicalninja.fontglyph.FontGlyph;
import com.radicalninja.fontglyph.Glyph;
import com.radicalninja.fontglyphsample.listener.GlyphPreviewListener;

public class GlyphPreviewFragment extends Fragment implements GlyphPreviewListener {

    private FontGlyph mFontGlyph;
    private Glyph mGlyph;
    private Float mSize;

    public static GlyphPreviewFragment newInstance() {
        return new GlyphPreviewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mFontGlyph = new FontGlyph(getActivity());
        mFontGlyph.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mFontGlyph.setGravity(Gravity.CENTER);
        if (mGlyph != null) {
            mFontGlyph.setIcon(mGlyph);
        }
        if (mSize != null) {
            mFontGlyph.setTextSize(mSize);
        }
        return mFontGlyph;
    }

    @Override
    public void setGlyph(Glyph glyph) {
        mGlyph = glyph;
        if (mFontGlyph != null && glyph != null) {
            mFontGlyph.setIcon(glyph);
        }
    }

    @Override
    public void setSize(int sizeUnit, Float size) {
        mSize = size;
        if (mFontGlyph != null && size != null) {
            mFontGlyph.setTextSize(sizeUnit, size);
        }
    }
}
