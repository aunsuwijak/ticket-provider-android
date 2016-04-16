package com.kanoonth.ticketprovider.ui.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.daasuu.ahp.AnimateHorizontalProgressBar;
import com.kanoonth.ticketprovider.Constants;
import com.kanoonth.ticketprovider.R;
import com.kanoonth.ticketprovider.managers.APIService;
import com.kanoonth.ticketprovider.managers.HttpManager;
import com.kanoonth.ticketprovider.models.AccessToken;
import com.kanoonth.ticketprovider.models.Element;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {

    @Bind(R.id.progressBar) AnimateHorizontalProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
        initComponents();

        SharedPreferences preferences = getSharedPreferences(Constants.APP_NAME, Context.MODE_PRIVATE);
        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(preferences.getString(Constants.ACCESS_TOKEN, null));
        accessToken.setTokenType(preferences.getString(Constants.TOKEN_TYPE, null));

        if (accessToken.getAccessToken() == null) {
            navigateToLogin();
        } else {
            Call<Element> element = HttpManager
                    .getInstance().getAPIService(APIService.class,accessToken).currentUser();
            element.enqueue(new Callback<Element>() {
                @Override
                public void onResponse(Call<Element> call, Response<Element> response) {
                    if (response.isSuccessful()) {
                        navigateToMain();
                    } else {
                        // TODO: Handle errors
                        Log.e("errors" , response.raw().toString());

                        navigateToLogin();
                    }
                }

                @Override
                public void onFailure(Call<Element> call, Throwable t) {
                    // TODO: Handle errors
                    Log.e("errors" , t.getMessage());

                    navigateToLogin();
                }
            });
        }
    }

    public void navigateToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void navigateToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void initComponents() {
        progressBar.setMax(1000);
        progressBar.setProgress(0);
        progressBar.setProgressWithAnim(1000);
    }
}
