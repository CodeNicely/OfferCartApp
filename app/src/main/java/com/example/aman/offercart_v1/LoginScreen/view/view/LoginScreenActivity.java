package com.example.aman.offercart_v1.LoginScreen.view.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.aman.offercart_v1.LoginScreen.view.presenter.LoginScreenPresenter;
import com.example.aman.offercart_v1.R;
import com.example.aman.offercart_v1.LoginScreen.view.models.RetrofitLoginScreenProvider;
import android.widget.ProgressBar;
import android.view.View;
import android.widget.Toast;
import com.example.aman.offercart_v1.LoginScreen.view.presenter.LoginScreenPresenterImpl;
import com.example.aman.offercart_v1.LoginScreen.view.models.data.LoginData;


/**
 * Created by aman on 15/10/16.
 */
public class LoginScreenActivity extends Activity implements LoginScreenView {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    EditText name;
    Button login_button;
    EditText mobile;
    EditText email;
    private ProgressBar progressbar;
    private RetrofitLoginScreenProvider retrofitLoginScreenProvider;
    private LoginScreenPresenter loginScreenPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginscreen);

        login_button = (Button) findViewById(R.id.button);
        name = (EditText) findViewById(R.id.editText);
        mobile = (EditText) findViewById(R.id.editText2);
        email = (EditText) findViewById(R.id.editText3);

        ButterKnife.bind(this);

        loginScreenPresenter =new LoginScreenPresenterImpl(this,
                new RetrofitLoginScreenProvider());


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = name.getText().toString();
                String mobile1 = mobile.getText().toString();
                String email1 = email.getText().toString();
                loginScreenPresenter.requestLogin(name1, mobile1, email1);



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
}