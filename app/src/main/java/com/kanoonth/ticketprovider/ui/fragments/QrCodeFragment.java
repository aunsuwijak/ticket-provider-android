package com.kanoonth.ticketprovider.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kanoonth.ticketprovider.R;
import com.kanoonth.ticketprovider.managers.Observable;

import java.util.Observer;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by TAWEESOFT on 4/11/16 AD.
 */
public class QrCodeFragment extends Fragment {

    private Observable observable = new Observable();

    @Bind(R.id.toolbar) Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.qr_code_layout , container , false);
        ButterKnife.bind(this,v);
        initComponents();
        // TODO: 4/11/16 AD
        // Implement layout
        return v;
    }

    public void initComponents() {
        toolbar.setTitle("QR Code");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                observable.setChanged();
                observable.notifyObservers();
            }
        });
    }

    public void addObserver(Observer observer) {
        this.observable.addObserver(observer);
    }
}
