package com.kanoonth.ticketprovider.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by suwijakchaipipat on 4/4/2016 AD.
 * TODO: Replace this class with custom converter.
 */
public class Element {

    @SerializedName("user") User user;
    @SerializedName("tickets") List<Ticket> tickets;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
