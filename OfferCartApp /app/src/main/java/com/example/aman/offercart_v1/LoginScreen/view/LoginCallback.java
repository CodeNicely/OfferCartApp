package com.example.aman.offercart_v1.LoginScreen.view;

import com.example.aman.offercart_v1.LoginScreen.view.models.data.LoginData;


/**
 * Created by aman on 15/10/16.
 */
public interface LoginCallback {
    void onSuccess(LoginData loginData);
    void onFailure(String error);

}
