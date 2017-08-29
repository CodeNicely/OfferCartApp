package com.codenicely.brandstore.project.splash_screen.view;

/**
 * Created by aman on 11/10/16.
 */

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.helper.MyApplication;
import com.codenicely.brandstore.project.helper.SharedPrefs;
import com.codenicely.brandstore.project.helper.image_loader.GlideImageLoader;
import com.codenicely.brandstore.project.helper.image_loader.ImageLoader;
import com.codenicely.brandstore.project.home.HomePage;
import com.codenicely.brandstore.project.shop_admin.shop_home.ShopHomePage;
import com.codenicely.brandstore.project.splash_screen.models.RetrofitSplashScreenProvider;
import com.codenicely.brandstore.project.splash_screen.models.data.SplashScreenData;
import com.codenicely.brandstore.project.splash_screen.presenter.SplashScreenPresenter;
import com.codenicely.brandstore.project.splash_screen.presenter.SplashScreenPresenterImpl;
import com.codenicely.brandstore.project.welcome_screen.view.WelcomeScreenActivity;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SplashScreenActivity extends Activity implements SplashScreenView {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.codenicely_logo)
    ImageView codenicely_logo;

    @BindView(R.id.logo)
    ImageView logo;
	private boolean LOCATION_REQUEST = false;
	public Context context;

    private ImageLoader imageLoader;
    private SharedPrefs sharedPrefs;
    private SplashScreenPresenter splashScreenPresenter;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);
		context = this;
		Dexter.initialize(context);

		imageLoader = new GlideImageLoader(this);
        Glide.with(this).load(R.drawable.codenicely_logo).into(codenicely_logo);
        Glide.with(this).load(R.drawable.brand_store_logo).into(logo);

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
    public void onVersionReceived(SplashScreenData splashScreenData) throws PackageManager.NameNotFoundException {

        if (getPackageManager().getPackageInfo(getPackageName(), 0).versionCode < splashScreenData.getVersion_code() && splashScreenData.getCompulsory_update() != 1) {


            final AlertDialog ad = new AlertDialog.Builder(this)
                    .create();
            ad.setCancelable(false);
            ad.setTitle("App Update Available");
            ad.setMessage("Please update the app for better experience");
            ad.setButton(DialogInterface.BUTTON_POSITIVE, "Update", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ad.cancel();
                    final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                    }

                    finish();
                }
            });
            ad.setButton(DialogInterface.BUTTON_NEGATIVE, "Not Now", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ad.cancel();

                    if (sharedPrefs.isLoggedIn()) {
						if (checkPermissionForLocation()) {
							startActivity(new Intent(SplashScreenActivity.this, HomePage.class));
							finish();
						} else {
							if (requestLocationPermission()) {
								startActivity(new Intent(SplashScreenActivity.this, HomePage.class));
								finish();
							}

						}
                    } else if (sharedPrefs.isLoggedInasShop()) {
						if (checkPermissionForLocation()) {
							startActivity(new Intent(SplashScreenActivity.this, ShopHomePage.class));
							finish();
						} else {
							if (requestLocationPermission()) {
								startActivity(new Intent(SplashScreenActivity.this, ShopHomePage.class));
								finish();
							}

						}

                    } else {
						if (checkPermissionForLocation()) {
							startActivity(new Intent(SplashScreenActivity.this, WelcomeScreenActivity.class));
							finish();
						} else {
							if (requestLocationPermission()) {
								startActivity(new Intent(SplashScreenActivity.this, WelcomeScreenActivity.class));
								finish();
							}

						}

                    }

                }
            });
            ad.show();


        } else if (getPackageManager().getPackageInfo(getPackageName(), 0).versionCode < splashScreenData.getVersion_code() && splashScreenData.getCompulsory_update() == 1) {

            final AlertDialog ad = new AlertDialog.Builder(this)

                    .create();
            ad.setCancelable(false);
            ad.setTitle("App Update Available");
            ad.setMessage("This is a compulsory Update . Please Update the app to enjoy our services");
            ad.setButton(DialogInterface.BUTTON_POSITIVE, "Update", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ad.cancel();
                    final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                    }

                    finish();
                }
            });
            ad.show();


        } else {
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (sharedPrefs.isLoggedIn()) {
						if (checkPermissionForLocation()) {
							startActivity(new Intent(SplashScreenActivity.this, HomePage.class));
							finish();
						} else {
							if (requestLocationPermission()) {
								startActivity(new Intent(SplashScreenActivity.this, HomePage.class));
								finish();
							}
						}
                    } else if (sharedPrefs.isLoggedInasShop()) {
						if (checkPermissionForLocation()) {
							startActivity(new Intent(SplashScreenActivity.this, ShopHomePage.class));
							finish();
						} else {
							if (requestLocationPermission()) {
								startActivity(new Intent(SplashScreenActivity.this, ShopHomePage.class));
								finish();
							}

						}

                    } else {
						if (checkPermissionForLocation()) {
							startActivity(new Intent(SplashScreenActivity.this, WelcomeScreenActivity.class));
							finish();
						} else {
							if (requestLocationPermission()) {
								startActivity(new Intent(SplashScreenActivity.this, WelcomeScreenActivity.class));
								finish();
							}

						}
                    }

                }
            }, 300);
        }


    }

    @Override
    public void onFailed() {

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (sharedPrefs.isLoggedIn()) {
                    if (checkPermissionForLocation()) {
						startActivity(new Intent(SplashScreenActivity.this, HomePage.class));
						finish();
                    } else {
                        if (requestLocationPermission()) {
							startActivity(new Intent(SplashScreenActivity.this, HomePage.class));
							finish();
                        }

                    }

                } else if (sharedPrefs.isLoggedInasShop()) {
					if (checkPermissionForLocation()) {
						startActivity(new Intent(SplashScreenActivity.this, ShopHomePage.class));
						finish();
					} else {
						if (requestLocationPermission()) {
							startActivity(new Intent(SplashScreenActivity.this, ShopHomePage.class));
							finish();
						}

					}
                } else {
					if (checkPermissionForLocation()) {
						startActivity(new Intent(SplashScreenActivity.this, WelcomeScreenActivity.class));
						finish();
					} else {
						if (requestLocationPermission()) {
							startActivity(new Intent(SplashScreenActivity.this, WelcomeScreenActivity.class));
							finish();
						}
					}
                }

            }
        }, 300);

    }
    private boolean checkPermissionForLocation() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
			Log.d("CHECK---","true");
            return true;

        } else {
			Log.d("CHECK---","false");
            return false;
        }
    }

    private boolean requestLocationPermission() {

		Log.d("REQQQ","chech true");


		Dexter.checkPermission(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {

				Log.d("REQUEST---","true");
				splashScreenPresenter.requestSplash(MyApplication.getFcm());

                LOCATION_REQUEST = true;
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {

				Log.d("REQUEST---","false");
				android.os.Process.killProcess(android.os.Process.myPid());
				System.exit(1);
                LOCATION_REQUEST = false;
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
				Log.d("REQUEST---","nothing");
				token.continuePermissionRequest();


            }
        }, Manifest.permission.ACCESS_FINE_LOCATION);
		Log.d("REQQQ","check finish");

		return LOCATION_REQUEST;

    }




}