package com.example.taweesoft.ticketprovider.utils;

import com.example.taweesoft.ticketprovider.managers.APIService;
import com.example.taweesoft.ticketprovider.managers.APIServiceFactory;
import com.example.taweesoft.ticketprovider.models.Token;


import retrofit.Call;
import retrofit.Callback;

/**
 * Created by TAWEESOFT on 3/5/16 AD.
 */
public class DBConnection {

    public void getToken(String client_id,String client_secret,String email,String password, Callback<Token> cb){
        APIService service = APIServiceFactory.getInstance();
        Call<Token> data = service.getToken(client_id,client_secret,Storage.grant_type,email,password);
        data.enqueue(cb);
    }
}
