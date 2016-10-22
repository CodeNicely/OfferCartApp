package com.example.aman.offercart_v1.SplashScreen.presenter;

import android.util.Log;

import com.example.aman.offercart_v1.SplashScreen.SplashScreenCallback;
import com.example.aman.offercart_v1.SplashScreen.models.RetrofitSplashScreenProvider;
import com.example.aman.offercart_v1.SplashScreen.models.SplashScreenProvider;
import com.example.aman.offercart_v1.SplashScreen.models.data.SplashScreenData;
import com.example.aman.offercart_v1.SplashScreen.view.SplashScreenActivity;
import com.example.aman.offercart_v1.SplashScreen.view.SplashScreenView;

/**
 * Created by aman on 13/10/16.
 */


public class SplashScreenPresenterImpl implements SplashScreenPresenter

    {

    private SplashScreenProvider splashScreenProvider;
    private SplashScreenView splashScreenView;
    private static final String LOG_TAG = "SplashScreenActivity";

    public SplashScreenPresenterImpl(SplashScreenActivity splashScreenView,
                                     RetrofitSplashScreenProvider retrofitSplashScreenProvider)
    {
        this.splashScreenView = splashScreenView;
        this.splashScreenProvider = retrofitSplashScreenProvider;
    }



    @Override
    public void requestSplash()
    {
        splashScreenView.showProgressBar(true);

        splashScreenProvider.requestSplash(new SplashScreenCallback()
        {
            @Override
            public void onSuccess(SplashScreenData splashScreenData)
            {

                //Log.d(LOG_TAG,"Reached");
                //splashScreenView.showProgressBar(false);
                if(splashScreenData.isSuccess())
                {
                    splashScreenView.version_check(splashScreenData);
                    splashScreenView.showMessage(splashScreenData.getMessage());
                    splashScreenView.showProgressBar(false);
                }
                else{

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
