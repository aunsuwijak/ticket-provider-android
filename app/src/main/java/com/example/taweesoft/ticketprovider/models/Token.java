package com.example.taweesoft.ticketprovider.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by TAWEESOFT on 3/5/16 AD.
 */
public class Token {

    @SerializedName("access_token")
    private String token;

    @SerializedName("token_type")
    private String type;

    @SerializedName("expires_in")
    private int expire;

    @SerializedName("created_at")
    private long time;

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }

    public int getExpire() {
        return expire;
    }

    public long getTime() {
        return time;
    }
}
