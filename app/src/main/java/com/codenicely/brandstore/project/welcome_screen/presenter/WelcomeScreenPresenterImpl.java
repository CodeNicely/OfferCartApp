package com.codenicely.brandstore.project.welcome_screen.presenter;

import com.codenicely.brandstore.project.welcome_screen.WelcomeScreenCallback;
import com.codenicely.brandstore.project.welcome_screen.models.WelcomeScreenProvider;
import com.codenicely.brandstore.project.welcome_screen.models.data.WelcomeScreenData;
import com.codenicely.brandstore.project.welcome_screen.view.WelcomeScreenView;

/**
 * Created by aman on 16/10/16.
 */
public class WelcomeScreenPresenterImpl implements WelcomeScreenPresenter {

    private WelcomeScreenView welcomeScreenView;
    private WelcomeScreenProvider welcomeScreenProvider;

    public WelcomeScreenPresenterImpl(WelcomeScreenView welcomeScreenView, WelcomeScreenProvider welcomeScreenProvider) {
        this.welcomeScreenView = welcomeScreenView;
        this.welcomeScreenProvider = welcomeScreenProvider;
    }

    @Override
    public void getWelcomeData() {
        welcomeScreenProvider.getWelcomeData(new WelcomeScreenCallback() {
            @Override
            public void onSuccess(WelcomeScreenData welcomeScreenData) {
                if (welcomeScreenData.isSuccess()) {
                    welcomeScreenView.setData(welcomeScreenData.getSlider_data());
                    welcomeScreenView.showProgressBar(false);

                } else {
                    welcomeScreenView.showMessage("Something went wrong");
                    welcomeScreenView.showProgressBar(true);
                }
            }

            @Override
            public void onFailure(String error) {

                welcomeScreenView.showMessage(error);

            }
        });
    }
}
