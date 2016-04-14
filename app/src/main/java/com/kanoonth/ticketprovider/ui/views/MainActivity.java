package com.kanoonth.ticketprovider.ui.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kanoonth.ticketprovider.Constants;
import com.kanoonth.ticketprovider.R;
import com.kanoonth.ticketprovider.managers.APIService;
import com.kanoonth.ticketprovider.managers.HttpManager;
import com.kanoonth.ticketprovider.models.AccessToken;
import com.kanoonth.ticketprovider.models.Element;
import com.kanoonth.ticketprovider.models.SideBarItem;
import com.kanoonth.ticketprovider.models.User;
import com.kanoonth.ticketprovider.ui.adapters.SideBarAdapter;
import com.kanoonth.ticketprovider.ui.fragments.ProfileFragment;
import com.kanoonth.ticketprovider.ui.fragments.QrCodeFragment;
import com.kanoonth.ticketprovider.ui.fragments.TicketListFragment;

import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import net.simonvt.menudrawer.MenuDrawer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Observer {

    private MenuDrawer menuDrawer;
    private int activeItem = 0;
    private QrCodeFragment qrCodeFragment;
    private TicketListFragment ticketListFragment;
    private ProfileFragment profileFragment;
    private List<SideBarItem> items;
    private SideBarAdapter adapter;
    private static final int QR_CODE_FRAGMENT = 0;
    private static final int TICKET_LIST_FRAGMENT = 1;

    @Bind(R.id.lvSidebar) ListView lvSideBar;
    @Bind(R.id.imgProfile)  ImageView imgProfile;
    @Bind(R.id.tvName) TextView tvName;
    @Bind(R.id.tvEmail) TextView tvEmail;

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
        qrCodeFragment = new QrCodeFragment();
        ticketListFragment = new TicketListFragment();
        qrCodeFragment.addObserver(this);
        ticketListFragment.addObserver(this);
        replaceFragment(qrCodeFragment);

        SideBarItem tickets = new SideBarItem(getString(R.string.my_tickets), R.drawable.my_ticket);
        final SideBarItem qrCode = new SideBarItem(getString(R.string.qr_code), R.drawable.qr_code);
        items = new ArrayList<>();
        items.add(qrCode);
        items.add(tickets);
        items.get(0).setActive(true);
        adapter = new SideBarAdapter(this,R.layout.drawer_item_layout,items);
        lvSideBar.setAdapter(adapter);
        lvSideBar.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SideBarItem active = items.get(activeItem);
                active.setActive(false);
                SideBarItem currentItem = items.get(position);
                currentItem.setActive(true);
                adapter.notifyDataSetChanged();
                menuDrawer.toggleMenu(true);
                activeItem = position;
                switch (position){
                    case QR_CODE_FRAGMENT :
                        replaceFragment(qrCodeFragment);
                        break;
                    case TICKET_LIST_FRAGMENT :
                        replaceFragment(ticketListFragment);
                        break;
                }
            }
        });
        AccessToken accessToken = getAccessToken();

        Call<Element> currentUser =
                HttpManager
                        .getInstance()
                        .getAPIService(APIService.class,accessToken)
                        .currentUser();
        currentUser.enqueue(new Callback<Element>() {
            @Override
            public void onResponse(Call<Element> call, Response<Element> response) {
                if (response.isSuccessful()) {
                    User user = response.body().getUser();
                    profileFragment = new ProfileFragment(user);
                    profileFragment.addObserver(MainActivity.this);
                    tvName.setText(user.getName());
                    tvEmail.setText(user.getEmail());
                }else{
                    // TODO: Handle errors
                    Log.e("errors" , response.raw().toString());
                }
            }

            @Override
            public void onFailure(Call<Element> call, Throwable t) {

            }
        });
    }

    public void replaceFragment(Fragment fragmnet) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragmnet)
                .addToBackStack(null)
                .commit();
    }

    public AccessToken getAccessToken() {
        SharedPreferences preferences = getSharedPreferences(Constants.APP_NAME, Context.MODE_PRIVATE);
        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(preferences.getString(Constants.ACCESS_TOKEN, null));
        accessToken.setTokenType(preferences.getString(Constants.TOKEN_TYPE, null));
        return accessToken;
    }

    @Override
    public void update(Observable observable, Object data) {
        menuDrawer.toggleMenu(true);
    }

    @OnClick(R.id.containerProfile)
    public void showProfileFragment() {
        items.get(activeItem).setActive(false);
        adapter.notifyDataSetChanged();
        menuDrawer.toggleMenu(true);
        replaceFragment(profileFragment);
    }
}
