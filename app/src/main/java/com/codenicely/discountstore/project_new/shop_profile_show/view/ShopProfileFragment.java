package com.codenicely.discountstore.project_new.shop_profile_show.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.codenicely.discountstore.project_new.R;
import com.codenicely.discountstore.project_new.helper.image_loader.GlideImageLoader;
import com.codenicely.discountstore.project_new.helper.image_loader.ImageLoader;
import com.codenicely.discountstore.project_new.shop_profile_show.data.ShowShopProfileData;
import com.codenicely.discountstore.project_new.shop_profile_show.model.RetrofitShopProfileProvider;
import com.codenicely.discountstore.project_new.shop_profile_show.presenter.ShowShopProfilePresenter;
import com.codenicely.discountstore.project_new.shop_profile_show.presenter.ShowShopProfilePresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShopProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShopProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopProfileFragment extends Fragment implements ShopProfileView {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	@BindView(R.id.shop_name)
	TextView textViewShopName;
	@BindView(R.id.shop_description)
	TextView textViewShopDescription;
	@BindView(R.id.shop_address)
	TextView textViewShopAddress;
	@BindView(R.id.phone_number)
	TextView textViewShopPhoneNo;
	@BindView(R.id.shop_category)
	TextView textViewShopCategory;
	@BindView(R.id.shop_city)
	TextView textViewShopCity;
	@BindView(R.id.shop_image)
	ImageView imageView;
	@BindView(R.id.imageProgressBar)
	ProgressBar progressBar;

	@BindView(R.id.edit_profile_btn)
	Button buttonEditProfile;

	ShowShopProfilePresenter shopProfilePresenter;
	private ImageLoader imageLoader;

	private OnFragmentInteractionListener mListener;

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param param1 Parameter 1.
	 * @param param2 Parameter 2.
	 * @return A new instance of fragment ShopProfileFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static ShopProfileFragment newInstance(String param1, String param2) {
		ShopProfileFragment fragment = new ShopProfileFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}	public ShopProfileFragment() {
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
		View v=inflater.inflate(R.layout.fragment_shop_profile,container,false);
		ButterKnife.bind(this,v);
		// Inflate the layout for this fragment
		shopProfilePresenter=new ShowShopProfilePresenterImpl(this,new RetrofitShopProfileProvider());
		imageLoader = new GlideImageLoader(getContext());
		buttonEditProfile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//something to open new fragment
			//	((ShopHomePage)getActivity()).addFragment();
			}
		});
		return v;
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
	public void onProfileRecieved(ShowShopProfileData shopProfileData) {

		progressBar.setVisibility(View.VISIBLE);

		imageLoader.loadImage(shopProfileData.getImage(), imageView, progressBar);

		textViewShopName.setText(shopProfileData.getShop_name());
		textViewShopDescription.setText(shopProfileData.getShop_description());
		textViewShopAddress.setText(shopProfileData.getShop_address());
		textViewShopPhoneNo.setText(shopProfileData.getMobile_number());
		textViewShopCategory.setText(shopProfileData.getShop_category());
		textViewShopCity.setText(shopProfileData.getCity());


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
