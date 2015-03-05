package com.radicalninja.fontglyphsample.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.radicalninja.fontglyph.FontGlyph;
import com.radicalninja.fontglyph.Glyph;
import com.radicalninja.fontglyphsample.R;
import com.radicalninja.fontglyphsample.SampleApplication;
import com.radicalninja.fontglyphsample.adapter.TitleSpinnerAdapter;

import java.util.Arrays;

public class IconDetailsActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "IconDetailsActivity";

    public static final String ARG_GLYPH = "glyph";
    public static final String ARG_THEME = "theme";

    //private ViewPager mViewPager;
    private FontGlyph mGlyph;
    private TextView mScaleSize;
    private SeekBar mScaleBar;
    private Spinner mSpinner;
    private TitleSpinnerAdapter mSpinnerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        int themeResId = getIntent().getIntExtra(ARG_THEME, -1);
        if (themeResId != -1) {
            setTheme(themeResId);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon_details);
        //mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mGlyph = (FontGlyph) findViewById(R.id.glyph);
        mGlyph.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mScaleSize = (TextView) findViewById(R.id.scale_size);
        mScaleBar = (SeekBar) findViewById(R.id.scale_bar);

        mSpinner = new Spinner(this);
        mSpinner.setOnItemSelectedListener(this);
        mSpinnerAdapter = new TitleSpinnerAdapter(SampleApplication.getGlyphs());
        mSpinner.setAdapter(mSpinnerAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();

        // TODO: Replace old fontglyph code with viewpager
        mScaleBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mScaleSize.setText(String.format("%d sp", progress + 32));
                mGlyph.setTextSize(TypedValue.COMPLEX_UNIT_SP, (float) progress + 32);
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        Glyph glyph = Glyph.valueOf(getIntent().getIntExtra(ARG_GLYPH, 0));

        mGlyph.setIcon(glyph);
        ActionBar ab =  getSupportActionBar();
        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        mSpinner.setSelection(Arrays.asList(SampleApplication.getGlyphs()).indexOf(glyph));
        ab.setCustomView(mSpinner);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mGlyph.setIcon(SampleApplication.getGlyphs()[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { }
}
