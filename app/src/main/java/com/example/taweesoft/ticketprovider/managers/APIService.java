package com.example.taweesoft.ticketprovider.managers;

import com.example.taweesoft.ticketprovider.models.Token;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by TAWEESOFT on 3/3/16 AD.
 */
public interface APIService {

    @POST("oauth/token")
    Call<Token> getToken(@Query("client_id") String client_id,
                         @Query("client_secret") String client_secret,
                         @Query("grant_type") String grant_type,
                         @Query("email") String email,
                         @Query("password") String password);
}
