package com.codenicely.brandstore.project.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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
import android.widget.Toast;

import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.about_us.view.AboutUsFragment;
import com.codenicely.brandstore.project.categories.view.CategoryFragment;
import com.codenicely.brandstore.project.city.view.CityFragment;
import com.codenicely.brandstore.project.contact_us.view.ContactUsFragment;
import com.codenicely.brandstore.project.developers.view.DeveloperFragment;
import com.codenicely.brandstore.project.helper.Keys;
import com.codenicely.brandstore.project.helper.SharedPrefs;
import com.codenicely.brandstore.project.my_offers.view.MyOrdersFragment;
import com.codenicely.brandstore.project.offer.view.OfferFragment;
import com.codenicely.brandstore.project.shops.view.ShopFragment;
import com.codenicely.brandstore.project.state.view.StateFragment;
import com.codenicely.brandstore.project.welcome_screen.view.WelcomeScreenActivity;

public class HomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
		StateFragment.OnFragmentInteractionListener,
						   HomePageInterface {

    private String amt = "10";
    private SharedPrefs sharedPrefs;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activity);

        setTheme(R.style.AppThemeUser);


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

        if( sharedPrefs.getState().equals("NA")){
            setFragment(new StateFragment(), "State");
        }else if (sharedPrefs.getCity().equals("NA")) {
            setFragment(new CityFragment(), "City");
        }else {
            setFragment(new CategoryFragment(), "Home");
        }

        int backStackCount = getSupportFragmentManager().getBackStackEntryCount();
        for (int i = 0; i < backStackCount; i++) {

            // Get the back stack fragment id.
            int backStackId = getSupportFragmentManager().getBackStackEntryAt(i).getId();

            getFragmentManager().popBackStack(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        }
        /*if (getIntent().getBooleanExtra(Keys.KEY_OPEN_WALLET, false)) {

            addFragment(new WalletFragment(), "WalletFragment");
            getSupportActionBar().hide();

        }*/
        //when app is in background or closed..!
        Intent startingIntent = getIntent();
        if (startingIntent.getExtras() != null) {
            String msg = startingIntent.getStringExtra("shop_id");
            String name = startingIntent.getStringExtra("shop_name");
            int id = Integer.valueOf(msg);
            if (id != 0 && name != null) {
                Log.d("open offers fragment 1", "offers");
                onShopSelected(id, name);
            }
        }

    }

    //when app is in foreground..!
    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getStringExtra("FcmActivity").equals("True")) {
            String msg = intent.getStringExtra("shop_id");
            String name = intent.getStringExtra("shop_name");
            int id = Integer.valueOf(msg);
            if (id != 0 && name != null) {
                Log.d("open offers fragment", "offers");
                onShopSelected(id, name);
            }
        }

    }

    @Override
    public void onBackPressed() {
       // Toast.makeText(getApplicationContext(),getFragmentManager().getBackStackEntryCount()+" ",Toast.LENGTH_SHORT).show();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
//            navigationView.getMenu().getItem(0).setChecked(true);
			getFragmentManager().popBackStack();
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

        if (sharedPrefs.getCity().equals("NA")) {
            addFragment(new CityFragment(), "Select City");
            getSupportActionBar().hide();
            Toast.makeText(this, "Please Select City First", Toast.LENGTH_SHORT).show();
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }


        if (id == R.id.nav_home) {

            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_changeCity) {
            addFragment(new StateFragment(), "Select State");
            getSupportActionBar().hide();

        } else if (id == R.id.nav_my_orders) {
            addFragment(new MyOrdersFragment(), "My Orders");
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
            sharedPrefs.setLogin(false);
            sharedPrefs.setCity("NA");
			sharedPrefs.setState("NA");
			sharedPrefs.setState(0);
            sharedPrefs.setAccessToken("");
            sharedPrefs.setEmailId("");
            sharedPrefs.setUsername("");
            Intent in = new Intent(HomePage.this, WelcomeScreenActivity.class);
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
            fragmentTransaction.addToBackStack(title);
            fragmentTransaction.commit();
            //     getSupportActionBar().setTitle(title);
        }
    }

    public void onCategorySelected(int category_id, String category_name) {
        Bundle bundle = new Bundle();
        bundle.putInt(Keys.KEY_CATEGORY_ID, category_id);
        ShopFragment shopFragment = new ShopFragment();
        shopFragment.setArguments(bundle);
        addFragment(shopFragment, category_name);
    }


    public void onShopSelected(int shop_id, String shop_name) {
        Bundle bundle = new Bundle();
        bundle.putInt(Keys.KEY_SHOP_ID, shop_id);
        OfferFragment offerFragment = new OfferFragment();
        offerFragment.setArguments(bundle);
        addFragment(offerFragment, shop_name);
    }



    /*

      This whole code was written here in Activity by Iket , I Meghal Shifted it to Payment Activity :) :D
  */
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

	@Override
	public void onFragmentInteraction(Uri uri) {

	}
}
