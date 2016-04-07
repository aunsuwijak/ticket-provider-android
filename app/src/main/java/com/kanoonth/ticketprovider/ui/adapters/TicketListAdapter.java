package com.kanoonth.ticketprovider.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kanoonth.ticketprovider.R;
import com.kanoonth.ticketprovider.models.TicketTemp;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by TAWEESOFT on 4/7/16 AD.
 */
public class TicketListAdapter extends RecyclerView.Adapter<TicketListAdapter.ViewHolder> {

    private List<TicketTemp> tickets;
    private String url;
    private Context context;

    class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.img_ticket) public ImageView img_ticket;
        @Bind(R.id.tv_ticket_name) public TextView tv_ticket_name;
        @Bind(R.id.tv_ticket_desc) public TextView tv_ticket_desc;

        public ViewHolder (View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }

    public TicketListAdapter(List<TicketTemp> tickets, String url) {
        this.tickets = tickets;
        this.url = url;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_layout,null,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_ticket_name.setText(tickets.get(position).getName());
        holder.tv_ticket_desc.setText(tickets.get(position).getDesc());
        Glide
                .with(context)
                .load(url+tickets.get(position).getImageName())
                .centerCrop()
                .crossFade()
                .into(holder.img_ticket);
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }
}
