package com.codenicely.discountstore.project_new.splash_screen.models;

import com.codenicely.discountstore.project_new.splash_screen.SplashScreenCallback;

/**
 * Created by aman on 13/10/16.
 */
public interface SplashScreenProvider {

    void requestSplash(String fcm, SplashScreenCallback splashScreenCallback);
}
