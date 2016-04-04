package com.kanoonth.ticketprovider.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by suwijakchaipipat on 4/4/2016 AD.
 */
public class Element {

    @SerializedName("user") User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
