package com.example.taweesoft.ticketprovider;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.taweesoft.ticketprovider.models.Token;
import com.example.taweesoft.ticketprovider.utils.DBConnection;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private String client_id = "9fccdcf70516329fab62bdcfe3708f15b489b75529f825249ccd0221514c0be673add27313a2e15445669090c06c86e2ffd015b2080429cc365428a580b187ea";
    private String client_secret = "9c544954e11defa519dfd9945a13e9535cb2c9af5ed9c7770c18f5ada7fd1ee5e3c268b9633520e71afb9a4fd105cb91f220ac5333a621a190c2fa49e876b9f1";
    private String email = "anthony@bernier.info";
    private String password = "12345678";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBConnection db = new DBConnection();
        Callback<Token> cb = new Callback<Token>() {
            @Override
            public void onResponse(Response<Token> response, Retrofit retrofit) {
                String token = response.body().getToken();
                Toast.makeText(MainActivity.this,"Success\nToken : " +token,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();
            }
        };
        db.getToken(client_id,client_secret,email,password,cb);

    }
}
