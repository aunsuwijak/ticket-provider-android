package com.kanoonth.ticketprovider.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by suwijakchaipipat on 3/28/2016 AD.
 */
public class User {

    @SerializedName("id") private String id;
    @SerializedName("email") private String email;
    @SerializedName("password") private String password;
    @SerializedName("name") private String name;
    @SerializedName("birthdate") private Date birthdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}
