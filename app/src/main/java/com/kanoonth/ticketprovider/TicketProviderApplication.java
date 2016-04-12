package com.kanoonth.ticketprovider;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.kanoonth.ticketprovider.managers.Contextor;

import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by suwijakchaipipat on 3/19/2016 AD.
 */
public class TicketProviderApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Contextor
        Contextor.getInstance().init(getApplicationContext());

        // Initialize Crashlytics
        Fabric.with(this, new Crashlytics());

        // Initialize Calligraphy
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/ThaiSansNeue-Regular.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
