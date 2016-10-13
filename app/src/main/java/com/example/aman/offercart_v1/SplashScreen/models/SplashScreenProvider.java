package com.example.aman.offercart_v1.SplashScreen.models;

import com.example.aman.offercart_v1.SplashScreen.SplashScreenCallback;
import com.example.aman.offercart_v1.SplashScreen.models.data.SplashScreenData;

/**
 * Created by aman on 13/10/16.
 */
public interface SplashScreenProvider {

    void requestSplash(int version, String message, boolean success, int compulsory_update,
                       SplashScreenCallback splashScreenCallback);
}
