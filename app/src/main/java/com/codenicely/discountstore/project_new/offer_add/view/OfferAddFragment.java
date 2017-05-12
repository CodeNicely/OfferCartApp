package com.codenicely.discountstore.project_new.offer_add.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.codenicely.discountstore.project_new.R;
import java.io.File;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OfferAddFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OfferAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OfferAddFragment extends Fragment implements OfferAddView, View.OnClickListener{
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	private boolean CAMERA_REQUEST = false;
	private boolean GALLERY_REQUEST = false;


	private Context context;
	private File image = null;
	private Bitmap bitmap;
	private Uri imageUri = null;

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	@BindView(R.id.name)
	EditText name;
	@BindView(R.id.offer_validity)
	EditText validity;
	@BindView(R.id.offer_price)
	EditText price;
	@BindView(R.id.offer_description)
	EditText description;
	@BindView(R.id.offer_code)
	EditText offerCode;
	@BindView(R.id.imageView)
	ImageView imageView;
	@BindView(R.id.progressBar)
	ProgressBar progressBar;

	@BindView(R.id.galleryButton)
	Button galleryButton;

	@BindView(R.id.cameraButton)
	Button cameraButton;
	@BindView(R.id.registerOffer)
	Button registerButton;


	private OnFragmentInteractionListener mListener;

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param param1 Parameter 1.
	 * @param param2 Parameter 2.
	 * @return A new instance of fragment OfferAddFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static OfferAddFragment newInstance(String param1, String param2) {
		OfferAddFragment fragment = new OfferAddFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}
	public OfferAddFragment() {
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
		View view=inflater.inflate(R.layout.fragment_offer_add,container,false);
		ButterKnife.bind(this,view);

		cameraButton.setOnClickListener((View.OnClickListener) view);
		galleryButton.setOnClickListener((View.OnClickListener) view);
		registerButton.setOnClickListener((View.OnClickListener) view);
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

	}

	@Override
	public void showProgressbar(boolean show) {

	}

	@Override
	public void onOfferAdded() {

	}

	@Override
	public void onClick(View v) {

		if (v==cameraButton){

		}if (v==galleryButton){

		}
		else{
			String name = this.name.getText().toString();
			String validity = this.validity.getText().toString();
			String offer_code = this.validity.getText().toString();
			String description = this.description.getText().toString();

			if (name.equals("") || name.equals(null)) {
				this.name.setError("Please enter Offer Name");
				this.name.requestFocus();
			}
			else if (description.equals("") || description.equals(null)) {
				this.description.setError("Please enter Offer description");
				this.description.requestFocus();
			}
			else if (validity.equals("") || validity.equals(null)) {
				this.validity.setError("Please enter Offer Validity");
				this.validity.requestFocus();
			}
			else if (offer_code.equals("") || offer_code.equals(null)) {
				this.offerCode.setError("Please enter Offer Validity");
				this.offerCode.requestFocus();
			}


		}
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
