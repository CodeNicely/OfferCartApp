package com.codenicely.discountstore.project1.payment.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.codenicely.discountstore.project1.R;
import com.codenicely.discountstore.project1.helper.Keys;
import com.codenicely.discountstore.project1.helper.SharedPrefs;
import com.codenicely.discountstore.project1.payment.model.RetrofitPaymentProvider;
import com.codenicely.discountstore.project1.payment.model.data.PaymentData;
import com.codenicely.discountstore.project1.payment.presenter.PaymentPresenter;
import com.codenicely.discountstore.project1.payment.presenter.PaymentPresenterImpl;
import com.payUMoney.sdk.PayUmoneySdkInitilizer;
import com.payUMoney.sdk.SdkConstants;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentActivity extends AppCompatActivity implements PaymentView {


    private static final String TAG = "PaymentActivity";
    private PaymentPresenter paymentPresenter;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private SharedPrefs sharedPrefs;
    private String transaction_id;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);
        paymentPresenter = new PaymentPresenterImpl(this, new RetrofitPaymentProvider());
        sharedPrefs = new SharedPrefs(this);
        if (getIntent() != null) {
            Bundle bundle = getIntent().getExtras();

            paymentPresenter.requestPaymentHash(bundle.getDouble(Keys.KEY_MONEY), sharedPrefs.getAccessToken());
            //    makePayment();
        } else {

            showMessage("");
        }

    }


    private boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


/*

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



             server side call required to calculate hash with the help of <salt>
             <salt> is already shared along with merchant <key>
             serverCalculatedHash =sha512(key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5|<salt>)

             (e.g.)

             sha512(FCstqb|0nf7|10.0|product_name|piyush|piyush.jain@payu.in||||||MBgjYaFG)

             9f1ce50ba8995e970a23c33e665a990e648df8de3baf64a33e19815acd402275617a16041e421cfa10b7532369f5f12725c7fcf69e8d10da64c59087008590fc




        // Recommended
        //  calculateServerSideHashAndInitiatePayment(paymentParam);



            testing purpose



        String serverCalculatedHash = "9f1ce50ba8995e970a23c33e665a990e648df8de3baf64a33e19815acd402275617a16041e421cfa10b7532369f5f12725c7fcf69e8d10da64c59087008590fc";
        paymentParam.setMerchantHash(serverCalculatedHash);
        PayUmoneySdkInitilizer.startPaymentActivityForResult(this, paymentParam);

    }

*/

    public void makePayment() {

        PayUmoneySdkInitilizer.PaymentParam.Builder builder = new PayUmoneySdkInitilizer.PaymentParam.Builder();

        builder
                .setAmount(100.0)
                .setTnxId("Txn Id")
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
        server side call required to calculate hash with the help of<salt>
        <salt > is already shared along with merchant<key>
                serverCalculatedHash = sha512(key | txnid | amount | productinfo | firstname | email | udf1 | udf2 | udf3 | udf4 | udf5 | < salt >)

        (e.g.)

                sha512(FCstqb | 0nf7 | 10.0 | product_name | piyush | piyush.jain @payu.in||||||
        MBgjYaFG)

        9f 1
        ce50ba8995e970a23c33e665a990e648df8de3baf64a33e19815acd402275617a16041e421cfa10b7532369f5f12725c7fcf69e8d10da64c59087008590fc

*/

        // Recommended
        //  calculateServerSideHashAndInitiatePayment(paymentParam);


//        testing purpose


        String serverCalculatedHash = "9f1ce50ba8995e970a23c33e665a990e648df8de3baf64a33e19815acd402275617a16041e421cfa10b7532369f5f12725c7fcf69e8d10da64c59087008590fc";
        paymentParam.setMerchantHash(serverCalculatedHash);
        PayUmoneySdkInitilizer.startPaymentActivityForResult(this, paymentParam);

    }

/*

    private void calculateServerSideHashAndInitiatePayment(final PayUmoneySdkInitilizer.PaymentParam paymentParam) {

        // Replace your server side hash generator API URL
        String url = "https://test.payumoney.com/payment/op/calculateHashForTest";

        Toast.makeText(this, "Please wait... Generating hash from server ... ", Toast.LENGTH_LONG).show();

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
        Volley.newRequestQueue(this).add(jsonObjectRequest);

    }
*/

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PayUmoneySdkInitilizer.PAYU_SDK_PAYMENT_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {
                Log.i("Response", "Success - Payment ID : " + data.getStringExtra(SdkConstants.PAYMENT_ID));
                String paymentId = data.getStringExtra(SdkConstants.PAYMENT_ID);
                //    showDialogMessage("Payment Success Id : " + paymentId);
                paymentPresenter.updatePaymentStatus(sharedPrefs.getAccessToken(), transaction_id);


            } else if (resultCode == RESULT_CANCELED) {
                Log.i("Response", "failure");
             //   showDialogMessage("cancelled");
                paymentPresenter.updatePaymentStatus(sharedPrefs.getAccessToken(), transaction_id);

            } else if (resultCode == PayUmoneySdkInitilizer.RESULT_FAILED) {
                Log.i("app_activity", "failure");

                paymentPresenter.updatePaymentStatus(sharedPrefs.getAccessToken(), transaction_id);

/*
                if (data != null) {
                    if (data.getStringExtra(SdkConstants.RESULT).equals("cancel")) {


                    } else {
                        showDialogMessage("failure");
                    }

//                    Log.e(TAG,data.getStringExtra());

                }
*/
                //Write your code if there's no result
            } else if (resultCode == PayUmoneySdkInitilizer.RESULT_BACK) {
                Log.i("Response", "User returned without login");
                showDialogMessage("User returned without login");
            }


/*
            if (data != null && data.hasExtra("result")) {
                String responsePayUmoney = data.getStringExtra("result");
                if (SdkHelper.checkForValidString(responsePayUmoney))
                    showDialogMessage(responsePayUmoney);
            } else {
                showDialogMessage("Unable to get Status of Payment");
            }

*/

        }


    }

    private void showDialogMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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

    @Override
    public void showMessage(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showLoading(boolean show) {

        if (show)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }

    @Override
    public void proceedToPayment(PaymentData paymentData) {


        PayUmoneySdkInitilizer.PaymentParam.Builder builder = new PayUmoneySdkInitilizer.PaymentParam.Builder();

        Log.d(TAG, paymentData.getMessage() + paymentData.getProduct_name() + paymentData.getAmount() + paymentData.getMerchant_id() + paymentData.getKey());

        transaction_id = paymentData.getTransaction_id();
        builder
                .setMerchantId(paymentData.getMerchant_id())
                .setKey(paymentData.getKey())
                .setIsDebug(false)
                .setAmount(paymentData.getAmount())
                .setTnxId(paymentData.getTransaction_id())
                .setPhone(paymentData.getMobile())
                .setProductName(paymentData.getProduct_name())
                .setFirstName(paymentData.getName())
                .setEmail(paymentData.getEmail())
                .setsUrl("https://www.payumoney.com/mobileapp/payumoney/success.php")
                .setfUrl("https://www.payumoney.com/mobileapp/payumoney/failure.php")
                .setUdf1("")
                .setUdf2("")
                .setUdf3("")
                .setUdf4("")
                .setUdf5("");

        PayUmoneySdkInitilizer.PaymentParam paymentParam = builder.build();
        paymentParam.setMerchantHash(paymentData.getServer_hash());
        PayUmoneySdkInitilizer.startPaymentActivityForResult(this, paymentParam);


    }
}
