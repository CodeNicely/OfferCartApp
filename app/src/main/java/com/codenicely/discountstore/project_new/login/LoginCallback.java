package com.codenicely.discountstore.project_new.login;

import com.codenicely.discountstore.project_new.login.models.data.LoginData;

/**
 * Created by aman on 15/10/16.
 */
public interface LoginCallback {
    void onSuccess(LoginData loginData);

    void onFailure(String error);

}
