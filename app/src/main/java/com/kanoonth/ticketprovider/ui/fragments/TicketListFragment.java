package com.kanoonth.ticketprovider.ui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
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

import com.kanoonth.ticketprovider.Constants;
import com.kanoonth.ticketprovider.R;
import com.kanoonth.ticketprovider.managers.APIService;
import com.kanoonth.ticketprovider.managers.HttpManager;
import com.kanoonth.ticketprovider.managers.MyObservable;
import com.kanoonth.ticketprovider.managers.Observable;
import com.kanoonth.ticketprovider.models.AccessToken;
import com.kanoonth.ticketprovider.models.Element;
import com.kanoonth.ticketprovider.models.Ticket;
import com.kanoonth.ticketprovider.ui.adapters.TicketListAdapter;

import java.util.List;
import java.util.Observer;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TAWEESOFT on 4/7/16 AD.
 */
public class TicketListFragment extends Fragment implements MyObservable {

    private Observable observable = new Observable();

    @Bind(R.id.rv) RecyclerView rv;
    @Bind(R.id.toolbar) Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ticket_list_layout,container,false);
        ButterKnife.bind(this, view);
        initComponents();
        return view;
    }

    public void initComponents() {
        toolbar.setTitle(getString(R.string.my_tickets));
        toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                observable.setChanged();
                observable.notifyObservers();
            }
        });

        SharedPreferences preferences = getActivity().getSharedPreferences(Constants.APP_NAME, Context.MODE_PRIVATE);
        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(preferences.getString(Constants.ACCESS_TOKEN, null));
        accessToken.setTokenType(preferences.getString(Constants.TOKEN_TYPE, null));

        Call<Element> retriveTicketCall = HttpManager
                .getInstance().getAPIService(APIService.class, accessToken).retrieveTickets();
        retriveTicketCall.enqueue(new Callback<Element>() {
            @Override
            public void onResponse(Call<Element> call, Response<Element> response) {
                if(response.isSuccessful()){
                    List<Ticket> tickets = response.body().getTickets();
                    TicketListAdapter adapter = new TicketListAdapter(tickets);
                    rv.setAdapter(adapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(TicketListFragment.this.getContext());
                    rv.setLayoutManager(layoutManager);
                    rv.setAdapter(adapter);
                }else{
                    // TODO: handle errors
                    Log.e("error" , response.raw().toString());
                }

            }

            @Override
            public void onFailure(Call<Element> call, Throwable t) {

            }
        });
    }

    public void addObserver(Observer observer) {
        this.observable.addObserver(observer);
    }
}
