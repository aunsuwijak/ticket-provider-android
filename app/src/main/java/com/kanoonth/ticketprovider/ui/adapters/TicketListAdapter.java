package com.kanoonth.ticketprovider.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kanoonth.ticketprovider.R;
import com.kanoonth.ticketprovider.models.Ticket;
import com.kanoonth.ticketprovider.ui.views.TicketActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

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
        @Bind(R.id.container) public RelativeLayout container;

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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_layout, null, false);
        final ViewHolder holder = new ViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,TicketActivity.class);
                intent.putExtra("ticket" , tickets.get(holder.getPosition()));
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        DateFormat format = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        final Ticket ticket = tickets.get(position);
        holder.tvTicketName.setText(ticket.getActivityName());
        holder.tvTicketDesc.setText(format.format(ticket.getActivityDate()));
        Glide
                .with(context)
                .load(ticket.getTicketTypeImageUrl())
                .centerCrop()
                .crossFade()
                .into(holder.imgTicket);
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }
}
