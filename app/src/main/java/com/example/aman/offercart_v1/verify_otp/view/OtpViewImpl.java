package com.example.aman.offercart_v1.verify_otp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.aman.offercart_v1.R;
import com.example.aman.offercart_v1.helper.SharedPrefs;
import com.example.aman.offercart_v1.home.HomePage;
import com.example.aman.offercart_v1.verify_otp.models.RetrofitOtpProvider;
import com.example.aman.offercart_v1.verify_otp.presenter.OtpPresenter;
import com.example.aman.offercart_v1.verify_otp.presenter.OtpPresenterImpl;

import butterknife.ButterKnife;

/**
 * Created by aman on 16/10/16.
 */
public class OtpViewImpl extends Activity implements OtpView {

    EditText otp;
    Button submitButton;
    ProgressBar progressbar;
    String mobile;
    String otp1;
    private SharedPrefs sharedPrefs;

    private OtpPresenter otpPresenter;
    private RetrofitOtpProvider retrofitOtpProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        ButterKnife.bind(this);
        sharedPrefs = new SharedPrefs(this);
        otpPresenter = new OtpPresenterImpl(this,
                new RetrofitOtpProvider());


        mobile = getIntent().getExtras().getString("mobile");
        progressbar = (ProgressBar) findViewById(R.id.progressBarView);
        otp = (EditText) findViewById(R.id.editText);
        submitButton = (Button) findViewById(R.id.button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otp1 = otp.getText().toString();
                otpPresenter.requestOtp(otp1, mobile);
            }
        });

    }

    @Override
    public void showLoading(boolean show) {
        if (show) {
            progressbar.setVisibility(View.VISIBLE);
        } else {
            progressbar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onOtpVerified(String token) {
        Log.d("res", token);
        sharedPrefs.setAccessToken(token);
        Log.d("res", "done");
        Intent a = new Intent(OtpViewImpl.this, HomePage.class);
        startActivity(a);
        finish();

    }

}