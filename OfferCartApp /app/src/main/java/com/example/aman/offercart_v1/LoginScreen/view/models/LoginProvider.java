package com.example.aman.offercart_v1.login.view.models;


import com.example.aman.offercart_v1.login.view.LoginCallback;

/**
 * Created by aman on 15/10/16.
 */
public interface LoginProvider {

    void requestLogin(String name, String mobile, String email, LoginCallback loginUsCallback);

}
