package com.kanoonth.ticketprovider.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kanoonth.ticketprovider.R;

/**
 * Created by TAWEESOFT on 4/11/16 AD.
 */
public class QrCodeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.qr_code_layout , container , false);
        // TODO: 4/11/16 AD
        // Implement layout
        return v;
    }
}
