package com.kanoonth.ticketprovider.ui.views;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kanoonth.ticketprovider.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import net.simonvt.menudrawer.MenuDrawer;

public class MainActivity extends AppCompatActivity {

    private MenuDrawer menuDrawer;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
