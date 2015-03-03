package com.radicalninja.fontglyphsample;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.radicalninja.fontglyph.FontGlyph;
import com.radicalninja.fontglyph.Glyph;

public class IconDetailsActivity extends ActionBarActivity {

    private static final String TAG = "IconDetailsActivity";

    public static final String ARG_GLYPH = "glyph";
    public static final String ARG_THEME = "theme";

    //private ViewPager mViewPager;
    private FontGlyph mGlyph;
    private TextView mScaleSize;
    private SeekBar mScaleBar;

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
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        View abView = getLayoutInflater().inflate(R.layout.glyph_title_item, null, false);
        TextView abTitle = (TextView) abView.findViewById(R.id.action_bar_title);
        FontGlyph abGlyph = (FontGlyph) abView.findViewById(R.id.action_bar_glyph);
        abTitle.setText(StringUtils.beautifyString(glyph.name()));
        abGlyph.setIcon(glyph);
        ab.setCustomView(abView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
