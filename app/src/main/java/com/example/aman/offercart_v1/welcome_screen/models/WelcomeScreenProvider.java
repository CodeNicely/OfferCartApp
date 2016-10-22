package com.example.aman.offercart_v1.welcome_screen.models;

import com.example.aman.offercart_v1.welcome_screen.WelcomeScreenCallback;

/**
 * Created by aman on 16/10/16.
 */
public interface WelcomeScreenProvider {
    void getWelcomeData(WelcomeScreenCallback welcomeScreenCallback);
}
