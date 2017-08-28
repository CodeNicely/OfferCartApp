package com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_forgot_password.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_forgot_password.model.RetrofitShopForgotPasswordHelper;
import com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_forgot_password.presenter.ShopForgotPasswordPresenter;
import com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_forgot_password.presenter.ShopForgotPasswordPresenterImpl;
import com.codenicely.brandstore.project.shop_admin.shop_otp.view.ShopOtpFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShopForgotPasswordFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShopForgotPasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopForgotPasswordFragment extends Fragment implements ShopForgotPasswordView{
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;
	private Context context;

	@BindView(R.id.mobile)
	EditText editTextMobile;

	@BindView(R.id.progressBar)
	ProgressBar progressBar;
	@BindView(R.id.button)
	Button sendButton;

	@BindView(R.id.toolbar)
	android.support.v7.widget.Toolbar toolbar;
	ShopForgotPasswordPresenter shopForgotPasswordPresenter;
	String mobile;
	private OnFragmentInteractionListener mListener;


	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param param1 Parameter 1.
	 * @param param2 Parameter 2.
	 * @return A new instance of fragment ShopForgotPasswordFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static ShopForgotPasswordFragment newInstance(String param1, String param2) {
		ShopForgotPasswordFragment fragment = new ShopForgotPasswordFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}	public ShopForgotPasswordFragment() {
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
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_shop_forgot_password, container, false);
		ButterKnife.bind(this, view);
		shopForgotPasswordPresenter= new ShopForgotPasswordPresenterImpl(this,new RetrofitShopForgotPasswordHelper(getContext()));
		/*backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().onBackPressed();
			}
		});

		*/
		context =getContext();

		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				getActivity().onBackPressed();
			}
		});

		sendButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mobile = editTextMobile.getText().toString();
				shopForgotPasswordPresenter.getMoblieNumber(mobile);
			}
		});
		return view;
	}

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
	public void showMessage(String message) {
		Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
	}

	@Override
	public void showProgressbar(boolean show) {
		if (show) {
			progressBar.setVisibility(View.VISIBLE);
			sendButton.setEnabled(true);
		} else {
			progressBar.setVisibility(View.INVISIBLE);
			sendButton.setEnabled(true);
		}

	}

	@Override
	public void onOTPSent() {
		Fragment fragment= new ShopOtpFragment();
		FragmentManager fm=getFragmentManager();
		FragmentTransaction ft=fm.beginTransaction();
		Bundle args = new Bundle();
		args.putBoolean("fp",true);
		args.putString("mobile",mobile);
		fragment.setArguments(args);
		ft.replace(R.id.container_body,fragment);
		ft.addToBackStack(null);
		ft.commit();
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated
	 * to the activity and potentially other fragments contained in that
	 * activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		void onFragmentInteraction(Uri uri);
	}
}
