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

    @Bind(R.id.emailEditText) EditText emailEditText;
    @Bind(R.id.nameEditText) EditText nameEditText;
    @Bind(R.id.passwordEditText) EditText passwordEditText;
    @Bind(R.id.passwordConfirmationEditText) EditText passwordConfirmationEditText;
    @Bind(R.id.signupButton) Button signupButton;
    @Bind(R.id.tvHaveAccount) TextView tvHaveAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tvHaveAccount)
    public void navigateToLogin() {
        finish();
    }
}
