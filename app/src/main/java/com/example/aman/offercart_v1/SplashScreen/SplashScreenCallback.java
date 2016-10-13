package com.example.aman.offercart_v1.SplashScreen;

import com.example.aman.offercart_v1.SplashScreen.models.data.SplashScreenData;

/**
 * Created by aman on 13/10/16.
 */
public interface SplashScreenCallback {

    void onSuccess(SplashScreenData splashScreenData);
    void onFailure();

}
