package com.kanoonth.ticketprovider.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kanoonth.ticketprovider.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by TAWEESOFT on 4/11/16 AD.
 */
public class QrCodeFragment extends Fragment {

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
    }
}
