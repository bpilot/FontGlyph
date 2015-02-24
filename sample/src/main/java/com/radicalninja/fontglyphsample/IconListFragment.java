package com.radicalninja.fontglyphsample;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.radicalninja.fontglyph.Glyph;

public class IconListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static final String TAG = "IconListFragment";

    private ListView mListView;
    private GlyphListener mListener;

    public IconListFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_icon_list, container, false);
        mListView = (ListView) rootView.findViewById(R.id.listview);
        ActionBar ab =  ((ActionBarActivity) getActivity()).getSupportActionBar();
        ab.setTitle(R.string.app_name);
        ab.setDisplayHomeAsUpEnabled(false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mListView.setAdapter(new IconListAdapter(getActivity()));
        mListView.setOnItemClickListener(this);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mListener.loadIconDetails((Glyph) mListView.getAdapter().getItem(position));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
