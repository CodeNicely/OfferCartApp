package com.codenicely.discountstore.project_new.login.models;


import com.codenicely.discountstore.project_new.login.LoginCallback;

/**
 * Created by aman on 15/10/16.
 */
public interface LoginProvider {

    void requestLogin(String name, String mobile, LoginCallback loginUsCallback);

}
