package com.example.aman.offercart_v1.login.presenter;

import android.util.Log;


import com.example.aman.offercart_v1.login.LoginCallback;
import com.example.aman.offercart_v1.login.models.LoginProvider;
import com.example.aman.offercart_v1.login.models.data.LoginData;
import com.example.aman.offercart_v1.login.view.LoginScreenView;



/**
 * Created by aman on 15/10/16.
 */
public class LoginScreenPresenterImpl implements LoginScreenPresenter {

    private LoginScreenView loginView;
    private LoginProvider loginProvider;

    public LoginScreenPresenterImpl( LoginScreenView loginView, LoginProvider loginProvider) {
        this. loginView =  loginView;
        this. loginProvider =  loginProvider;
    }

    @Override
    public void requestLogin(String loginToken,String name, String mobile, String email) {

        loginView.showLoading(true);
        Log.d("Resp","1");
        loginProvider.requestLogin(loginToken,name, mobile, email, new LoginCallback() {
            @Override
            public void onSuccess(LoginData loginData) {
                if(loginData.isSuccess())
                {
                    loginView.showLoading(false);
                    loginView.onLoginVerified();
                }
                else
                {
                    loginView.showLoading(false);
                    loginView.showMessage(loginData.getMessage());

                }

            }

            public void onFailure(String error) {
                Log.d("Response","false");
                loginView.showLoading(false);
                loginView.showMessage("Failed");

            }
        });




    }
}

