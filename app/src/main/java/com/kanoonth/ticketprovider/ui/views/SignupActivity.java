package com.kanoonth.ticketprovider.ui.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
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
import com.kanoonth.ticketprovider.models.Element;
import com.kanoonth.ticketprovider.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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

public class SignupActivity extends AppCompatActivity{

    @Bind(R.id.emailEditText) EditText emailEditText;
    @Bind(R.id.nameEditText) EditText nameEditText;
    @Bind(R.id.passwordEditText) EditText passwordEditText;
    @Bind(R.id.passwordConfirmationEditText) EditText passwordConfirmationEditText;
    @Bind(R.id.signupButton) Button signupButton;
    @Bind(R.id.tvHaveAccount) TextView tvHaveAccount;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.signupButton)
    public void signup() {
        final ProgressDialog dialog = ProgressDialog.show(SignupActivity.this, null, getResources().getString(R.string.please_wait), true);
        dialog.setCancelable(true);

        String email = emailEditText.getText().toString().trim();
        String name = nameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String passwordConfirmation = passwordConfirmationEditText.getText().toString().trim();

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setPasswordConfimation(passwordConfirmation);

        Element element = new Element();
        element.setUser(user);

        Call<Element> createUserCall = HttpManager
                .getInstance().getAPIService(APIService.class).createUser(element);

        createUserCall.enqueue(new Callback<Element>() {
            @Override
            public void onResponse(Call<Element> call, Response<Element> response) {
                if (response.isSuccessful()) {

                    User user = response.body().getUser();

                    Map<String, String> map = new HashMap<>();
                    map.put("client_id", Constants.CLIENT_ID);
                    map.put("client_secret", Constants.CLIENT_SECRET);
                    map.put("grant_type", Constants.GRANT_TYPE);
                    map.put("email", user.getEmail());
                    map.put("password", user.getPassword());

                    Call<AccessToken> accessTokenCall = HttpManager
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
                                try {
                                    JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                    String error = jsonObject.getString("error");
                                    AlertDialog dialog = new AlertDialog.Builder(SignupActivity.this).create();
                                    dialog.setMessage(error);
                                    dialog.show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Log.d("error", response.raw().toString());
                            }
                        }

                        @Override
                        public void onFailure(Call<AccessToken> call, Throwable t) {

                        }
                    });
                } else {
                    Log.d("error", response.raw().toString());
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String error = jsonObject.getString("error");
                        AlertDialog dialog = new AlertDialog.Builder(SignupActivity.this).create();
                        dialog.setMessage(error);
                        dialog.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Element> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.tvHaveAccount)
    public void navigateToLogin() {
        finish();
    }

}
