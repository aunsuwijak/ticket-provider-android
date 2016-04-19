package com.kanoonth.ticketprovider.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kanoonth.ticketprovider.R;
import com.kanoonth.ticketprovider.models.SideBarItem;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by TAWEESOFT on 4/2/16 AD.
 */
public class SideBarAdapter extends ArrayAdapter<SideBarItem> {

    public SideBarAdapter(Context context, int resource, List<SideBarItem> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.drawer_item_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        SideBarItem item = getItem(position);
        holder.imgIcon.setImageResource(item.getImg());
        holder.title.setText(item.getText());

        if (item.isActive()) {
            holder.title.setTextColor(Color.WHITE);
            holder.layoutContainer.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        } else {
            holder.title.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            holder.layoutContainer.setBackgroundColor(0);
        }
        return convertView;
    }

    class ViewHolder {
        @Bind(R.id.title) public TextView title;
        @Bind(R.id.imgIcon) public ImageView imgIcon;
        @Bind(R.id.layoutContainer) public LinearLayout layoutContainer;

        public ViewHolder(View v) {
            ButterKnife.bind(this, v);
        }
    }
}
