package com.codenicely.brandstore.project.login.presenter;

import android.util.Log;

import com.codenicely.brandstore.project.login.LoginCallback;
import com.codenicely.brandstore.project.login.models.LoginProvider;
import com.codenicely.brandstore.project.login.models.data.LoginData;
import com.codenicely.brandstore.project.login.view.LoginScreenView;


/**
 * Created by aman on 15/10/16.
 */
public class LoginScreenPresenterImpl implements LoginScreenPresenter {

    private LoginScreenView loginView;
    private LoginProvider loginProvider;

    public LoginScreenPresenterImpl(LoginScreenView loginView, LoginProvider loginProvider) {
        this.loginView = loginView;
        this.loginProvider = loginProvider;
    }

    @Override
    public void requestLogin(String name, String mobile) {

        loginView.showLoading(true);
        Log.d("Resp", "1");
        loginProvider.requestLogin(name, mobile,  new LoginCallback() {
            @Override
            public void onSuccess(LoginData loginData) {
                if (loginData.isSuccess()) {
                    loginView.showLoading(false);
                    loginView.onLoginVerified();
                } else {
                    loginView.showLoading(false);
                    loginView.showMessage(loginData.getMessage());
                    loginView.onLoginFailed();
                }

            }

            public void onFailure(String error) {
                loginView.showLoading(false);
                loginView.showMessage(error);

            }
        });


    }
}

