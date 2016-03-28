package com.kanoonth.ticketprovider.managers;

import com.kanoonth.ticketprovider.models.AccessToken;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by TAWEESOFT on 3/3/16 AD.
 */
public interface APIService {

    @POST("oauth/token")
    Call<AccessToken> createAccessToken(@Body RequestBody body);
}
