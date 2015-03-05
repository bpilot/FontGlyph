package com.radicalninja.fontglyphsample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.radicalninja.fontglyph.FontGlyph;
import com.radicalninja.fontglyph.Glyph;
import com.radicalninja.fontglyphsample.R;
import com.radicalninja.fontglyphsample.util.StringUtils;

public class TitleSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {

    private static class ViewHolder {
        public TextView title;
        public FontGlyph glyph;
    }

    private Glyph[] mGlyphs;

    public TitleSpinnerAdapter(Glyph[] mGlyphs) {
        this.mGlyphs = mGlyphs;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return mGlyphs.length;
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Glyph getItem(int position) {
        return mGlyphs[position];
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link android.view.LayoutInflater#inflate(int, android.view.ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.glyph_title_item, parent, false);
            ViewHolder holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.action_bar_title);
            holder.glyph = (FontGlyph) convertView.findViewById(R.id.action_bar_glyph);
            convertView.setTag(holder);
        }

        Glyph glyph = getItem(position);
        ViewHolder vh = (ViewHolder) convertView.getTag();
        vh.title.setText(StringUtils.beautifyString(glyph.name()));
        vh.glyph.setIcon(glyph);

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.glyph_title_dropdown_item, parent, false);
            ViewHolder holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.action_bar_title);
            holder.glyph = (FontGlyph) convertView.findViewById(R.id.action_bar_glyph);
            convertView.setTag(holder);
        }

        Glyph glyph = getItem(position);
        ViewHolder vh = (ViewHolder) convertView.getTag();
        vh.title.setText(StringUtils.beautifyString(glyph.name()));
        vh.glyph.setIcon(glyph);

        return convertView;
    }
}
