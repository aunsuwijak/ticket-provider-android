package com.kanoonth.ticketprovider.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by suwijakchaipipat on 3/28/2016 AD.
 */
public class User {

    @SerializedName("id") private String id;
    @SerializedName("email") private String email;
    @SerializedName("name") private String name;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) { this.id = id; }

    public void setEmail(String email) { this.email = email; }

    public void setName(String name) {
        this.name = name;
    }
}
