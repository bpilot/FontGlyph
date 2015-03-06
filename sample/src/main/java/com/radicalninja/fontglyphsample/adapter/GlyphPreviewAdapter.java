package com.radicalninja.fontglyphsample.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.util.TypedValue;

import com.radicalninja.fontglyph.Glyph;
import com.radicalninja.fontglyphsample.fragment.GlyphPreviewFragment;
import com.radicalninja.fontglyphsample.listener.GlyphPreviewListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class GlyphPreviewAdapter extends FragmentStatePagerAdapter {

    private int mSizeUnit = TypedValue.COMPLEX_UNIT_SP;
    private Float mSize;
    private Glyph mGlyph;
    private List<WeakReference<GlyphPreviewListener>> mFragments;

    public GlyphPreviewAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new ArrayList<>(getCount());
    }

    @Override
    public Fragment getItem(int i) {
        GlyphPreviewFragment f = GlyphPreviewFragment.newInstance();
        f.setGlyph(mGlyph);
        f.setSize(mSizeUnit, mSize);
        mFragments.add(new WeakReference<GlyphPreviewListener>(f));
        return f;
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return 3;
    }

    public void setGlyph(Glyph glyph) {
        mGlyph = glyph;
        for (WeakReference<GlyphPreviewListener> rf : mFragments) {
            GlyphPreviewListener f = rf.get();
            if (f != null) {
                f.setGlyph(glyph);
            } else {
                mFragments.remove(rf);
            }
        }
    }

    public void setSize(float size) {
        mSize = size;
        for (WeakReference<GlyphPreviewListener> rf : mFragments) {
            GlyphPreviewListener f = rf.get();
            if (f != null) {
                f.setSize(mSizeUnit, size);
            } else {
                mFragments.remove(rf);
            }
        }
    }

}
