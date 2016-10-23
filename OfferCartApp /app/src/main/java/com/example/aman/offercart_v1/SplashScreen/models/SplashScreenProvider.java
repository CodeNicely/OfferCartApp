package com.example.aman.offercart_v1.splash_screen.models;

import com.example.aman.offercart_v1.splash_screen.SplashScreenCallback;
import com.example.aman.offercart_v1.splash_screen.models.data.SplashScreenData;

/**
 * Created by aman on 13/10/16.
 */
public interface SplashScreenProvider {

    void requestSplash(SplashScreenCallback splashScreenCallback);
}
