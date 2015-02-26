package com.radicalninja.fontglyphsample;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.radicalninja.fontglyph.FontGlyph;
import com.radicalninja.fontglyph.Glyph;


public class IconListRecyclerAdapter
        extends RecyclerView.Adapter<IconListRecyclerAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public FontGlyph glyph;
        public TextView label;
        public int theme;

        public ViewHolder(CardView itemView, FontGlyph glyph, TextView label) {
            super(itemView);
            this.glyph = glyph;
            this.label = label;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mClickListener.onItemClick(v, getPosition());
        }

        public CardView getItemView() {
            return (CardView) itemView;
        }
    }

    public static interface OnListItemClickedListener {
        public void onItemClick(View v, int position);
    }

    private Glyph[] values = Glyph.values();

    public OnListItemClickedListener mClickListener;

    public IconListRecyclerAdapter(OnListItemClickedListener onListItemClickedListener) {
        mClickListener = onListItemClickedListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        CardView cardView = new CardView(parent.getContext());
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_icon_list_item, parent, false);
        FontGlyph g = (FontGlyph) v.findViewById(R.id.glyph);
        TextView l = (TextView) v.findViewById(R.id.label);
        cardView.addView(v);
        return new ViewHolder(cardView, g, l);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.glyph.setIcon(values[position]);
        holder.label.setText(StringUtils.beautifyString(values[position].name()));
        PaletteTheme palette =
                PaletteTheme.getPalette(holder.glyph.getContext(), PaletteTheme.getTheme(position));
        holder.getItemView().setCardBackgroundColor(palette.getColorPrimary());
        holder.glyph.setTextColor(palette.getColorAccent());
        holder.label.setTextColor(palette.getColorAccent());
    }

    @Override
    public int getItemCount() {
        return values.length;
    }

    public Glyph getItem(int position) {
        return values[position];
    }
}
