package com.codenicely.discountstore.project1.splash_screen;

import com.codenicely.discountstore.project1.splash_screen.models.data.SplashScreenData;

/**
 * Created by aman on 13/10/16.
 */
public interface SplashScreenCallback {

    void onSuccess(SplashScreenData splashScreenData);

    void onFailure(String error);

}
