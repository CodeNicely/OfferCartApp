package com.codenicely.brandstore.project.welcome_screen;

import com.codenicely.brandstore.project.welcome_screen.models.data.WelcomeScreenData;

/**
 * Created by aman on 16/10/16.
 */
public interface WelcomeScreenCallback {

    void onSuccess(WelcomeScreenData welcomeScreenData);

    void onFailure(String error);
}
