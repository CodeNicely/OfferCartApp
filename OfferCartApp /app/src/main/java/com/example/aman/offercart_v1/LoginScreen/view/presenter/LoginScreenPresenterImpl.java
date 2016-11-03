package com.codenicely.discountstore.project.login.view.presenter;

import android.util.Log;

import com.codenicely.discountstore.project.login.view.models.LoginProvider;
import com.codenicely.discountstore.project.login.view.view.LoginScreenView;
import com.codenicely.discountstore.project.login.view.models.data.LoginData;
import com.codenicely.discountstore.project.login.view.LoginCallback;
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
    public void requestLogin(String name, String mobile, String email) {

        loginView.showLoading(true);
        Log.d("Resp","1");
        loginProvider.requestLogin(name, mobile, email, new LoginCallback() {
            @Override
            public void onSuccess(LoginData loginData) {
                Log.d("Response","succ");



                loginView.showLoading(false);

                if(loginData.isSuccess()){
                    loginView.showMessage("success");
                    loginView.onLoginVerified();

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

