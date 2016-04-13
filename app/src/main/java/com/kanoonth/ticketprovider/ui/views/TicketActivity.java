package com.kanoonth.ticketprovider.ui.views;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kanoonth.ticketprovider.R;
import com.kanoonth.ticketprovider.models.Ticket;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TicketActivity extends AppCompatActivity {
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.tvTicketName) TextView tvTicketName;
    @Bind(R.id.tvZone) TextView tvZone;
    @Bind(R.id.tvSeat) TextView tvSeat;
    @Bind(R.id.tvPrice) TextView tvPrice;
    @Bind(R.id.tvDate) TextView tvDate;
    @Bind(R.id.imgTicket) ImageView imgTicket;

    private Ticket ticket;

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        ButterKnife.bind(this);
        ticket = (Ticket)getIntent().getSerializableExtra("ticket");
        initComponents();
    }

    public void initComponents() {
        toolbar.setTitle(ticket.getActivityName());
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Glide
                .with(this)
                .load(ticket.getTicketTypeImageUrl())
                .centerCrop()
                .crossFade()
                .into(imgTicket);
        tvTicketName.setText(ticket.getActivityName());
        tvZone.setText(ticket.getRow());
        tvSeat.setText(ticket.getColumn());
        tvPrice.setText(ticket.getPrice() + " " + getString(R.string.currency));
        DateFormat format = new SimpleDateFormat("dd MMM yyyy" , Locale.getDefault());
        tvDate.setText(format.format(ticket.getActivityDate()));
    }
}
