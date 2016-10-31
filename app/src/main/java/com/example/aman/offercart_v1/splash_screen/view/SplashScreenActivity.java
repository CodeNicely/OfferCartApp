package com.example.aman.offercart_v1.splash_screen.view;

/**
 * Created by aman on 11/10/16.
 */
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.aman.offercart_v1.BuildConfig;
import com.example.aman.offercart_v1.R;
import com.example.aman.offercart_v1.helper.SharedPrefs;
import com.example.aman.offercart_v1.home.HomePage;
import com.example.aman.offercart_v1.splash_screen.models.RetrofitSplashScreenProvider;
import com.example.aman.offercart_v1.splash_screen.models.data.SplashScreenData;
import com.example.aman.offercart_v1.splash_screen.presenter.SplashScreenPresenter;
import com.example.aman.offercart_v1.splash_screen.presenter.SplashScreenPresenterImpl;
import com.example.aman.offercart_v1.welcome_screen.view.WelcomeScreenActivity;
import com.example.aman.offercart_v1.city.view.CityScreenActivity;


public class SplashScreenActivity extends Activity implements SplashScreenView
{

    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    private SharedPrefs sharedPrefs;
    private SplashScreenPresenter splashScreenPresenter;
    SplashScreenData splashScreenData;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);
        sharedPrefs = new SharedPrefs(this);
        splashScreenPresenter =new SplashScreenPresenterImpl(this,
                                new RetrofitSplashScreenProvider());

        splashScreenPresenter.requestSplash();

    }


    @Override
    public void showMessage(String message)
    {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar(boolean show)
    {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
        } else
        {
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
        else if (splashScreenData.isSuccess())
        {

/*
           if (sharedPrefs.isLoggedIn())
            {
                Intent city = new Intent(SplashScreenActivity.this, CityScreenActivity.class);
                startActivity(city);
                finish();
            } else
           {

               Intent Welcome = new Intent(SplashScreenActivity.this, WelcomeScreenActivity.class);
//               Welcome.putExtra("mobile","9174908579");
               startActivity(Welcome);
               finish();

*/
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {


                    if (sharedPrefs.isLoggedIn())
                    {
                        Log.d("Res",""+sharedPrefs.isLoggedIn());
                        Intent city = new Intent(SplashScreenActivity.this, HomePage.class);
                        startActivity(city);
                        finish();
                    } else
                    {
                        Log.d("Res",""+sharedPrefs.isLoggedIn());

                        Intent Welcome = new Intent(SplashScreenActivity.this, WelcomeScreenActivity.class);
                        startActivity(Welcome);
                        finish();
                    }

                }
            },5000);



        }
    }


}