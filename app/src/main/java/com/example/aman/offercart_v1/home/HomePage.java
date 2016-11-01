package com.example.aman.offercart_v1.home;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
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
import android.view.MenuItem;

import com.example.aman.offercart_v1.R;
import com.example.aman.offercart_v1.categories.view.CategoryFragment;
import com.example.aman.offercart_v1.city.view.CityFragment;
import com.example.aman.offercart_v1.city.view.CityScreenActivity;
import com.example.aman.offercart_v1.helper.SharedPrefs;
import com.example.aman.offercart_v1.offer.view.ShopOfferFragment;
import com.example.aman.offercart_v1.shops.view.ShopFragment;
import com.example.aman.offercart_v1.wallet.view.WalletFragment;

public class HomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HomePageInterface {

    private String category_id = "1";
    private String shop_id = "1";
    private SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        sharedPrefs = new SharedPrefs(this);

        if (!sharedPrefs.getCity().equals("NA")) {
            setFragment(new CategoryFragment(), "Categories");

        } else {
            setFragment(new CityFragment(), "City");

        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getFragmentManager().getBackStackEntryCount() == 0) {


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
            ad.show();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            setFragment(new CategoryFragment(), "Category ");
            getSupportActionBar().show();
        } else if (id == R.id.nav_changeCity) {

            addFragment(new CityFragment(), "Select City");

            getSupportActionBar().hide();

        } else if (id == R.id.nav_wallet) {

            addFragment(new WalletFragment(), "Wallet");
            getSupportActionBar().hide();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public String getCategory() {
        return category_id;
    }

    public String getShop_id() {
        return shop_id;
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

    void addFragment(Fragment fragment, String title) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.home_layout, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            getSupportActionBar().setTitle(title);
        }

    }

    @Override
    public void onCategorySelected(String category_id) {
        this.category_id = category_id;
        addFragment(new ShopFragment(), "Shops");
    }

    @Override
    public void onShopSelected(String shop_id, String shop_name) {
        this.shop_id = shop_id;
        addFragment(new ShopOfferFragment(), shop_name);
    }

}
