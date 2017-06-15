package com.codenicely.brandstore.project.login.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.login.models.RetrofitLoginScreenProvider;
import com.codenicely.brandstore.project.login.presenter.LoginScreenPresenter;
import com.codenicely.brandstore.project.login.presenter.LoginScreenPresenterImpl;
import com.codenicely.brandstore.project.verify_otp.view.OtpViewImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aman on 15/10/16.
 */
public class LoginScreenActivity extends Activity implements LoginScreenView {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    String name1;
    EditText name;
    Button login_button;
    EditText mobile;
    String mobile1;

    @BindView(R.id.back_button)
    ImageView back_button;

    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;

//
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
	public static LoginScreenActivity loginScreenActivity;

    private ProgressBar progressbar;
    private LoginScreenPresenter loginScreenPresenter;

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
		loginScreenActivity=this;
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Glide.with(this).load(R.drawable.login_background).asBitmap().into(
                new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource,
                                                GlideAnimation<? super Bitmap> glideAnimation) {
                        Drawable drawable = new BitmapDrawable(resource);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            relativeLayout.setBackground(drawable);
                        }
                    }
                });


        progressbar = (ProgressBar) findViewById(R.id.progressBar);

        login_button = (Button) findViewById(R.id.button);
        name = (EditText) findViewById(R.id.editText);
        mobile = (EditText) findViewById(R.id.editText2);

        ButterKnife.bind(this);

        loginScreenPresenter = new LoginScreenPresenterImpl(this,
                new RetrofitLoginScreenProvider());

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name1 = name.getText().toString();
                mobile1 = mobile.getText().toString();
                if (name1.equals("") || name1.equals(null)) {
                    name.setError("Please fill name");
                    name.requestFocus();
                } else if (mobile1.equals("") || mobile1.equals(null)) {
                    mobile.setError("Please fill mobile");
                    mobile.requestFocus();

                } else if (mobile1.length() != 10) {
                    mobile.setError("Invalid Mobile No.");
                    mobile.requestFocus();
                }
                if ((name1.equals("") || name1.equals(null)) ||
                    ((mobile1.equals("") || mobile1.equals(null)) || mobile1.length() != 10))
                {


                } else {
                    login_button.setEnabled(false);
                    login_button.setClickable(false);
                    loginScreenPresenter.requestLogin(name1, mobile1);
                }


            }
        });


    }

    @Override
    public void showLoading(boolean show) {
        if (show) {
            progressbar.setVisibility(View.VISIBLE);
            login_button.setEnabled(true);
        } else {
            progressbar.setVisibility(View.INVISIBLE);
            login_button.setEnabled(true);
        }
    }

    @Override
    public void showMessage(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLoginVerified() {
        login_button.setEnabled(true);
        login_button.setClickable(true);

        Intent in = new Intent(LoginScreenActivity.this, OtpViewImpl.class);
        in.putExtra("mobile", mobile1);
        startActivity(in);
    }

    @Override
    public void onLoginFailed() {
        login_button.setEnabled(true);
        login_button.setClickable(true);
    }

    @Override
    public void onBackPressed() {
        /*Intent intent = new Intent(LoginScreenActivity.this, WelcomeScreenActivity.class);
        startActivity(intent);
        finish();
*/	super.onBackPressed();
    }
}