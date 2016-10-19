package com.example.aman.offercart_v1.WelcomeScreen;

import com.example.aman.offercart_v1.WelcomeScreen.models.data.WelcomeScreenData;

/**
 * Created by aman on 16/10/16.
 */
public interface WelcomeScreenCallback {

    void onSuccess(WelcomeScreenData welcomeScreenData);
    void onFailure(String error);
}
