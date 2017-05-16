package com.codenicely.discountstore.project_new.shop_login.view;



import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.codenicely.discountstore.project_new.R;
import com.codenicely.discountstore.project_new.helper.SharedPrefs;
import com.codenicely.discountstore.project_new.login.presenter.LoginScreenPresenter;
import com.codenicely.discountstore.project_new.shop_activity.ShopActivity;
import com.codenicely.discountstore.project_new.shop_login.model.RetrofitShopLoginProvider;
import com.codenicely.discountstore.project_new.shop_login.presenter.ShopLoginPresenter;
import com.codenicely.discountstore.project_new.shop_login.presenter.ShopLoginPresenterImpl;
import com.codenicely.discountstore.project_new.shop_register.view.ShopRegisterFragment;
import com.payu.magicretry.MainActivity;

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

	private ShopLoginPresenter shopLoginPresenter;

	String mobile,password;

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
        View view =inflater.inflate(R.layout.fragment_shop_login, container, false);
		ButterKnife.bind(this,view);
		shopLoginPresenter=new ShopLoginPresenterImpl(this,new RetrofitShopLoginProvider());

		loginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mobile = editTextMobile.getText().toString();
				password = editTextPassword.getText().toString();
				if (mobile.equals("") || mobile.equals(null)) {
					editTextMobile.setError("Please fill name");
					editTextMobile.requestFocus();
				} else if (password.equals("") || password.equals(null)) {
					editTextPassword.setError("Please fill mobile");
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
	public void onLoginVerified() {
		//Something to jump to next activity
	}

	public interface OnFragmentInteractionListener {
		void onFragmentInteraction(Uri uri);
	}
}
