package com.kanoonth.ticketprovider.managers;

import com.kanoonth.ticketprovider.models.AccessToken;
import com.kanoonth.ticketprovider.models.Element;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by TAWEESOFT on 3/3/16 AD.
 */
public interface APIService {

    @POST("oauth/token")
    Call<AccessToken> createAccessToken(@Body RequestBody body);

    @POST("api/v1/users")
    Call<Element> createUser(@Body Element user);

    @GET("api/v1/users/me")
    Call<Element> currentUser();

    @PUT("api/v1/users/{id}")
    Call<Element> updateUser(@Path("id") String id, @Body Element user);

    @GET("api/v1/tickets")
    Call<Element> retrieveTickets();
}
