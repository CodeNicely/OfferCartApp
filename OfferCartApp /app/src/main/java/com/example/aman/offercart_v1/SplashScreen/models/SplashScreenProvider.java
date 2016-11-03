package com.codenicely.discountstore.project.splash_screen.models;

import com.codenicely.discountstore.project.splash_screen.SplashScreenCallback;
import com.codenicely.discountstore.project.splash_screen.models.data.SplashScreenData;

/**
 * Created by aman on 13/10/16.
 */
public interface SplashScreenProvider {

    void requestSplash(SplashScreenCallback splashScreenCallback);
}
