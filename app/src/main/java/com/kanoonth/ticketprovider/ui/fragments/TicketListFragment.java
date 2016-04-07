package com.kanoonth.ticketprovider.ui.fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by TAWEESOFT on 4/7/16 AD.
 */
public class TicketListFragment extends Fragment {

    private String url = "http://taweesoft.xyz/ticket-provider/ticket-images/";
    private String[] images = new String[]{
            "s2o.png",
            "breezer.png",
            "motorshow.png",
            "loy_kra_thong.png",
            "stand_up_comedy.png",
            "khon.png"
    };

    @Nullable
    @Override
    public View getView() {
        return super.getView();
    }
}
