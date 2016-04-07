package com.kanoonth.ticketprovider.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kanoonth.ticketprovider.R;
import com.kanoonth.ticketprovider.models.TicketTemp;
import com.kanoonth.ticketprovider.ui.adapters.TicketListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by TAWEESOFT on 4/7/16 AD.
 */
public class TicketListFragment extends Fragment {

    @Bind(R.id.rv) RecyclerView rv;
    @Bind(R.id.toolbar) Toolbar toolbar;

    private String url = "http://taweesoft.xyz/ticket-provider/ticket-images/";
    private String[] images = new String[]{
            "s2o.png",
            "breezer.png",
            "motorshow.png",
            "loy_kra_thong.png",
            "stand_up_comedy.png",
            "khon.png"
    };

    private String[] names = new String[] {
            "S2O Songkarn Festival",
            "Breezer Water War",
            "Bangkok Motorshow",
            "Loy Kra Thong 2016",
            "Stand Up Comedy 11 #18",
            "Khon"
    };

    private String[] desc = new String[] {
            "13-15 April 2016",
            "13-15 April 2016",
            "20-25 September 2016",
            "14 November 2016",
            "23 May 2016",
            "1 August 2016"
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ticket_list_layout,container,false);
        ButterKnife.bind(this, view);
        initComponents();
        return view;
    }

    public void initComponents() {
        List<TicketTemp> tickets = new ArrayList<>();
        for(int i =0;i<images.length;i++)
            tickets.add(new TicketTemp(images[i],names[i],desc[i]));
        TicketListAdapter adapter = new TicketListAdapter(tickets,url);
        rv.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);

        toolbar.setTitle(getString(R.string.my_tickets));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Clicked" , "Toolbar back");
            }
        });
    }
}
