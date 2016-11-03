package com.codenicely.discountstore.project.login.models;


import com.codenicely.discountstore.project.login.LoginCallback;

/**
 * Created by aman on 15/10/16.
 */
public interface LoginProvider {

    void requestLogin(String name, String mobile, String email, LoginCallback loginUsCallback);

}
