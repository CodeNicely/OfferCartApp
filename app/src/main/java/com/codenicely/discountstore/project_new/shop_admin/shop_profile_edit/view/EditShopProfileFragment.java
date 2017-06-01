package com.codenicely.discountstore.project_new.shop_admin.shop_profile_edit.view;

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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.codenicely.discountstore.project_new.R;
import com.codenicely.discountstore.project_new.helper.SharedPrefs;
import com.codenicely.discountstore.project_new.helper.image_loader.GlideImageLoader;
import com.codenicely.discountstore.project_new.helper.image_loader.ImageLoader;
import com.codenicely.discountstore.project_new.helper.utils.BitmapUtils;
import com.codenicely.discountstore.project_new.helper.utils.UriUtils;
import com.codenicely.discountstore.project_new.offer.view.OfferFragment;
import com.codenicely.discountstore.project_new.shop_admin.shop_home.ShopHomePage;
import com.codenicely.discountstore.project_new.shop_admin.shop_offerlist.view.ShopOfferListFragment;
import com.codenicely.discountstore.project_new.shop_admin.shop_profile_edit.model.RetrofitEditShopProfileHelper;
import com.codenicely.discountstore.project_new.shop_admin.shop_profile_edit.presenter.EditShopProfilePresenter;
import com.codenicely.discountstore.project_new.shop_admin.shop_profile_edit.presenter.EditShopProfilePresenterImpl;
import com.codenicely.discountstore.project_new.shop_admin.shop_profile_show.view.ShowShopProfileFragment;
import com.codenicely.discountstore.project_new.shop_admin.shop_register.data.CategoryData;
import com.codenicely.discountstore.project_new.shop_admin.shop_register.data.CityData;
import com.codenicely.discountstore.project_new.shop_admin.shop_register.data.ShopPreRegistrationData;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditShopProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditShopProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditShopProfileFragment extends Fragment implements EditShopProfileView {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	private static final String TAG = "EditShopProfileFragment";
	private static final String SELECT_CITY = "Select City";
	private static final String SELECT_CATEGORY = "Select Category";
	private boolean CAMERA_REQUEST = false;
	private boolean GALLERY_REQUEST = false;
	private static final int CAMERA_REQUEST_ID = 100;
	private final int GALLERY_REQUEST_ID = 1;


	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;


	@BindView(R.id.galleryButton)
	Button galleryButton;

	@BindView(R.id.cameraButton)
	Button cameraButton;

	@BindView(R.id.updateShopDetails)
	Button upDatebutton;

	@BindView(R.id.name)
	EditText name_edittext;
/*

	@BindView(R.id.mobile)
	TextView mobile_textview;
*/

	@BindView(R.id.description)
	EditText description_edittext;

	@BindView(R.id.address)
	EditText address_edittext;

	@BindView(R.id.category_spinner)
	Spinner category_spinner;

	@BindView(R.id.city_spinner)
	Spinner city_spinner;

	@BindView(R.id.imageView)
	ImageView imageView;

/*
	@BindView(R.id.cardView)
	CardView cardView;
*/

	@BindView(R.id.progressBar)
	ProgressBar progressBar;

/*	@BindView(R.id.toolbar)
	Toolbar toolbar;*/

	ImageLoader imageLoader;
	private Bitmap bitmap;
	private Uri imageUri = null;
	private ArrayAdapter<String> city_array_adapter;
	private ArrayAdapter<String> category_array_adapter;
	private ProgressDialog progressDialog;
	private SharedPrefs sharedPrefs;
	Context context;
	private EditShopProfilePresenter editShopProfilePresenter;
	private File image = null;

	private OnFragmentInteractionListener mListener;
	String name,description,address,category,city,image1;

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param param1 Parameter 1.
	 * @param param2 Parameter 2.
	 * @return A new instance of fragment EditShopProfileFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static EditShopProfileFragment newInstance(String param1,String param2) {
		EditShopProfileFragment fragment = new EditShopProfileFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public EditShopProfileFragment() {
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
		View view=inflater.inflate(R.layout.fragment_edit_shop_profile, container, false);
		name = getArguments().getString("name");
		address = getArguments().getString("address");
		category = getArguments().getString("category");
		description = getArguments().getString("description");
		city = getArguments().getString("city");
		image1 = getArguments().getString("image");


		imageLoader = new GlideImageLoader(getContext());


		context = getContext();
		ButterKnife.bind(this, view);

		/*toolbar.setNavigationIcon(ContextCompat.getDrawable(context,R.drawable.ic_arrow_back_white_24dp));

		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				getActivity().onBackPressed();
			}
		});
*/
		progressDialog = new ProgressDialog(context);
		progressDialog.setMessage("Please wait . . .");
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setIndeterminate(true);
		progressDialog.setCancelable(false);
		city_array_adapter = new ArrayAdapter<>(context, R.layout.spinner_item);
		category_array_adapter = new ArrayAdapter<>(context, R.layout.spinner_item);


		city_array_adapter.add(SELECT_CITY);
		category_array_adapter.add(SELECT_CATEGORY);

		city_spinner.setAdapter(city_array_adapter);
		category_spinner.setAdapter(category_array_adapter);
		Dexter.initialize(context);
		initialise();


		editShopProfilePresenter = new EditShopProfilePresenterImpl(this, new RetrofitEditShopProfileHelper(context));

		galleryButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				editShopProfilePresenter.openGallery();

			}
		});
		cameraButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				editShopProfilePresenter.openCamera();
			}
		});

		upDatebutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String name = name_edittext.getText().toString();
				String description = description_edittext.getText().toString();
				String address = address_edittext.getText().toString();
				String city = city_spinner.getSelectedItem().toString();
				String category = category_spinner.getSelectedItem().toString();
				if (name.equals("") || name.equals(null)) {
					name_edittext.setError("Please enter Name");
					name_edittext.requestFocus();
				} else if (description.equals("") || description.equals(null)) {
					description_edittext.setError("Please enter shop description");
					description_edittext.requestFocus();
				} else if (address.equals("") || address.equals(null)) {
					description_edittext.setError("Please enter shop address");
					description_edittext.requestFocus();
				} else if (category.equals(SELECT_CATEGORY)) {
					Toast.makeText(context, "Please select a category",
							Toast.LENGTH_SHORT).show();
					category_spinner.requestFocus();
				} else if (city.equals(SELECT_CITY)) {
					Toast.makeText(context, "Please select a city",
							Toast.LENGTH_SHORT).show();
					city_spinner.requestFocus();
				} /*else if (imageUri == null) {
					Snackbar.make(getActivity().findViewById(android.R.id.content),
							"You've not selected any image to upload.", Snackbar.LENGTH_LONG)
							.setActionTextColor(Color.RED)
							.show();
				} */else {
					sharedPrefs = new SharedPrefs(getContext());
					String access_token = sharedPrefs.getKeyAccessTokenShop();

					editShopProfilePresenter.editShopProfile(access_token,name, description, address,
							category, city, imageUri);
				}
			}
		});

		editShopProfilePresenter.requestPreRegistrationDetails();

		// Inflate the layout for this fragment
		return view;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == GALLERY_REQUEST_ID && resultCode == RESULT_OK && data != null && data.getData() != null) {
			imageUri = data.getData();
			try {
				//Getting the Bitmap from Gallery
				//bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
				if (imageUri != null) {
					bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageUri);
					imageView.setImageBitmap(bitmap);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (requestCode ==CAMERA_REQUEST_ID && resultCode == RESULT_OK) {

			//    imageUri=data.getData();
			imageUri=Uri.fromFile(image);
			try {
				bitmap = BitmapUtils.filePathToBitmapConverter(UriUtils.uriToFilePathConverter(context, imageUri));
				imageView.setImageBitmap(bitmap);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void initialise() {
	name_edittext.setText("Previous name");

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
		//	cardView.setVisibility(View.GONE);
		} else {
			progressBar.setVisibility(View.GONE);
		//	cardView.setVisibility(View.VISIBLE);
		}

	}

	@Override
	public void showDialogLoader(boolean show){
		if (show) {
			progressDialog.show();
		} else {
			progressDialog.hide();
		}
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
	public void setPreRegistrationData(ShopPreRegistrationData shopPreRegistrationData) {

		int index_of_category = 0;
		int index_of_city = 0;

		for (int i = 0; i < shopPreRegistrationData.getCity_list().size(); i++) {
			CityData cityData = shopPreRegistrationData.getCity_list().get(i);
			index_of_city =cityData.getName().indexOf(city);
			city_array_adapter.add(cityData.getName());
		}


		for (int i = 0; i < shopPreRegistrationData.getCategory_list().size(); i++) {
			CategoryData categoryData = shopPreRegistrationData.getCategory_list().get(i);
			index_of_category =categoryData.getName().indexOf(category);
			category_array_adapter.add(categoryData.getName());
		}
		city_array_adapter.notifyDataSetChanged();
		category_array_adapter.notifyDataSetChanged();

		name_edittext.setText(name);
		address_edittext.setText(address);
		description_edittext.setText(description);
		category_spinner.setSelection(index_of_category);
		city_spinner.setSelection(index_of_city);
		imageUri= Uri.parse(image1);
		imageLoader.loadImage(image1, imageView, progressBar);

	}

	@Override
	public void onEditSuccess() {
		ShopOfferListFragment shopOfferListFragment =new ShopOfferListFragment();
		((ShopHomePage)getActivity()).setFragment(shopOfferListFragment,"Home");

		ShowShopProfileFragment showShopProfileFragment=new ShowShopProfileFragment();
		((ShopHomePage)getActivity()).addFragment(showShopProfileFragment,"Home");
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
