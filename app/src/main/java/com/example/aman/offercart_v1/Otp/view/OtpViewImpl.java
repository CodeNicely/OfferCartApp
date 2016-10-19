package com.example.aman.offercart_v1.Otp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.aman.offercart_v1.R;
import com.example.aman.offercart_v1.cityScreen.view.CityScreenActivity;


/**
 * Created by aman on 16/10/16.
 */
public class OtpViewImpl extends Activity implements OtpView {

    EditText otp;
    Button submitButton;
    ProgressBar progressbar;
    String mobile;
<<<<<<< HEAD:Downloads/OfferCartApp/app/src/main/java/com/example/aman/offercart_v1/Otp/view/OtpViewImpl.java
    String otp1;
=======
>>>>>>> 010a0f397f1e129cecfa05bd03b46277f631d483:app/src/main/java/com/example/aman/offercart_v1/Otp/view/OtpViewImpl.java

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

<<<<<<< HEAD:Downloads/OfferCartApp/app/src/main/java/com/example/aman/offercart_v1/Otp/view/OtpViewImpl.java
        mobile = getIntent().getExtras().getString("mobile");
        progressbar = (ProgressBar) findViewById(R.id.progressBarView);
=======
        mobile=getIntent().getExtras().getString("mobile");

>>>>>>> 010a0f397f1e129cecfa05bd03b46277f631d483:app/src/main/java/com/example/aman/offercart_v1/Otp/view/OtpViewImpl.java
        otp = (EditText) findViewById(R.id.editText);
        submitButton = (Button) findViewById(R.id.button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otp1 = otp.getText().toString();

            }
        });

    }

    @Override
    public void showLoading(boolean show) {
        if (show) {
            progressbar.setVisibility(View.VISIBLE);
        } else {
            progressbar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    //@Override
    //public void onOtpVerified() {
    //Intent a=new Intent(OtpViewImpl.this, CityScreenActivity.class);

    // startActivity(a);
    //finish();

    //  }
}