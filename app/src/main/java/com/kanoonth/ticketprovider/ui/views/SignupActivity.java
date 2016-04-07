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

public class SignupActivity extends AppCompatActivity {

    /*UI Components*/
    @Bind(R.id.email_txt) EditText email_txt;
    @Bind(R.id.name_txt) EditText name_txt;
    @Bind(R.id.pass_txt) EditText pass_txt;
    @Bind(R.id.confirm_pass_txt) EditText confirm_pass_txt;
    @Bind(R.id.signup_btn) Button signup_btn;
    @Bind(R.id.have_account_txt) TextView have_account_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.signup_btn)
    public void signup() {
        String email = email_txt.getText().toString().trim();
        String name = name_txt.getText().toString().trim();
        String pass = pass_txt.getText().toString().trim();
        String confirm_pass = confirm_pass_txt.getText().toString().trim();
        // TODO: 4/1/16 AD
        // do signup
    }

    @OnClick(R.id.have_account_txt)
    public void goLogin() {
        finish();
    }
}
