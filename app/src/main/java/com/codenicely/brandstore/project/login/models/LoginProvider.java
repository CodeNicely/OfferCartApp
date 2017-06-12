package com.codenicely.brandstore.project.login.models;


import com.codenicely.brandstore.project.login.LoginCallback;

/**
 * Created by aman on 15/10/16.
 */
public interface LoginProvider {

    void requestLogin(String name, String mobile, LoginCallback loginUsCallback);

}
