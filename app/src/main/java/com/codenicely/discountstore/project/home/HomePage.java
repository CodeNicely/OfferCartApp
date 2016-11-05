package com.codenicely.discountstore.project.home;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.codenicely.discountstore.project.R;
import com.codenicely.discountstore.project.about_us.view.AboutUsFragment;
import com.codenicely.discountstore.project.categories.view.CategoryFragment;
import com.codenicely.discountstore.project.city.view.CityFragment;
import com.codenicely.discountstore.project.contact_us.view.ContactUsFragment;
import com.codenicely.discountstore.project.developers.view.DeveloperFragment;
import com.codenicely.discountstore.project.helper.Keys;
import com.codenicely.discountstore.project.helper.SharedPrefs;
import com.codenicely.discountstore.project.my_orders.view.MyOrdersFragment;
import com.codenicely.discountstore.project.offer.view.ShopOfferFragment;
import com.codenicely.discountstore.project.shops.view.ShopFragment;
import com.codenicely.discountstore.project.wallet.view.WalletFragment;
import com.codenicely.discountstore.project.welcome_screen.view.WelcomeScreenActivity;
import com.payUMoney.sdk.PayUmoneySdkInitilizer;
import com.payUMoney.sdk.SdkConstants;
import com.payUMoney.sdk.walledSdk.SharedPrefsUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HomePageInterface {

    private String category_id = "1";
    private String shop_id ="0";
    private String amt = "10";
    private SharedPrefs sharedPrefs;
    private NavigationView navigationView;

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
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        sharedPrefs = new SharedPrefs(this);

        if (!sharedPrefs.getCity().equals("NA")) {
            setFragment(new CategoryFragment(), "Categories");

        } else {
            setFragment(new CityFragment(), "City");

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
        } else if (getFragmentManager().getBackStackEntryCount() == 0) {


            navigationView.getMenu().getItem(0).setChecked(true);
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

        if(sharedPrefs.getCity().equals("NA")){
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
            addFragment(new CityFragment(), "Select City");
            getSupportActionBar().hide();

        } else if (id == R.id.nav_wallet) {


            addFragment(new WalletFragment(), "Wallet");
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

        }else if(id==R.id.nav_logout)
        {
            sharedPrefs.setLogin(false);
            sharedPrefs.setCity("NA");
            sharedPrefs.setAccessToken("");
            sharedPrefs.setEmailId("");
            sharedPrefs.setUsername("");
            Intent in=new Intent(HomePage.this, WelcomeScreenActivity.class);
            startActivity(in);


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public String getCategory() {
        return category_id;
    }

    public String getShop_id() {
        return this.shop_id;
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
            //     getSupportActionBar().setTitle(title);
        }

    }

    @Override
    public void onCategorySelected(String category_id) {
        this.category_id = category_id;
        addFragment(new ShopFragment(), "Shops");
    }

    @Override
    public void onShopSelected(String shop_id1, String shop_name) {
        Bundle bundle = new Bundle();
        bundle.putString(Keys.KEY_SHOP_ID, shop_id1);
        ShopOfferFragment shopOfferFragment = new ShopOfferFragment();
        shopOfferFragment.setArguments(bundle);

        addFragment(shopOfferFragment, shop_name);
    }

    public void payement(String amount) {
        amt = amount;
        makePayment();
    }

    private boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String getTxnId() {
        return ("0nf7" + System.currentTimeMillis());
    }

    private double getAmount() {


        Double amount = 100.0;

        if (isDouble(amt.toString())) {
            amount = Double.parseDouble(amt.toString());
            return amount;
        } else {
            Toast.makeText(getApplicationContext(), "Paying Default Amount ₹10", Toast.LENGTH_LONG).show();
            return amount;
        }

    }

    public void makePayment(View view) {

        PayUmoneySdkInitilizer.PaymentParam.Builder builder = new PayUmoneySdkInitilizer.PaymentParam.Builder();

        builder.setAmount(getAmount())
                .setTnxId(getTxnId())
                .setPhone("8882434664")
                .setProductName("product_name")
                .setFirstName("piyush")
                .setEmail("piyush.jain@payu.in")
                .setsUrl("https://www.payumoney.com/mobileapp/payumoney/success.php")
                .setfUrl("https://www.payumoney.com/mobileapp/payumoney/failure.php")
                .setUdf1("")
                .setUdf2("")
                .setUdf3("")
                .setUdf4("")
                .setUdf5("")
                .setIsDebug(true)
                .setKey("gtKFFx")
                .setMerchantId("4928174");// Debug Merchant ID

        PayUmoneySdkInitilizer.PaymentParam paymentParam = builder.build();

            /*
             server side call required to calculate hash with the help of <salt>
             <salt> is already shared along with merchant <key>
             serverCalculatedHash =sha512(key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5|<salt>)

             (e.g.)

             sha512(FCstqb|0nf7|10.0|product_name|piyush|piyush.jain@payu.in||||||MBgjYaFG)

             9f1ce50ba8995e970a23c33e665a990e648df8de3baf64a33e19815acd402275617a16041e421cfa10b7532369f5f12725c7fcf69e8d10da64c59087008590fc

            */

        // Recommended
        //  calculateServerSideHashAndInitiatePayment(paymentParam);

           /*
            testing purpose

        */
        String serverCalculatedHash = "9f1ce50ba8995e970a23c33e665a990e648df8de3baf64a33e19815acd402275617a16041e421cfa10b7532369f5f12725c7fcf69e8d10da64c59087008590fc";
        paymentParam.setMerchantHash(serverCalculatedHash);
        PayUmoneySdkInitilizer.startPaymentActivityForResult(this, paymentParam);

    }


    public void makePayment() {

        PayUmoneySdkInitilizer.PaymentParam.Builder builder = new PayUmoneySdkInitilizer.PaymentParam.Builder();

        builder.setAmount(getAmount())
                .setTnxId(getTxnId())
                .setPhone("8882434664")
                .setProductName("product_name")
                .setFirstName("piyush")
                .setEmail("piyush.jain@payu.in")
                .setsUrl("https://www.payumoney.com/mobileapp/payumoney/success.php")
                .setfUrl("https://www.payumoney.com/mobileapp/payumoney/failure.php")
                .setUdf1("")
                .setUdf2("")
                .setUdf3("")
                .setUdf4("")
                .setUdf5("")
                .setIsDebug(true)
                .setKey("gtKFFx")
                .setMerchantId("4928174");// Debug Merchant ID

        PayUmoneySdkInitilizer.PaymentParam paymentParam = builder.build();

            /*
             server side call required to calculate hash with the help of <salt>
             <salt> is already shared along with merchant <key>
             serverCalculatedHash =sha512(key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5|<salt>)

             (e.g.)

             sha512(FCstqb|0nf7|10.0|product_name|piyush|piyush.jain@payu.in||||||MBgjYaFG)

             9f1ce50ba8995e970a23c33e665a990e648df8de3baf64a33e19815acd402275617a16041e421cfa10b7532369f5f12725c7fcf69e8d10da64c59087008590fc

            */

        // Recommended
        //  calculateServerSideHashAndInitiatePayment(paymentParam);

           /*
            testing purpose

        */
        String serverCalculatedHash = "9f1ce50ba8995e970a23c33e665a990e648df8de3baf64a33e19815acd402275617a16041e421cfa10b7532369f5f12725c7fcf69e8d10da64c59087008590fc";
        paymentParam.setMerchantHash(serverCalculatedHash);
        PayUmoneySdkInitilizer.startPaymentActivityForResult(HomePage.this, paymentParam);

    }

    public static String hashCal(String str) {
        byte[] hashseq = str.getBytes();
        StringBuilder hexString = new StringBuilder();
        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-512");
            algorithm.reset();
            algorithm.update(hashseq);
            byte messageDigest[] = algorithm.digest();
            for (byte aMessageDigest : messageDigest) {
                String hex = Integer.toHexString(0xFF & aMessageDigest);
                if (hex.length() == 1) {
                    hexString.append("0");
                }
                hexString.append(hex);
            }
        } catch (NoSuchAlgorithmException ignored) {
        }
        return hexString.toString();
    }

    private void calculateServerSideHashAndInitiatePayment(final PayUmoneySdkInitilizer.PaymentParam paymentParam) {

        // Replace your server side hash generator API URL
        String url = "https://test.payumoney.com/payment/op/calculateHashForTest";

        Toast.makeText(this, "Please wait... Generating hash from server ... ", Toast.LENGTH_LONG).show();

        /*
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.has(SdkConstants.STATUS)) {
                        String status = jsonObject.optString(SdkConstants.STATUS);
                        if (status != null || status.equals("1")) {

                            String hash = jsonObject.getString(SdkConstants.RESULT);
                            Log.i("app_activity", "Server calculated Hash :  " + hash);

                            paymentParam.setMerchantHash(hash);

                            PayUmoneySdkInitilizer.startPaymentActivityForResult(PaymentActivity.this, paymentParam);
                        } else {
                            Toast.makeText(PaymentActivity.this,
                                    jsonObject.getString(SdkConstants.RESULT),
                                    Toast.LENGTH_SHORT).show();
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                if (error instanceof NoConnectionError) {
                    Toast.makeText(PaymentActivity.this,
                            PaymentActivity.this.getString(R.string.connect_to_internet),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PaymentActivity.this,
                            error.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return paymentParam.getParams();
            }
        };
        Volley.newRequestQueue(this).add(jsonObjectRequest);*/
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PayUmoneySdkInitilizer.PAYU_SDK_PAYMENT_REQUEST_CODE) {

            /*if(data != null && data.hasExtra("result")){
              String responsePayUmoney = data.getStringExtra("result");
                if(SdkHelper.checkForValidString(responsePayUmoney))
                    showDialogMessage(responsePayUmoney);
            } else {
                showDialogMessage("Unable to get Status of Payment");
            }*/


            if (resultCode == RESULT_OK) {
                Log.i("Response", "Success - Payment ID : " + data.getStringExtra(SdkConstants.PAYMENT_ID));
                String paymentId = data.getStringExtra(SdkConstants.PAYMENT_ID);
                showDialogMessage("Payment Success Id : " + paymentId);
            } else if (resultCode == RESULT_CANCELED) {
                Log.i("Response", "failure");
                showDialogMessage("cancelled");
            } else if (resultCode == PayUmoneySdkInitilizer.RESULT_FAILED) {
                Log.i("app_activity", "failure");

                if (data != null) {
                    if (data.getStringExtra(SdkConstants.RESULT).equals("cancel")) {

                    } else {
                        showDialogMessage("failure");
                    }
                }
                //Write your code if there's no result
            } else if (resultCode == PayUmoneySdkInitilizer.RESULT_BACK) {
                Log.i("Response", "User returned without login");
                showDialogMessage("User returned without login");
            }
        }
    }

    private void showDialogMessage(String message) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Response");
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }


}
