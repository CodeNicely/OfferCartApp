package com.codenicely.brandstore.project.shop_admin.shop_home;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;

import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.about_us.view.AboutUsFragment;
import com.codenicely.brandstore.project.contact_us.view.ContactUsFragment;
import com.codenicely.brandstore.project.developers.view.DeveloperFragment;
import com.codenicely.brandstore.project.helper.SharedPrefs;
import com.codenicely.brandstore.project.home.HomePageInterface;
import com.codenicely.brandstore.project.shop_admin.shop_change_password.view.ShopChangePasswordFragment;
import com.codenicely.brandstore.project.shop_admin.shop_add_offer.view.OfferAddFragment;
import com.codenicely.brandstore.project.shop_admin.shop_add_subscription.view.AddSubscriptionFragment;
import com.codenicely.brandstore.project.shop_admin.shop_edit_offer.view.OfferEditFragment;
import com.codenicely.brandstore.project.shop_admin.shop_offerlist.view.ShopOfferListFragment;
import com.codenicely.brandstore.project.shop_admin.shop_privacy_policy.ShopPrivacyPolicyFragment;
import com.codenicely.brandstore.project.shop_admin.shop_profile_edit.view.EditShopProfileFragment;
import com.codenicely.brandstore.project.shop_admin.shop_profile_show.view.ShowShopProfileFragment;
import com.codenicely.brandstore.project.shop_admin.shop_refund_and_cancellation.ShopRefundCancellationFragment;
import com.codenicely.brandstore.project.shop_admin.shop_terms_and_conditions.ShopTNCFragment;
import com.codenicely.brandstore.project.welcome_screen.view.WelcomeScreenActivity;

public class ShopHomePage extends AppCompatActivity
        implements
        NavigationView.OnNavigationItemSelectedListener,
        HomePageInterface,
        AboutUsFragment.OnFragmentInteractionListener,
        EditShopProfileFragment.OnFragmentInteractionListener,
        ShowShopProfileFragment.OnFragmentInteractionListener,
        DeveloperFragment.OnFragmentInteractionListener,
        ContactUsFragment.OnFragmentInteractionListener,
        OfferAddFragment.OnFragmentInteractionListener,
        OfferEditFragment.OnFragmentInteractionListener,
        ShopChangePasswordFragment.OnFragmentInteractionListener,
        com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_change_password.view.ShopChangePasswordFragment.OnFragmentInteractionListener,
        AddSubscriptionFragment.OnFragmentInteractionListener,
        ShopOfferListFragment.OnFragmentInteractionListener,
        ShopPrivacyPolicyFragment.OnFragmentInteractionListener,
        ShopRefundCancellationFragment.OnFragmentInteractionListener,
        ShopTNCFragment.OnFragmentInteractionListener
        {

    private SharedPrefs sharedPrefs;
    private NavigationView navigationView;
    boolean fp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppThemeShop);
        super.onCreate(savedInstanceState);
        fp = getIntent().getBooleanExtra("fp",false);

        setContentView(R.layout.activity_shop_home_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        sharedPrefs = new SharedPrefs(this);

        if (fp==true){
            com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_change_password.view.ShopChangePasswordFragment shopChangePasswordFragment= new com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_change_password.view.ShopChangePasswordFragment();
            addFragment(shopChangePasswordFragment,"Change Password");
        }else {
            ShopOfferListFragment shopOfferListFragment = new ShopOfferListFragment();
            setFragment(shopOfferListFragment, "Home");
        }

        int backStackCount = getSupportFragmentManager().getBackStackEntryCount();
        for (int i = 0; i < backStackCount; i++) {

            // Get the back stack fragment id.
            int backStackId = getSupportFragmentManager().getBackStackEntryAt(i).getId();

            getFragmentManager().popBackStack(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getSupportFragmentManager().getBackStackEntryCount() > 0) {

         super.onBackPressed();

        } else {

            final AlertDialog ad = new AlertDialog.Builder(this)
                    .create();
            ad.setCancelable(false);
            ad.setTitle("Exit ?");
            ad.setMessage("Do you really want to exit ?");
            ad.setButton(DialogInterface.BUTTON_POSITIVE, "yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ad.cancel();
                    finish();
                }
            });
            ad.setButton(DialogInterface.BUTTON_NEGATIVE, "no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ad.cancel();

                }
            });
            ad.setOnShowListener( new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface arg0) {
                    ad.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                    ad.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                }
            });
            ad.show();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            Intent intent = new Intent(this, ShopHomePage.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.change_password) {
            addFragment(new ShopChangePasswordFragment(), "Change Password");
            getSupportActionBar().hide();
        } else if (id == R.id.terms_conditions) {
            addFragment(new ShopTNCFragment(), "Terms and Conditions");
            getSupportActionBar().hide();
        } else if (id == R.id.refund_cancellation) {
            addFragment(new ShopRefundCancellationFragment(), "Refund and Cancellation ");
            getSupportActionBar().hide();
        } else if (id == R.id.privacy_policy) {
            addFragment(new ShopPrivacyPolicyFragment(), "Privacy and Policy");
            getSupportActionBar().hide();
        } else if (id == R.id.nav_profile) {
            addFragment(new ShowShopProfileFragment(), "Profile");
            getSupportActionBar().hide();

        } else if (id == R.id.nav_contact_us) {

            addFragment(new ContactUsFragment(), "Contact Us");
            getSupportActionBar().hide();
        } else if (id == R.id.nav_about_us) {
            addFragment(new AboutUsFragment(), "About Us");
            getSupportActionBar().hide();

        } else if (id == R.id.nav_developer) {
            addFragment(new DeveloperFragment(), "Developers");
            getSupportActionBar().hide();

        } else if (id == R.id.nav_logout) {
            sharedPrefs.setShopLogin(false);
            sharedPrefs.setAccessTokenShop("");
            Intent in = new Intent(ShopHomePage.this, WelcomeScreenActivity.class);
            startActivity(in);
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void setFragment(Fragment fragment, String title) {

        if (fragment != null) {



            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.home_layout, fragment);
            fragmentTransaction.commit();
            getSupportActionBar().setTitle(title);
            }

    }

    public void addFragment(Fragment fragment, String title) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.home_layout, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            getSupportActionBar().hide();

        }
    }


    private void showDialogMessage(String message) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("CityData");
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void onOfferAdded(){
        final AlertDialog ad = new AlertDialog.Builder(this)
                                       .create();
        Log.d("QWERTYUIOP-----------","sdansdnmsdnsdk");
        ad.setIcon(R.drawable.brand_store_logo);
        ad.setTitle("Offer Successfully Published" );
        ad.setMessage("To show your offers to the world register your shop" + "\n" );
        ad.setButton(DialogInterface.BUTTON_POSITIVE, "Register Now", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
				AddSubscriptionFragment addSubscriptionFragment= new AddSubscriptionFragment();
				addFragment(addSubscriptionFragment,"Add Subscription");
				ad.cancel();
            }
        });
		ad.setOnShowListener( new DialogInterface.OnShowListener() {
		  @Override
		  public void onShow(DialogInterface arg0) {
			  ad.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
		  }
	  });
        ad.show();




    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
