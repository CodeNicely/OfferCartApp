package com.example.aman.offercart_v1.LoginScreen.view.presenter;

import com.example.aman.offercart_v1.LoginScreen.view.models.LoginProvider;
import com.example.aman.offercart_v1.LoginScreen.view.models.RetrofitLoginScreenProvider;
import com.example.aman.offercart_v1.LoginScreen.view.view.LoginScreenView;
import com.example.aman.offercart_v1.LoginScreen.view.models.data.LoginData;
import com.example.aman.offercart_v1.LoginScreen.view.LoginCallback;
/**
 * Created by aman on 15/10/16.
 */
public class LoginScreenPresenterImpl implements LoginScreenPresenter {

    private LoginScreenView loginView;
    private LoginProvider loginProvider;

    public LoginScreenPresenterImpl(LoginScreenView loginView, RetrofitLoginScreenProvider retrofitLoginScreenProvider) {
        this. loginView =  loginView;
        this. loginProvider =  retrofitLoginScreenProvider;
    }

    @Override
    public void requestLogin(String name, String mobile, String email) {

        loginView.showLoading(true);
        loginProvider.requestLogin(name, mobile, email, new LoginCallback() {
            @Override
            public void onSuccess(LoginData loginData) {

                loginView.showLoading(false);

                loginView.showMessage("success");
            }
                 @Override
            public void onFailure(String error) {
               loginView.showLoading(true);
                loginView.showMessage("Failed");

            }
        });




    }
}

