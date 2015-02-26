package com.radicalninja.fontglyphsample;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class IconListFragment extends Fragment implements IconListRecyclerAdapter.OnListItemClickedListener {

    private static final String TAG = "IconListFragment";

    private GlyphListener mListener;
    private RecyclerView mRecyclerView;
    private IconListRecyclerAdapter mAdapter;

    public IconListFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_icon_list, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        ActionBar ab =  ((ActionBarActivity) getActivity()).getSupportActionBar();
        ab.setTitle(R.string.app_name);
        ab.setDisplayHomeAsUpEnabled(false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        mAdapter = new IconListRecyclerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
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
    public void onItemClick(View view, int position) {
        mListener.loadIconDetails(mAdapter.getItem(position), PaletteTheme.getTheme(position));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
