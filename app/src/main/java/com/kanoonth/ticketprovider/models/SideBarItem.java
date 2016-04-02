package com.kanoonth.ticketprovider.models;

/**
 * Created by TAWEESOFT on 4/2/16 AD.
 */
public class SideBarItem {

    private int img;
    private String text;

    public SideBarItem(String text, int img) {
        this.text = text;
        this.img = img;
    }

    public int getImg() {
        return img;
    }

    public String getText() {
        return text;
    }
}
