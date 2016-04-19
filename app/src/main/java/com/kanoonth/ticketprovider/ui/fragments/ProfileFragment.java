package com.kanoonth.ticketprovider.ui.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kanoonth.ticketprovider.Constants;
import com.kanoonth.ticketprovider.R;
import com.kanoonth.ticketprovider.managers.APIService;
import com.kanoonth.ticketprovider.managers.HttpManager;
import com.kanoonth.ticketprovider.managers.MyObservable;
import com.kanoonth.ticketprovider.managers.Observable;
import com.kanoonth.ticketprovider.models.AccessToken;
import com.kanoonth.ticketprovider.models.Element;
import com.kanoonth.ticketprovider.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Observer;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TAWEESOFT on 4/14/16 AD.
 */
public class ProfileFragment extends Fragment implements MyObservable {

    @Bind(R.id.imgProfile) ImageView imgProfile;
    @Bind(R.id.tvName) TextView tvName;
    @Bind(R.id.tvEmail) TextView tvEmail;
    @Bind(R.id.etName) EditText etName;
    @Bind(R.id.etBirthDate) EditText etBirth;
    @Bind(R.id.etOldPassword) EditText etOldPassword;
    @Bind(R.id.etNewPassword) EditText etNewPassword;
    @Bind(R.id.etConfirmPassword) EditText etConfirmPassword;
    @Bind(R.id.toolbar) Toolbar toolbar;

    private DateFormat format = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    private User user;
    private Observable observable = new Observable();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profile_layout, container, false);
        ButterKnife.bind(this, v);
        initComponents();
        return v;
    }

    public void initComponents() {
        toolbar.setTitle(getString(R.string.profile));
        toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                observable.setChanged();
                observable.notifyObservers();
            }
        });
        tvName.setText(user.getName());
        tvEmail.setText(user.getEmail());
    }

    @OnClick(R.id.btnUpdate)
    public void update(){
        String name = etName.getText().toString().trim();
        String birthDate = etBirth.getText().toString().trim();
        String oldPass = etOldPassword.getText().toString().trim();
        String newPass = etNewPassword.getText().toString().trim();
        String confirmPass = etConfirmPassword.getText().toString().trim();

        if (name.length() > 0) {
            user.setName(name);
        }

        if (birthDate.length() > 0) {
            try {
                user.setBirthdate(format.parse(birthDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (oldPass.length() > 0) {
            user.setCurrentPassword(oldPass);
            if (newPass.length() > 0 && confirmPass.length() > 0) {
                if (newPass.equals(confirmPass)) {
                    user.setPassword(newPass);
                    user.setPasswordConfimation(confirmPass);
                } else {
                    Toast.makeText(this.getContext(),getString(R.string.not_match_pass) , Toast.LENGTH_SHORT).show();
                }
            }
        }

        APIService apiService = HttpManager.getInstance().getAPIService(APIService.class,getAccessToken());
        Element element = new Element();
        element.setUser(user);
        Call<Element> updateUserCall = apiService.updateUser(user.getId(),element);
        updateUserCall.enqueue(new Callback<Element>() {
            @Override
            public void onResponse(Call<Element> call, Response<Element> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ProfileFragment.this.getContext(), getString(R.string.success), Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String error = jsonObject.getString("error");
                        AlertDialog dialog = new AlertDialog.Builder(ProfileFragment.this.getContext()).create();
                        dialog.setMessage(error);
                        dialog.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.e("error", response.raw().toString());
                }
                etOldPassword.setText("");
                etNewPassword.setText("");
                etConfirmPassword.setText("");
            }

            @Override
            public void onFailure(Call<Element> call, Throwable t) {
                Log.e("error", t.getMessage().toString());
            }
        });
    }

    @Override
    public void addObserver(Observer observer) {
        this.observable.addObserver(observer);
    }

    public AccessToken getAccessToken() {
        SharedPreferences preferences = getActivity().getSharedPreferences(Constants.APP_NAME, Context.MODE_PRIVATE);
        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(preferences.getString(Constants.ACCESS_TOKEN, null));
        accessToken.setTokenType(preferences.getString(Constants.TOKEN_TYPE, null));
        return accessToken;
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Date date = new Date();
            date.setYear(year-1900);
            date.setMonth(monthOfYear);
            date.setDate(dayOfMonth);
            etBirth.setText(format.format(date));
        }
    };

    @OnClick(R.id.etBirthDate)
    public void showDatePicker() {
        Date date;
        if(user.getBirthdate() == null)
            date = new Date();
        else
            date = user.getBirthdate();
        new DatePickerDialog(this.getContext(), dateSetListener, date.getYear()+1900, date.getMonth(), date.getDate()).show();
    }

    public void setUser(User user) {
        this.user = user;
    }
}
