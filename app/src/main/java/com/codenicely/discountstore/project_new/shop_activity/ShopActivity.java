package com.codenicely.discountstore.project_new.shop_activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.codenicely.discountstore.project_new.R;
import com.codenicely.discountstore.project_new.shop_login.view.ShopLoginFragment;
import com.codenicely.discountstore.project_new.shop_register.view.ShopRegisterFragment;
import com.codenicely.discountstore.project_new.shop_otp.view.ShopOtpFragment;

public class ShopActivity extends AppCompatActivity implements ShopRegisterFragment.OnFragmentInteractionListener,
           ShopOtpFragment.OnFragmentInteractionListener,ShopLoginFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        setFragment(new ShopOtpFragment());
    }

    public void setFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
        }
    }

    public void addFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.home_layout, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
