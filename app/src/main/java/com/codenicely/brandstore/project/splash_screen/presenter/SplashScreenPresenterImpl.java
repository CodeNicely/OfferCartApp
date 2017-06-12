package com.codenicely.brandstore.project.splash_screen.presenter;

import android.content.pm.PackageManager;

import com.codenicely.brandstore.project.splash_screen.SplashScreenCallback;
import com.codenicely.brandstore.project.splash_screen.models.RetrofitSplashScreenProvider;
import com.codenicely.brandstore.project.splash_screen.models.SplashScreenProvider;
import com.codenicely.brandstore.project.splash_screen.models.data.SplashScreenData;
import com.codenicely.brandstore.project.splash_screen.view.SplashScreenActivity;
import com.codenicely.brandstore.project.splash_screen.view.SplashScreenView;

/**
 * Created by aman on 13/10/16.
 */


public class SplashScreenPresenterImpl implements SplashScreenPresenter

{

    private static final String LOG_TAG = "SplashScreenActivity";
    private SplashScreenProvider splashScreenProvider;
    private SplashScreenView splashScreenView;

    public SplashScreenPresenterImpl(SplashScreenActivity splashScreenView,
                                     RetrofitSplashScreenProvider retrofitSplashScreenProvider) {
        this.splashScreenView = splashScreenView;
        this.splashScreenProvider = retrofitSplashScreenProvider;
    }


    @Override
    public void requestSplash(String fcm) {
        splashScreenView.showProgressBar(true);

        splashScreenProvider.requestSplash(fcm, new SplashScreenCallback() {
            @Override
            public void onSuccess(SplashScreenData splashScreenData) throws PackageManager.NameNotFoundException {

                //Log.d(LOG_TAG,"Reached");
                //splashScreenView.showProgressBar(false);
                if (splashScreenData.isSuccess()) {
                    splashScreenView.onVersionReceived(splashScreenData);
                    splashScreenView.showProgressBar(false);
                } else {
                    //splashScreenView.version_check(splashScreenData);
                    splashScreenView.onFailed();
                    splashScreenView.showMessage(splashScreenData.getMessage());
                    splashScreenView.showProgressBar(false);
                }

            }

            @Override
            public void onFailure(String error) {
                splashScreenView.showProgressBar(false);
                splashScreenView.showMessage("No Internet Connection");

            }
        });


    }
}
