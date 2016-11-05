package com.codenicely.discountstore.project.splash_screen.view;

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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codenicely.discountstore.project.BuildConfig;
import com.codenicely.discountstore.project.R;
import com.codenicely.discountstore.project.helper.MyApplication;
import com.codenicely.discountstore.project.helper.SharedPrefs;
import com.codenicely.discountstore.project.helper.image_loader.GlideImageLoader;
import com.codenicely.discountstore.project.helper.image_loader.ImageLoader;
import com.codenicely.discountstore.project.home.HomePage;
import com.codenicely.discountstore.project.splash_screen.models.RetrofitSplashScreenProvider;
import com.codenicely.discountstore.project.splash_screen.models.data.SplashScreenData;
import com.codenicely.discountstore.project.splash_screen.presenter.SplashScreenPresenter;
import com.codenicely.discountstore.project.splash_screen.presenter.SplashScreenPresenterImpl;
import com.codenicely.discountstore.project.welcome_screen.view.WelcomeScreenActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SplashScreenActivity extends Activity implements SplashScreenView {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.codenicely_logo)
    ImageView codenicely_logo;

    @BindView(R.id.logo)
    ImageView logo;

    private ImageLoader imageLoader;
    private SharedPrefs sharedPrefs;
    private SplashScreenPresenter splashScreenPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);
        imageLoader = new GlideImageLoader(this);
        Glide.with(this).load(R.drawable.codenicely_logo).into(codenicely_logo);
        Glide.with(this).load(R.drawable.discount_store_logo).into(logo);

        sharedPrefs = new SharedPrefs(this);
        splashScreenPresenter = new SplashScreenPresenterImpl(this,
                new RetrofitSplashScreenProvider());
        splashScreenPresenter.requestSplash(MyApplication.getFcm());
    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void version_check(SplashScreenData splashScreenData) {

        int i = splashScreenData.getVersion();

        if (i > BuildConfig.VERSION_CODE) {
            final Dialog dialog = new Dialog(SplashScreenActivity.this);
            dialog.setContentView(R.layout.activity_splash_dialog_box);
            Button btn = (Button) dialog.findViewById(R.id.dialog_button);
            TextView rules = (TextView) dialog.findViewById(R.id.text);


            if (splashScreenData.getCompulsory_update() == 1) {
                rules.setText("Major Update.");
                dialog.setCancelable(false);
            } else {
                rules.setText("Please Update");
                dialog.setCancelable(true);
            }

            dialog.setTitle("App Update");
            btn.setText("Update");
            dialog.show();
            btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

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


        } else if (splashScreenData.isSuccess()) {

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
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {


                    if (sharedPrefs.isLoggedIn()) {
                        Log.d("Res", "" + sharedPrefs.isLoggedIn());
                        Intent city = new Intent(SplashScreenActivity.this, HomePage.class);
                        startActivity(city);
                        finish();
                    } else {
                        Log.d("Res", "" + sharedPrefs.isLoggedIn());

                        Intent Welcome = new Intent(SplashScreenActivity.this, WelcomeScreenActivity.class);
                        startActivity(Welcome);
                        finish();
                    }

                }
            }, 2500);

        }
    }


}