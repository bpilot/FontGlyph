package com.radicalninja.fontglyphsample;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.radicalninja.fontglyph.Glyph;

public class IconListFragment extends Fragment implements AdapterView.OnItemClickListener {

    public static interface GlyphListener {
        public void loadIconDetails(Glyph glyph);
    }

    private ListView mListView;
    private GlyphListener mListener;

    public IconListFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_icon_list, container, false);
        mListView = (ListView) rootView.findViewById(R.id.listview);
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
}
