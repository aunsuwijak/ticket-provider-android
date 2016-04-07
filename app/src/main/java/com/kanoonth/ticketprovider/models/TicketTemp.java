package com.kanoonth.ticketprovider.models;

/**
 * Created by TAWEESOFT on 4/7/16 AD.
 */
public class TicketTemp {
    private String imageName;
    private String name;
    private String desc;

    public TicketTemp(String imageName, String name, String desc) {
        this.imageName = imageName;
        this.name = name;
        this.desc = desc;
    }

    public String getImageName() {
        return imageName;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
