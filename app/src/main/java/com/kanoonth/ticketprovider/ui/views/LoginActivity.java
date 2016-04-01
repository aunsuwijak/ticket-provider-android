package com.kanoonth.ticketprovider.ui.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kanoonth.ticketprovider.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    /*UI componenets*/
    @Bind(R.id.email_txt)
    EditText email_txt;

    @Bind(R.id.pass_txt)
    EditText pass_txt;

    @Bind(R.id.login_btn)
    Button login_btn;

    @Bind(R.id.forgot_pass_txt)
    TextView forgot_pass_txt;

    @Bind(R.id.register_txt)
    TextView register_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
    }

    @OnClick(R.id.login_btn)
    public void login() {
        String email = email_txt.getText().toString();
        String password = pass_txt.getText().toString();
        // TODO: 4/1/16 AD
        // do loing
    }

    @OnClick(R.id.forgot_pass_txt)
    public void forgotPassword() {
        // TODO: 4/1/16 AD
        // Open new activity for reset password
    }

    @OnClick(R.id.register_txt)
    public void register() {
        // TODO: 4/1/16 AD
        // Open register activity.
    }

}
