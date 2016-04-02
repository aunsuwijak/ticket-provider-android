package com.kanoonth.ticketprovider.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.drawer_item_layout,null,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else
            holder = (ViewHolder)convertView.getTag();
        SideBarItem item = getItem(position);
        holder.icon_img.setImageResource(item.getImg());
        holder.text.setText(item.getText());
        return convertView;
    }

    class ViewHolder {
        @Bind(R.id.text) public TextView text;
        @Bind(R.id.icon_img) public ImageView icon_img;

        public ViewHolder(View v) {
            ButterKnife.bind(v);
        }
    }
}
