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
import com.kanoonth.ticketprovider.models.Ticket;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by TAWEESOFT on 4/7/16 AD.
 */
public class TicketListAdapter extends RecyclerView.Adapter<TicketListAdapter.ViewHolder> {

    private List<Ticket> tickets;
    private Context context;

    class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.imgTicket) public ImageView imgTicket;
        @Bind(R.id.tvTicketName) public TextView tvTicketName;
        @Bind(R.id.tvTicketDesc) public TextView tvTicketDesc;

        public ViewHolder (View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }

    public TicketListAdapter(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_layout,null,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTicketName.setText(tickets.get(position).getTicketTypeName());
        holder.tvTicketDesc.setText(tickets.get(position).getColumn());
        Glide
                .with(context)
                .load(tickets.get(position).getTicketTypeImageUrl())
                .centerCrop()
                .crossFade()
                .into(holder.imgTicket);
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }
}
