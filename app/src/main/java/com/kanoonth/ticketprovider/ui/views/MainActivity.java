package com.kanoonth.ticketprovider.ui.views;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.kanoonth.ticketprovider.R;
import com.kanoonth.ticketprovider.models.SideBarItem;
import com.kanoonth.ticketprovider.ui.adapters.SideBarAdapter;
import com.kanoonth.ticketprovider.ui.fragments.TicketListFragment;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import net.simonvt.menudrawer.MenuDrawer;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private MenuDrawer menuDrawer;
    private int activeItem = 0;

    @Bind(R.id.sidebar) ListView sidebar;
    @Bind(R.id.img_profile)  ImageView img_profile;
    @Bind(R.id.tv_name) TextView tv_name;
    @Bind(R.id.tv_email) TextView tv_email;

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
        final List<SideBarItem> items = new ArrayList<>();
        items.add(events);
        items.add(tickets);
        items.add(payments);
        items.get(0).setActive(true);
        final SideBarAdapter adapter = new SideBarAdapter(this,R.layout.drawer_item_layout,items);
        sidebar.setAdapter(adapter);

        tv_name.setText("Ryan");
        tv_email.setText("ryan@hollywood.com");

        sidebar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SideBarItem active = items.get(activeItem);
                active.setActive(!active.isActive());
                SideBarItem currentItem = items.get(position);
                currentItem.setActive(!currentItem.isActive());
                adapter.notifyDataSetChanged();
                menuDrawer.closeMenu(true);
                activeItem = position;
                switch (position){
                    case 1 :
                        replaceFragment(new TicketListFragment());
                }
            }
        });
    }

    public void replaceFragment(Fragment fragmnet) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragmnet)
                .addToBackStack(null)
                .commit();
    }
}
