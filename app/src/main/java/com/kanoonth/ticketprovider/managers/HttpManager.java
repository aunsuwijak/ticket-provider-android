package com.kanoonth.ticketprovider.managers;

import android.content.Context;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.kanoonth.ticketprovider.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kanoonth.ticketprovider.models.AccessToken;

import java.io.IOException;

import io.realm.RealmObject;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by TAWEESOFT on 3/3/16 AD.
 */
public class HttpManager {

    private static HttpManager instance = null;
    private static OkHttpClient.Builder httpClient  = new OkHttpClient.Builder();

    public static HttpManager getInstance() {
        if (instance == null)
            instance = new HttpManager();
        return instance;
    }

    public static APIService getAPIService(Class<APIService> serviceClass) {
        return getAPIService(serviceClass, null);
    }

    public static APIService getAPIService(Class<APIService> serviceClass, final AccessToken token) {

        // Add access token interceptor to http client
        if (token != null) {
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Accept", "application/json")
                            .header("Authorization",
                                    token.getTokenType() + " " + token.getAccessToken())
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .create();

        OkHttpClient client = httpClient.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(serviceClass);
    }

    private Context mContext;

    private HttpManager() { mContext = Contextor.getInstance().getContext(); }
}
