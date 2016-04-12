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
import com.kanoonth.ticketprovider.managers.Utility;
import com.kanoonth.ticketprovider.models.Ticket;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        String date = "";
        try {
            date = getDateStr(tickets.get(position).getActivityDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tvTicketName.setText(tickets.get(position).getActivityName());
        holder.tvTicketDesc.setText(date);
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

    private String getDateStr(String rawDate) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        Date date = format.parse(rawDate);
        int day = date.getDate();
        String month = Utility.getMonthStr(date.getMonth());
        int year = date.getYear() + 1900;
        return String.format("%d %s %d", day , month , year);
    }
}
