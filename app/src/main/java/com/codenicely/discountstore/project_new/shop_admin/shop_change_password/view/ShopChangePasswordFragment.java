package com.codenicely.discountstore.project_new.shop_admin.shop_change_password.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.codenicely.discountstore.project_new.R;
import com.codenicely.discountstore.project_new.helper.SharedPrefs;
import com.codenicely.discountstore.project_new.shop_admin.shop_change_password.model.RetrofitShopChangePasswordHelper;
import com.codenicely.discountstore.project_new.shop_admin.shop_change_password.presenter.ShopChangePasswordPresenter;
import com.codenicely.discountstore.project_new.shop_admin.shop_change_password.presenter.ShopChangePasswordPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShopChangePasswordFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShopChangePasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopChangePasswordFragment extends Fragment implements ShopChangePasswordView {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;


	@BindView(R.id.old_password)
	EditText old_password_edittext;
	@BindView(R.id.new_password)
	EditText new_password_edittext;
	@BindView(R.id.confirm_password)
	EditText confirm_password_edittext;
	@BindView(R.id.button)
	Button loginButton;
	@BindView(R.id.progressBar)
	ProgressBar progressBar;
	ShopChangePasswordPresenter shopChangePasswordPresenter;
	SharedPrefs sharedPrefs;
/*

	@BindView(R.id.toolbar)
	android.support.v7.widget.Toolbar toolbar;
*/

	private OnFragmentInteractionListener mListener;
	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param param1 Parameter 1.
	 * @param param2 Parameter 2.
	 * @return A new instance of fragment ShopChangePasswordFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static ShopChangePasswordFragment newInstance(String param1, String param2) {
		ShopChangePasswordFragment fragment = new ShopChangePasswordFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public ShopChangePasswordFragment() {
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
		View view=inflater.inflate(R.layout.fragment_shop_change_password, container, false);
		ButterKnife.bind(this,view);
		Context context =getContext();

		//toolbar.setNavigationIcon(ContextCompat.getDrawable(context,R.drawable.ic_arrow_back_white_24dp));

	/*	toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				getActivity().onBackPressed();
			}
		});
*/

		shopChangePasswordPresenter=new ShopChangePasswordPresenterImpl(this,new RetrofitShopChangePasswordHelper());
		sharedPrefs=new SharedPrefs(getContext());
		loginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String old_password = old_password_edittext.getText().toString();
				String new_password = new_password_edittext.getText().toString();
				String confirm_password = confirm_password_edittext.getText().toString();
				String shop_access_token = sharedPrefs.getKeyAccessTokenShop();
				if (old_password.equals("") || old_password.equals(null)) {
					old_password_edittext.setError("Please enter Name");
					old_password_edittext.requestFocus();
				}else if (new_password.equals("") || new_password.equals(null)) {
					new_password_edittext.setError("Please enter a password");
					new_password_edittext.requestFocus();
				} else if (new_password_edittext.length() < 6) {
					new_password_edittext.setError("Password should be of min length 6");
					new_password_edittext.requestFocus();
				} else if (!confirm_password.contentEquals(new_password)) {
					confirm_password_edittext.setError("Passwords don't match");
					confirm_password_edittext.requestFocus();
				}else {
					shopChangePasswordPresenter.requestChangePassword(shop_access_token,old_password,new_password);
				}
			}
		});
		// Inflate the layout for this fragment
		return view ;}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if (context instanceof OnFragmentInteractionListener) {
			mListener = (OnFragmentInteractionListener) context;
		} else {
			throw new RuntimeException(context.toString()
											   + " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	@Override
	public void showProgressbar(boolean show) {
		if (show){
			progressBar.setVisibility(View.VISIBLE);
		}else {
			progressBar.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	public void showMessage(String message) {
		Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onPasswordChanged() {
		//don't know what to put here
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated
	 * to the activity and potentially other fragments contained in that
	 * activity.
	 * <p/>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */

	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		void onFragmentInteraction(Uri uri);
	}

}
