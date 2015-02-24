package com.radicalninja.fontglyphsample;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.radicalninja.fontglyph.FontGlyph;
import com.radicalninja.fontglyph.Glyph;

public class IconDetailsFragment extends Fragment {

    // TODO: Implement fragment backstack behavior

    private static final String TAG = "IconDetailsFragment";

    public static final String ARG_GLYPH = "glyph";

    private FontGlyph mGlyph;
    private TextView mScaleSize;
    private SeekBar mScaleBar;
    private GlyphListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_icon_details, container, false);
        mGlyph = (FontGlyph) rootView.findViewById(R.id.glyph);
        mScaleSize = (TextView) rootView.findViewById(R.id.scale_size);
        mScaleBar = (SeekBar) rootView.findViewById(R.id.scale_bar);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (GlyphListener) activity;
        } catch (ClassCastException e) {
            throw new RuntimeException("Parent activity must implement GlyphListener.", e);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        mScaleBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mScaleSize.setText(String.format("%d sp", progress + 32));
                mGlyph.setTextSize(TypedValue.COMPLEX_UNIT_SP, (float) progress + 32);
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        Glyph glyph = Glyph.valueOf(getArguments().getInt(ARG_GLYPH, 0));

        mGlyph.setIcon(glyph);
        ActionBar ab =  ((ActionBarActivity) getActivity()).getSupportActionBar();
        ab.setTitle(glyph.name());
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mListener.goBack();
        }
        return super.onOptionsItemSelected(item);
    }

}
