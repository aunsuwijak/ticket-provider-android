package com.kanoonth.ticketprovider;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

/**
 * Created by suwijakchaipipat on 3/19/2016 AD.
 */
public class TicketProviderApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Fabric.with(this, new Crashlytics());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
