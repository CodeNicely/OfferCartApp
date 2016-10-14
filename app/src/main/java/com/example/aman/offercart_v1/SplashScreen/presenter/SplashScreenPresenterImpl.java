package com.example.aman.offercart_v1.SplashScreen.presenter;

import com.example.aman.offercart_v1.SplashScreen.SplashScreenCallback;
import com.example.aman.offercart_v1.SplashScreen.models.RetrofitSplashScreenProvider;
import com.example.aman.offercart_v1.SplashScreen.models.SplashScreenProvider;
import com.example.aman.offercart_v1.SplashScreen.models.data.SplashScreenData;
import com.example.aman.offercart_v1.SplashScreen.view.SplashScreenActivity;
import com.example.aman.offercart_v1.SplashScreen.view.SplashScreenView;

/**
 * Created by aman on 13/10/16.
 */
public class SplashScreenPresenterImpl implements SplashScreenPresenter {

    private SplashScreenProvider splashScreenProvider;
    private SplashScreenView splashScreenView;

    public SplashScreenPresenterImpl(SplashScreenActivity splashScreenView,
                                     RetrofitSplashScreenProvider retrofitSplashScreenProvider) {
        this.splashScreenView = splashScreenView;
        this.splashScreenProvider = retrofitSplashScreenProvider;
    }



    @Override
    public void requestSplash(int version, String message, boolean success, int compulsory_update)
    {
        splashScreenView.showProgressBar(true);


        splashScreenProvider.requestSplash(version, message, success,
                compulsory_update, new SplashScreenCallback()
        {
            @Override
            public void onSuccess(SplashScreenData splashScreenData) {

                splashScreenView.showProgressBar(false);
                splashScreenView.showMessage("Success");



            }

            @Override
            public void onFailure(String error) {
                splashScreenView.showProgressBar(true);
                splashScreenView.showMessage("Failed");

            }
        });




    }
}
