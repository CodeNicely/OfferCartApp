package com.codenicely.brandstore.project.splash_screen.view;


import android.content.pm.PackageManager;

import com.codenicely.brandstore.project.splash_screen.models.data.SplashScreenData;

/**
 * Created by aman on 13/10/16.
 */
public interface SplashScreenView {

    void showMessage(String message);

    void showProgressBar(boolean show);

    void onVersionReceived(SplashScreenData splashScreenData) throws PackageManager.NameNotFoundException;

    void onFailed();
}
