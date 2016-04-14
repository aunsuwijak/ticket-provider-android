package com.kanoonth.ticketprovider.ui.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.kanoonth.ticketprovider.Constants;
import com.kanoonth.ticketprovider.R;
import com.kanoonth.ticketprovider.managers.APIService;
import com.kanoonth.ticketprovider.managers.BarcodeGenerator;
import com.kanoonth.ticketprovider.managers.HttpManager;
import com.kanoonth.ticketprovider.managers.MyObservable;
import com.kanoonth.ticketprovider.managers.Observable;
import com.kanoonth.ticketprovider.models.AccessToken;
import com.kanoonth.ticketprovider.models.Element;
import com.kanoonth.ticketprovider.models.User;

import java.util.Observer;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TAWEESOFT on 4/11/16 AD.
 */
public class QrCodeFragment extends Fragment implements MyObservable {

    private Observable observable = new Observable();

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.qrCodeImage) ImageView qrCodeImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.qr_code_layout, container, false);
        ButterKnife.bind(this,v);
        initComponents();
        return v;
    }

    public void initComponents() {
        toolbar.setTitle("QR Code");
        toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                observable.setChanged();
                observable.notifyObservers();
            }
        });

        final ProgressDialog dialog = ProgressDialog.show(getActivity(), null, getResources().getString(R.string.please_wait), true);
        dialog.setCancelable(true);

        SharedPreferences preferences = getActivity().getSharedPreferences(Constants.APP_NAME, Context.MODE_PRIVATE);
        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(preferences.getString(Constants.ACCESS_TOKEN, null));
        accessToken.setTokenType(preferences.getString(Constants.TOKEN_TYPE, null));

        Display defaultDisplay = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        defaultDisplay.getSize(size);
        int screenWidth = size.x;
        final int qrCodeSize = (int)(screenWidth * 0.8);

        Call<Element> currentUserCall = HttpManager
                .getInstance()
                .getAPIService(APIService.class, accessToken)
                .currentUser();

        currentUserCall.enqueue(new Callback<Element>() {
            @Override
            public void onResponse(Call<Element> call, Response<Element> response) {
                if (response.isSuccessful()) {
                    User user = response.body().getUser();

                    Bitmap bitmap = BarcodeGenerator
                            .getInstance()
                            .generateBitmapBarcode(
                                    user.getId(),
                                    BarcodeFormat.QR_CODE,
                                    qrCodeSize,
                                    qrCodeSize
                            );

                    qrCodeImage.setImageBitmap(bitmap);

                    dialog.dismiss();
                } else {
                    dialog.dismiss();

                    // TODO: Handle this error
                    Log.e("error", response.raw().toString());
                }
            }

            @Override
            public void onFailure(Call<Element> call, Throwable t) {

            }
        });
    }

    public void addObserver(Observer observer) {
        this.observable.addObserver(observer);
    }
}
