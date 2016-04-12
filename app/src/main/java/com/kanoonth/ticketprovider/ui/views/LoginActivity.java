package com.kanoonth.ticketprovider.ui.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kanoonth.ticketprovider.Constants;
import com.kanoonth.ticketprovider.R;
import com.kanoonth.ticketprovider.managers.APIService;
import com.kanoonth.ticketprovider.managers.HttpManager;
import com.kanoonth.ticketprovider.models.AccessToken;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.emailEditText) EditText emailEditText;
    @Bind(R.id.passwordEditText) EditText passwordEditText;
    @Bind(R.id.loginButton) Button loginButton;
    @Bind(R.id.tvForgotPassword) TextView tvForgotPassword;
    @Bind(R.id.tvRegister) TextView tvRegiser;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tvRegister)
    public void navigateToSignup() {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.loginButton)
    public void login() {
        final ProgressDialog dialog = ProgressDialog.show(LoginActivity.this, null, "Please wait! ...", true);
        dialog.setCancelable(true);

        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        Map<String, String> map = new HashMap<>();
        map.put("client_id", Constants.CLIENT_ID);
        map.put("client_secret", Constants.CLIENT_SECRET);
        map.put("grant_type", Constants.GRANT_TYPE);
        map.put("email", email);
        map.put("password", password);

        Call<AccessToken> accessTokenCall =
                HttpManager
                    .getInstance()
                    .getAPIService(APIService.class)
                    .createAccessToken(
                            RequestBody.create(MediaType.parse("application/json"), (new JSONObject(map)).toString())
                    );

        accessTokenCall.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                if (response.isSuccessful()) {
                    AccessToken accessToken = response.body();

                    SharedPreferences.Editor editor = getSharedPreferences(Constants.APP_NAME, MODE_PRIVATE).edit();
                    editor.putString(Constants.ACCESS_TOKEN, accessToken.getAccessToken());
                    editor.putString(Constants.TOKEN_TYPE, accessToken.getTokenType());
                    editor.apply();

                    dialog.dismiss();
                } else {
                    dialog.dismiss();

                    // TODO: Handle error message
                    Log.d("error", response.raw().toString());
                }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {

            }
        });
    }
}
