package com.radicalninja.fontglyphsample;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.radicalninja.fontglyph.FontGlyph;
import com.radicalninja.fontglyph.Glyph;

public class IconListAdapter extends BaseAdapter {

    private static class ViewHolder {
        public FontGlyph glyph;
        public TextView label;
    }

    private Glyph[] values = Glyph.values();
    private Context context;

    public IconListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public Glyph getItem(int position) {
        return values[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.adapter_icon_list_item, parent, false);
            ViewHolder h = new ViewHolder();
            h.glyph = (FontGlyph) convertView.findViewById(R.id.glyph);
            h.label = (TextView) convertView.findViewById(R.id.label);
            convertView.setTag(h);
        }
        ViewHolder vh = (ViewHolder) convertView.getTag();
        Glyph glyph = getItem(position);
        vh.glyph.setIcon(glyph);
        vh.label.setText(glyph.name());
        return convertView;
    }
}
