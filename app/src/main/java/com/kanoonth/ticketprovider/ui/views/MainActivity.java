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
        ButterKnife.bind(this);
        initComponents();
    }

    public void initComponents() {
        SideBarItem events = new SideBarItem(getString(R.string.events),R.drawable.menu);
        SideBarItem tickets = new SideBarItem(getString(R.string.my_tickets) , R.drawable.shopping);
        SideBarItem payments = new SideBarItem(getString(R.string.payment) , R.drawable.credit_card);
        List<SideBarItem> items = new ArrayList<>();
        items.add(events);
        items.add(tickets);
        items.add(payments);
        SideBarAdapter adapter = new SideBarAdapter(this,R.layout.drawer_item_layout,items);
        sidebar.setAdapter(adapter);
    }
}
