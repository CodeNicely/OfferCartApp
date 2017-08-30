package com.codenicely.brandstore.project.shop_admin.shop_login.view;



import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.helper.SharedPrefs;
import com.codenicely.brandstore.project.shop_admin.shop_activity.ShopActivity;
import com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_forgot_password.view.ShopForgotPasswordFragment;
import com.codenicely.brandstore.project.shop_admin.shop_home.ShopHomePage;
import com.codenicely.brandstore.project.shop_admin.shop_login.model.RetrofitShopLoginProvider;
import com.codenicely.brandstore.project.shop_admin.shop_login.presenter.ShopLoginPresenter;
import com.codenicely.brandstore.project.shop_admin.shop_login.presenter.ShopLoginPresenterImpl;
import com.codenicely.brandstore.project.shop_admin.shop_register.view.ShopRegisterFragment;
import com.codenicely.brandstore.project.welcome_screen.view.WelcomeScreenActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopLoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */

public class ShopLoginFragment extends Fragment implements ShopLoginView{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

	WelcomeScreenActivity welcomeScreenActivity;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
	@BindView(R.id.mobile)
	EditText editTextMobile;
	@BindView(R.id.password)
	EditText editTextPassword;
	@BindView(R.id.button)
	Button loginButton;
	@BindView(R.id.progressBar)
	ProgressBar progressBar;
	@BindView(R.id.buttonSignUp)
	Button buttonSignUp;
	@BindView(R.id.forgot_password_button)
	TextView forgotPasswordButton;
	/*

	@BindView(R.id.imageView)
	ImageView imageView;*/
	@BindView(R.id.linearlayoutLoginShop)
	RelativeLayout relativeLayout;
	/*
	@BindView(R.id.linearlayoutLoginShop)
	LinearLayout linearLayout;
	*/

	@BindView(R.id.backButton)
	ImageView backButton;

	private ShopLoginPresenter shopLoginPresenter;
	private Context context;
	String mobile,password;
	private SharedPrefs sharedPrefs;

	/**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShopLoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShopLoginFragment newInstance(String param1, String param2) {
        ShopLoginFragment fragment = new ShopLoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public ShopLoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


		if (Build.VERSION.SDK_INT < 16)//before Jelly Bean Versions
		{
			getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
		else // Jelly Bean and up
		{
			View decorView = getActivity().getWindow().getDecorView();
			// Hide the status bar.
			int ui = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
			decorView.setSystemUiVisibility(ui);

			//Hide actionbar

			//	android.app.ActionBar actionBar = getActivity().getActionBar();
		//	actionBar.hide();
		}

		context =getContext();
		View view =inflater.inflate(R.layout.fragment_shop_login, container, false);
		ButterKnife.bind(this,view);
		sharedPrefs = new SharedPrefs(getContext());
	//	Glide.with(getContext()).load(R.drawable.background_shop_login).asBitmap().override(1080, 600).into(imageView);
	//	Glide.with(this).load(R.drawable.background_shop_login).into(imageView);
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


		shopLoginPresenter=new ShopLoginPresenterImpl(this,new RetrofitShopLoginProvider());
		welcomeScreenActivity=new WelcomeScreenActivity();
		backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().onBackPressed();
			}
		});

		forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ShopForgotPasswordFragment shopForgotPasswordFragment = new ShopForgotPasswordFragment();
				((ShopActivity)getContext()).addFragment(shopForgotPasswordFragment);
			}
		});
		loginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mobile = editTextMobile.getText().toString();
				password = editTextPassword.getText().toString();
				if (mobile.equals("") || mobile.equals(null)) {
					editTextMobile.setError("Please fill mobile");
					editTextMobile.requestFocus();
				} else if (password.equals("") || password.equals(null)) {
					editTextPassword.setError("Please fill password");
					editTextPassword.requestFocus();
			}
				else {
					loginButton.setEnabled(false);
					loginButton.setClickable(false);
					shopLoginPresenter.getLoginDetails(mobile,password);
				}

			}

		});
		buttonSignUp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((ShopActivity)getActivity()).addFragment(new ShopRegisterFragment());
			}
		});
        // Inflate the layout for this fragment
        return view;
    }


	@Override
	public void showProgressbar(boolean show) {
		if (show) {
			progressBar.setVisibility(View.VISIBLE);
			loginButton.setEnabled(true);
		} else {
			progressBar.setVisibility(View.INVISIBLE);
			loginButton.setEnabled(true);
		}

	}

	@Override
	public void showMessage(String message) {
		Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onLoginVerified(String access_token) {

		Log.d("Access Token :",access_token);
		sharedPrefs.setAccessTokenShop(access_token);

		sharedPrefs.setShopLogin(true);

		loginButton.setEnabled(true);
		loginButton.setClickable(true);


		//Something to jump to next activity
		Intent intent = new Intent(context, ShopHomePage.class);
		startActivity(intent);
		getActivity().finish();
		WelcomeScreenActivity.welcomeScreenActivity.finish();
	}

	@Override
	public void onLoginFailed() {
		loginButton.setEnabled(true);
		loginButton.setClickable(true);

	}

	public interface OnFragmentInteractionListener {
		void onFragmentInteraction(Uri uri);
	}
}
