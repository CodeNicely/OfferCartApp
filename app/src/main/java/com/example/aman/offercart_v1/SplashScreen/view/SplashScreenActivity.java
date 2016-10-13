package com.example.aman.offercart_v1.SplashScreen.view;

/**
 * Created by aman on 11/10/16.
 */
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.aman.offercart_v1.BuildConfig;
import com.example.aman.offercart_v1.CityScreen.view.City_Screen;
import com.example.aman.offercart_v1.MainActivity;
import com.example.aman.offercart_v1.R;
import com.example.aman.offercart_v1.SharedPrefs;
import com.example.aman.offercart_v1.SplashScreen.models.RetrofitSplashScreenProvider;
import com.example.aman.offercart_v1.SplashScreen.models.data.SplashScreenData;
import com.example.aman.offercart_v1.SplashScreen.presenter.SplashScreenPresenter;
import com.example.aman.offercart_v1.SplashScreen.presenter.SplashScreenPresenterImpl;
import com.example.aman.offercart_v1.WelcomeScreen.view.Welcome_screen;


public class SplashScreenActivity extends Activity implements SplashScreenView
{

    // Splash screen timer
    //private static int SPLASH_TIME_OUT = 3000;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private SharedPrefs sharedPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);
        sharedPrefs = new SharedPrefs(this);
        final SplashScreenPresenter splashScreenPresenter =
                new SplashScreenPresenterImpl(this, new RetrofitSplashScreenProvider());


        /*new Handler().postDelayed(
                new Runnable()
                {

                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        // Start your app main activity
                        Intent i = new Intent(SplashScreenActivity.this, Welcome_screen.class);
                        startActivity(i);

                        finish();
                    }
                }, SPLASH_TIME_OUT);*/

    }


    @Override
    public void showMessage(String message)
    {

    }

    @Override
    public void showProgressBar(boolean show)
    {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }

    }

    @Override
    public void version_check(SplashScreenData splashScreenData)
    {
        int i = splashScreenData.getVersion();

        if (i > BuildConfig.VERSION_CODE)
        {
            final Dialog dialog = new Dialog(SplashScreenActivity.this);
            dialog.setContentView(R.layout.activity_splash_dialog_box);
            Button btn = (Button) dialog.findViewById(R.id.dialog_button);
            TextView rules = (TextView) dialog.findViewById(R.id.text);



            if (splashScreenData.getCompulsory_update() == 1)
            {
                rules.setText("Major Update.");
                dialog.setCancelable(false);
            }
            else
            {
                rules.setText("Please Update");
                dialog.setCancelable(true);
            }

            dialog.setTitle("App Update");
            btn.setText("Update");
            dialog.show();
            btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v)
                {

                    final String appPackageName = getPackageName();
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse
                                ("market://details?id="
                                + appPackageName)));
                    } catch (android.content.ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(
                                "https://play.google.com/store/apps/details?id=" + appPackageName)));
                    }
                }
            });



        }
        else if (splashScreenData.isSuccess()) {

            if (sharedPrefs.isLoggedIn()) {
                Intent in = new Intent(SplashScreenActivity.this, City_Screen.class);
                startActivity(in);
                finish();
            } else {
                Intent signIn = new Intent(SplashScreenActivity.this, Welcome_screen.class);
                startActivity(signIn);
                finish();

            }
        }
    }


}