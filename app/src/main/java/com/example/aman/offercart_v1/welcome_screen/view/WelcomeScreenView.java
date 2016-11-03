package com.example.aman.offercart_v1.welcome_screen.view;

import com.example.aman.offercart_v1.welcome_screen.models.data.WelcomeImageDetails;

import java.util.List;

/**
 * Created by aman on 16/10/16.
 */
public interface WelcomeScreenView {
    void showMessage(String error);

    void showProgressBar(boolean show);

    void setData(List<WelcomeImageDetails> welcomeImageDetails);
}
