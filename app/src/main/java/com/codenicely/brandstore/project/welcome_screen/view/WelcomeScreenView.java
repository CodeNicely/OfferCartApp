package com.codenicely.brandstore.project.welcome_screen.view;

import com.codenicely.brandstore.project.welcome_screen.models.data.WelcomeImageDetails;

import java.util.List;

/**
 * Created by aman on 16/10/16.
 */
public interface WelcomeScreenView {
    void showMessage(String error);

    void showProgressBar(boolean show);

    void setData(List<WelcomeImageDetails> welcomeImageDetails);
}
