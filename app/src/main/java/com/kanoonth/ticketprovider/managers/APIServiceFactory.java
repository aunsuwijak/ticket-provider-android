package com.kanoonth.ticketprovider.managers;

import com.kanoonth.ticketprovider.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by TAWEESOFT on 3/3/16 AD.
 */
public class APIServiceFactory {
    private static APIService service = null;
    private static GsonBuilder gsonBuilder= new GsonBuilder();

    public static APIService getInstance(){
        Gson gson = gsonBuilder.create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(APIService.class);
        return service;
    }

    public static void setGsonBuilder(GsonBuilder builder){
        gsonBuilder = builder;
    }
}
