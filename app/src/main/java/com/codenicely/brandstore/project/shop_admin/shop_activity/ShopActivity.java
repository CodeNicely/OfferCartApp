package com.codenicely.brandstore.project.shop_admin.shop_activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_forgot_password.view.ShopForgotPasswordFragment;
import com.codenicely.brandstore.project.shop_admin.shop_login.view.ShopLoginFragment;
import com.codenicely.brandstore.project.shop_admin.shop_otp.view.ShopOtpFragment;
import com.codenicely.brandstore.project.shop_admin.shop_register.view.ShopRegisterFragment;

public class ShopActivity extends AppCompatActivity implements ShopRegisterFragment.OnFragmentInteractionListener,
           ShopOtpFragment.OnFragmentInteractionListener,ShopLoginFragment.OnFragmentInteractionListener
,ShopForgotPasswordFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        setFragment(new ShopLoginFragment());

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
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
