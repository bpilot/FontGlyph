package com.radicalninja.fontglyphsample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.radicalninja.fontglyph.Glyph;
import com.radicalninja.fontglyphsample.listener.AppNavListener;
import com.radicalninja.fontglyphsample.fragment.IconListFragment;
import com.radicalninja.fontglyphsample.util.PaletteTheme;
import com.radicalninja.fontglyphsample.R;

import java.util.Random;


public class MainActivity extends ActionBarActivity implements AppNavListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(PaletteTheme.THEMES[new Random().nextInt(PaletteTheme.THEMES.length)]);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new IconListFragment())
                    .commit();
        }
    }

    @Override
    public void goBack() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void loadIconDetails(Glyph glyph, int themeResId) {
        Intent intent = new Intent(this, IconDetailsActivity.class);
        intent.putExtra(IconDetailsActivity.ARG_GLYPH, glyph.value);
        intent.putExtra(IconDetailsActivity.ARG_THEME, themeResId);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        goBack();
    }
}
