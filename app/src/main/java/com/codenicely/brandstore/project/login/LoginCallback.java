package com.codenicely.brandstore.project.login;

import com.codenicely.brandstore.project.login.models.data.LoginData;

/**
 * Created by aman on 15/10/16.
 */
public interface LoginCallback {
    void onSuccess(LoginData loginData);

    void onFailure(String error);

}
