package com.kanoonth.ticketprovider.ui.views;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.kanoonth.ticketprovider.R;
import com.kanoonth.ticketprovider.models.SideBarItem;
import com.kanoonth.ticketprovider.ui.adapters.SideBarAdapter;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import net.simonvt.menudrawer.MenuDrawer;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private MenuDrawer menuDrawer;
    @Bind(R.id.sidebar) ListView sidebar;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        menuDrawer = MenuDrawer.attach(this);
        menuDrawer.setContentView(R.layout.activity_main);
        menuDrawer.setMenuView(R.layout.left_drawer_layout);
        ButterKnife.bind(menuDrawer);
    }

    public void initComponents() {
    }
}
