package com.example.aman.offercart_v1.login.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import butterknife.ButterKnife;

import com.example.aman.offercart_v1.login.models.RetrofitLoginScreenProvider;
import com.example.aman.offercart_v1.login.presenter.LoginScreenPresenter;
import com.example.aman.offercart_v1.login.presenter.LoginScreenPresenterImpl;
import com.example.aman.offercart_v1.verify_otp.view.OtpViewImpl;
import com.example.aman.offercart_v1.R;
import android.widget.ProgressBar;
import android.view.View;
import android.widget.Toast;


/**
 * Created by aman on 15/10/16.
 */
public class LoginScreenActivity extends Activity implements LoginScreenView {


    EditText name;
    Button login_button;
    EditText mobile;
    String mobile1;
    EditText email;
    private ProgressBar progressbar;
    private RetrofitLoginScreenProvider retrofitLoginScreenProvider;
    private LoginScreenPresenter loginScreenPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginscreen);
        Log.d("Response","1");

        progressbar=(ProgressBar)findViewById(R.id.progressBar);
        Log.d("Response","2");

        login_button = (Button) findViewById(R.id.button);
        name = (EditText) findViewById(R.id.editText);
        mobile = (EditText) findViewById(R.id.editText2);
        Log.d("Response","3");
        email = (EditText) findViewById(R.id.editText3);

        ButterKnife.bind(this);
        Log.d("Response","4");

        loginScreenPresenter =new LoginScreenPresenterImpl(this,
                new RetrofitLoginScreenProvider());

        Log.d("Response","5");
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = name.getText().toString();
                mobile1 = mobile.getText().toString();
                String email1 = email.getText().toString();
                Log.d("Response","b1");
                loginScreenPresenter.requestLogin(name1, mobile1, email1);
                Log.d("Response","b2");
            }
        });
        Log.d("Response","6");


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
    public void onLoginVerified() {
        Intent in=new Intent(LoginScreenActivity.this, OtpViewImpl.class);
        in.putExtra("mobile",mobile1);
        startActivity(in);

    }
}