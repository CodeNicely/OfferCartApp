package com.codenicely.brandstore.project.verify_otp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.helper.SharedPrefs;
import com.codenicely.brandstore.project.home.HomePage;
import com.codenicely.brandstore.project.login.view.LoginScreenActivity;
import com.codenicely.brandstore.project.verify_otp.models.RetrofitOtpProvider;
import com.codenicely.brandstore.project.verify_otp.presenter.OtpPresenter;
import com.codenicely.brandstore.project.verify_otp.presenter.OtpPresenterImpl;
import com.codenicely.brandstore.project.welcome_screen.view.WelcomeScreenActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aman on 16/10/16.
 */
public class OtpViewImpl extends AppCompatActivity implements OtpView {

    EditText otp;
    Button submitButton;
    ProgressBar progressbar;
    String mobile;
    String otp1;
    LoginScreenActivity loginScreenActivity;
	WelcomeScreenActivity welcomeScreenActivity;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private SharedPrefs sharedPrefs;

    private OtpPresenter otpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);
        loginScreenActivity=new LoginScreenActivity();
		welcomeScreenActivity= new WelcomeScreenActivity();
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
                submitButton.setEnabled(false);
                submitButton.setClickable(false);
            }
        });

    }

    @Override
    public void showLoading(boolean show) {
        submitButton.setEnabled(true);
        submitButton.setClickable(true);
        if (show) {
            progressbar.setVisibility(View.VISIBLE);
            submitButton.setClickable(false);
            submitButton.setEnabled(false);
        } else {
            progressbar.setVisibility(View.INVISIBLE);
            submitButton.setEnabled(true);
            submitButton.setClickable(true);
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
        sharedPrefs.setLogin(true);
        Log.d("res", "done");
        Intent a = new Intent(OtpViewImpl.this, HomePage.class);
        startActivity(a);
        finish();
		loginScreenActivity.loginScreenActivity.finish();
        welcomeScreenActivity.welcomeScreenActivity.finish();
    }

}