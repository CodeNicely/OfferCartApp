package com.codenicely.discountstore.project_new.offer_add.view;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.codenicely.discountstore.project_new.R;
import com.codenicely.discountstore.project_new.helper.SharedPrefs;
import com.codenicely.discountstore.project_new.offer_add.model.RetrofitOfferAddHelper;
import com.codenicely.discountstore.project_new.offer_add.presenter.OfferAddPresenter;
import com.codenicely.discountstore.project_new.offer_add.presenter.OfferAddPresenterImpl;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.Date;
import java.util.List;

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
public class OfferAddFragment extends Fragment implements OfferAddView{
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";


	private Context context;
	private File image = null;
	private Uri imageUri = null;

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;
	SharedPrefs sharedPrefs;
	@BindView(R.id.name)
	EditText editTextname;

	@BindView(R.id.offer_validity)
	EditText editTextvalidity;

	@BindView(R.id.offer_price)
	EditText editTextprice;

	@BindView(R.id.offer_description)
	EditText editTextdescription;

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

	@BindView(R.id.cardView)
	CardView cardView;
	private ProgressDialog progressDialog;
	private static final String TAG = "OfferEditFragment";
	private boolean CAMERA_REQUEST = false;
	private boolean GALLERY_REQUEST = false;
	private static final int CAMERA_REQUEST_ID = 100;
	private final int GALLERY_REQUEST_ID = 1;

	OfferAddPresenter offerAddPresenter;
	//Date expiry_date;
	private OnFragmentInteractionListener mListener;

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param param1 Parameter 1.
	 * @param param2 Parameter 2.
	 * @return A new instance of fragment OfferEditFragment.
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
		/*progressDialog = new ProgressDialog(context);
		progressDialog.setMessage("Please wait . . .");
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setIndeterminate(true);
		progressDialog.setCancelable(false);
*/
		// Inflate the layout for this fragment
		context = getContext();

		View view=inflater.inflate(R.layout.fragment_offer_add,container,false);
		ButterKnife.bind(this,view);
		offerAddPresenter = new OfferAddPresenterImpl(this,new RetrofitOfferAddHelper(context));
		cameraButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				offerAddPresenter.openCamera();

			}
		});
		galleryButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				offerAddPresenter.openGallery();
			}
		});
		registerButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


				sharedPrefs = new SharedPrefs(getContext());
				String name = editTextname.getText().toString();
				String validity = editTextvalidity.getText().toString();
				String description = editTextdescription.getText().toString();
				String price =editTextprice.getText().toString();


				if (name.equals("") || name.equals(null)) {
					editTextname.setError("Please enter Offer Name");
					editTextname.requestFocus();
				}
				else if (description.equals("") || description.equals(null)) {
					editTextdescription.setError("Please enter Offer description");
					editTextdescription.requestFocus();
				}
				else if (validity.equals("") || validity.equals(null)) {
					editTextvalidity.setError("Please enter Offer Validity");
					editTextvalidity.requestFocus();
				}else if (price.equals("") || price.equals(null)) {
					editTextprice.setError("Please enter Offer Validity");
					editTextprice.requestFocus();
				}else if (imageUri == null) {
					Snackbar.make(getActivity().findViewById(android.R.id.content),
							"You've not selected any image to upload.", Snackbar.LENGTH_LONG)
							.setActionTextColor(Color.RED)
							.show();
				} else {
					offerAddPresenter.addOffer(sharedPrefs.getKeyAccessTokenShop(),
							name,description,price,validity,imageUri);
			/*
				shopRegisterPresenter.registerShop(name, mobile, password, description, address,
						category, city, imageUri);
			*/

				}

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
	public void showLoader(boolean show) {
		if (show) {
			progressBar.setVisibility(View.VISIBLE);
			cardView.setVisibility(View.GONE);
		} else {
			progressBar.setVisibility(View.GONE);
			cardView.setVisibility(View.VISIBLE);
		}

	}

	@Override
	public void showDialogLoader(boolean show) {
	/*	if (show) {
			progressDialog.show();
		} else {
			progressDialog.hide();
		}
*/
	}

	@Override
	public void showMessage(String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

	}

	@Override
	public boolean checkPermissionForCamera() {

		if (ContextCompat.checkSelfPermission(context,
				Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
					ContextCompat.checkSelfPermission(context,
							Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
			return true;
		else
			return false;

	}

	@Override
	public boolean checkPermissionForGallery() {
		if (ContextCompat.checkSelfPermission(context,
				Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
			return true;
		else
			return false;
	}

	@Override
	public boolean requestCameraPermission() {
		Dexter.checkPermissions(new MultiplePermissionsListener() {

			@Override
			public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {


				if (multiplePermissionsReport.areAllPermissionsGranted()) {

					CAMERA_REQUEST = true;
				} else {
					CAMERA_REQUEST = false;
				}
			}

			@Override
			public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

			}
		}, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE);


		return CAMERA_REQUEST;
	}

	@Override
	public boolean requestGalleryPermission() {
		Dexter.checkPermission(new PermissionListener() {
			@Override
			public void onPermissionGranted(PermissionGrantedResponse response) {/* ... */

				GALLERY_REQUEST = true;
			}

			@Override
			public void onPermissionDenied(PermissionDeniedResponse response) {/* ... */

				GALLERY_REQUEST = false;
			}

			@Override
			public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {/* ... */}
		}, Manifest.permission.READ_EXTERNAL_STORAGE);


		return GALLERY_REQUEST;
	}

	@Override
	public void showCamera() {

		Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(image));
		Log.i(TAG, image.getPath());

		if (intent.resolveActivity(context.getPackageManager()) != null) {
			// Start the image capture intent to take photo
			startActivityForResult(intent, CAMERA_REQUEST_ID);
		}


	}

	@Override
	public void showGallery() {

		Intent intent = new Intent();
		intent.setType("image/*");
		intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST_ID);

	}

	@Override
	public void fileFromPath(String filePath) {
		image = new File(filePath);
		Log.i(TAG, "fileFromPath method : " + image.getPath());

	}

	@Override
	public void showDialog(String title, String message) {
		final AlertDialog ad = new AlertDialog.Builder(getActivity())
									   .create();
		ad.setCancelable(false);
		ad.setTitle(title);
		ad.setMessage(message);
		ad.setButton(DialogInterface.BUTTON_POSITIVE, "Okay , Thanks", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				ad.cancel();
			}
		});
		ad.show();


	}

	@Override
	public void onOfferAdded() {
		//Go to home page now.
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
