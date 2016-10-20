package com.example.aman.offercart_v1.SplashScreen.view;


import com.example.aman.offercart_v1.SplashScreen.models.data.SplashScreenData;
/**
 * Created by aman on 13/10/16.
 */
public interface SplashScreenView {

    void showMessage(String message);
    void showProgressBar(boolean show);
    void version_check(SplashScreenData splashScreenData);

}
