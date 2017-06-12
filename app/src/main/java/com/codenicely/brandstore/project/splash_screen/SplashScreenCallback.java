package com.codenicely.brandstore.project.splash_screen;

import android.content.pm.PackageManager;

import com.codenicely.brandstore.project.splash_screen.models.data.SplashScreenData;

/**
 * Created by aman on 13/10/16.
 */
public interface SplashScreenCallback {

    void onSuccess(SplashScreenData splashScreenData) throws PackageManager.NameNotFoundException;

    void onFailure(String error);

}
