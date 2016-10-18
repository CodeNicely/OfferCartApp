package com.example.aman.offercart_v1.WelcomeScreen.presenter;

import com.example.aman.offercart_v1.WelcomeScreen.WelcomeScreenCallback;
import com.example.aman.offercart_v1.WelcomeScreen.models.WelcomeScreenProvider;
import com.example.aman.offercart_v1.WelcomeScreen.models.data.WelcomeScreenData;
import com.example.aman.offercart_v1.WelcomeScreen.view.WelcomeScreenView;

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
    public void getHomeData()
    {
        welcomeScreenProvider.getWelcomeData(new WelcomeScreenCallback() {
            @Override
            public void onSuccess(WelcomeScreenData welcomeScreenData) {
                if (welcomeScreenData.isSuccess()){
                    welcomeScreenView.setData(welcomeScreenData.getSlider_data());
                    welcomeScreenView.showMessage("Success");

                }
                else{
                    welcomeScreenView.showMessage("Something went wrong");

                }
            }

            @Override
            public void onFailure(String error) {

                welcomeScreenView.showMessage("Failed");

            }
        });
    }
}
