package com.codenicely.discountstore.project1.login;

import com.codenicely.discountstore.project1.login.models.data.LoginData;

/**
 * Created by aman on 15/10/16.
 */
public interface LoginCallback {
    void onSuccess(LoginData loginData);

    void onFailure(String error);

}
