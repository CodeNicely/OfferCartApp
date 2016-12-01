package com.codenicely.discountstore.project1.splash_screen.view;


import com.codenicely.discountstore.project1.splash_screen.models.data.SplashScreenData;

/**
 * Created by aman on 13/10/16.
 */
public interface SplashScreenView {

    void showMessage(String message);

    void showProgressBar(boolean show);

    void version_check(SplashScreenData splashScreenData);

}
