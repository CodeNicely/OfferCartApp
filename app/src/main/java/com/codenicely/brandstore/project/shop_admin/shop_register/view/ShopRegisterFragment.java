package com.codenicely.brandstore.project.shop_admin.shop_register.view;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.city.data.CityDetails;
import com.codenicely.brandstore.project.shop_admin.shop_otp.view.ShopOtpFragment;
import com.codenicely.brandstore.project.shop_admin.shop_register.data.CategoryData;
import com.codenicely.brandstore.project.shop_admin.shop_register.data.ShopPreRegistrationData;
import com.codenicely.brandstore.project.shop_admin.shop_register.data.StateData;
import com.codenicely.brandstore.project.shop_admin.shop_register.presenter.ShopRegisterPresenter;
import com.codenicely.brandstore.project.shop_admin.shop_register.presenter.ShopRegisterPresenterImpl;
import com.codenicely.brandstore.project.shop_admin.shop_register.providers.RetrofitShopRegisterHelper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShopRegisterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShopRegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class ShopRegisterFragment extends Fragment implements ShopRegisterView, LocationListener
        , GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private StateData stateData;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // LogCat tag
    private static final String TAG = "ShopRegisterFragment";
    private static final String SELECT_CITY = "Select City";
    private static final String SELECT_STATE = "Select State";
    private static final String SELECT_CATEGORY = "Select Category";
    //Define a request code to send to Google Play services
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private double latitude;
    private double longitude;
    private boolean LOCATION_REQUEST = false;
    private boolean CAMERA_REQUEST = false;
    private boolean GALLERY_REQUEST = false;
    private static final int CAMERA_REQUEST_ID = 100;
    private final int GALLERY_REQUEST_ID = 1;
    private ShopRegisterPresenter shopRegisterPresenter;
    private File image = null;

    HashMap<String, Integer> hashMap;
    int flag;
    private Context context;

    @BindView(R.id.galleryButton)
    Button galleryButton;

    @BindView(R.id.cameraButton)
    Button cameraButton;

    @BindView(R.id.registerShop)
    Button registerShop;

    @BindView(R.id.name)
    EditText name_edittext;

    @BindView(R.id.mobile)
    EditText mobile_edittext;

    @BindView(R.id.password)
    EditText password_edittext;

    @BindView(R.id.confirm_password)
    EditText confirm_password_edittext;

    @BindView(R.id.description)
    EditText description_edittext;

    @BindView(R.id.address)
    EditText address_edittext;

    @BindView(R.id.category_spinner)
    Spinner category_spinner;

    @BindView(R.id.city_spinner)
    Spinner city_spinner;

    @BindView(R.id.state_spinner)
    Spinner state_spinner;

    @BindView(R.id.imageView)
    ImageView imageView;
    /*
    @BindView(R.id.cardView)
    CardView cardView;*/

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.latitude_longitude)
    TextView latitudeLongitude;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    String name, password;
    String confirm_password;
    String description;
    String address;
    String city;
    String category;
    private Bitmap bitmap;
    private Uri imageUri = null;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    static final int LOCATION_SETTINGS_REQUEST = 1;

    String state = "Select State";
    private OnFragmentInteractionListener mListener;
    private ArrayAdapter<String> state_array_adapter;
    private ArrayAdapter<String> category_array_adapter;
    private ArrayAdapter<String> city_array_adapter;

    private ProgressDialog progressDialog;
    String mobile;
    private boolean gps_enabled;
    private boolean network_enabled;

    public ShopRegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShopRegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public ShopRegisterFragment newInstance(String param1, String param2) {
        ShopRegisterFragment fragment = new ShopRegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_register_shop, container, false);
        hashMap = new HashMap<String, Integer>();
        flag++;
        context = getContext();
        ButterKnife.bind(this, view);
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait . . .");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);

        state_array_adapter = new ArrayAdapter<>(context, R.layout.spinner_item);
        category_array_adapter = new ArrayAdapter<>(context, R.layout.spinner_item);
        city_array_adapter = new ArrayAdapter<>(context, R.layout.spinner_item);

        city_array_adapter.add(SELECT_CITY);
        category_array_adapter.add(SELECT_CATEGORY);
        state_array_adapter.add(SELECT_STATE);

        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                // The next two lines tell the new client that “this” current class will handle connection stuff
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                //fourth line adds the LocationServices API endpoint from GooglePlayServices
                .addApi(LocationServices.API)
                .build();

        // Create the LocationRequest object
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds

        mGoogleApiClient.connect();


        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

		try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
		} catch (Exception ex) {
		}
		try {
			network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		} catch (Exception ex) {
		}
		if (!gps_enabled ) {
		//	Toast.makeText(getContext(),"True",Toast.LENGTH_SHORT).show();

			AlertDialog.Builder dialog = new AlertDialog.Builder(getContext(), R.style.AlertDialogTheme);
			dialog.setCancelable(false);
			dialog.setTitle("Location Permission Required");
			dialog.setMessage(getResources().getString(R.string.gps_network_not_enabled));
			dialog.setPositiveButton(getResources().getString(R.string.open_location_settings), new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface paramDialogInterface, int paramInt) {
					Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);


					startActivityForResult(myIntent, LOCATION_SETTINGS_REQUEST);
				}
			});
			dialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface paramDialogInterface, int paramInt) {
					// TODO Auto-generated method stub
					getActivity().finish();
				}
			});
			dialog.show();
		} else {
//			Toast.makeText(getContext(),"False",Toast.LENGTH_SHORT).show();

//			if (receiver == null) {
//				IntentFilter filter = new IntentFilter("Hello World");
//				receiver = new BroadcastReceiver() {
//					@Override
//					public void onReceive(Context context, Intent intent) {
//						if (intent.getAction().contentEquals("Hello World")) {
//							lat = intent.getDoubleExtra("lat", 0.0);
//							lng = intent.getDoubleExtra("lng", 0.0);
//							world.lat = lat;
//							world.lng = lng;
//
//							profile.lat = lat;
//							profile.lng = lng;
//
//							if (needSomethingTweet || needSomethingWorld) {
//								needSomethingWorld = false;
//								needSomethingTweet = false;
//								getAllPosts(count);
//							}
//						}
//					}
//				};
//				registerReceiver(receiver, filter);
//			}

}

        city_spinner.setAdapter(city_array_adapter);
        state_spinner.setAdapter(state_array_adapter);
        category_spinner.setAdapter(category_array_adapter);

        state_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (flag > 1) {
                    int error = 0;
                    int state_id = 0;
                    state = state_spinner.getSelectedItem().toString();
                    //Toast.makeText(getContext(),state,Toast.LENGTH_SHORT).show();
                    try {
                        state_id = hashMap.get(state);
                    } catch (Exception e) {
                        error = 1;
                    }
                    if (error == 0) {
                        city_array_adapter.clear();
                        city_array_adapter.add(SELECT_CITY);
                        shopRegisterPresenter.requestCityList(state_id);
                    }

                }
                flag++;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Dexter.initialize(context);
        if (checkPermissionForLocation()) {

        } else {
            if (requestLocationPermission()) {

            }

        }
        shopRegisterPresenter = new ShopRegisterPresenterImpl(this, new RetrofitShopRegisterHelper(context));
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        /*        Intent intent = new Intent();
                intent.setType("image*//*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
*/
                shopRegisterPresenter.openGallery();

            }
        });
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopRegisterPresenter.openCamera();

            }
        });

        //getLocation();
        registerShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = name_edittext.getText().toString();
                mobile = mobile_edittext.getText().toString();
                password = password_edittext.getText().toString();
                confirm_password = confirm_password_edittext.getText().toString();
                description = description_edittext.getText().toString();
                address = address_edittext.getText().toString();
                city = city_spinner.getSelectedItem().toString();
                category = category_spinner.getSelectedItem().toString();

                if (name.equals("") || name.equals(null)) {
                    name_edittext.setError("Please enter Name");
                    name_edittext.requestFocus();
                } else if (mobile.equals("") || mobile.equals(null)) {
                    mobile_edittext.setError("Please enter mobile");
                    mobile_edittext.requestFocus();
                } else if (mobile.length() != 10) {
                    mobile_edittext.setError("Mobile number length should be 10");
                    mobile_edittext.requestFocus();
                } else if (password.equals("") || password.equals(null)) {
                    password_edittext.setError("Please enter a password");
                    password_edittext.requestFocus();
                } else if (password.length() < 6) {
                    password_edittext.setError("Password should be of min length 6");
                    password_edittext.requestFocus();
                } else if (!confirm_password.contentEquals(password)) {
                    confirm_password_edittext.setError("Passwords don't match");
                    confirm_password_edittext.requestFocus();
                }/* else if (description.equals("") || description.equals(null)) {
                    description_edittext.setError("Please enter shop description");
                    description_edittext.requestFocus();
                } */ else if (address.equals("") || address.equals(null)) {
                    address_edittext.setError("Please enter shop address");
                    address_edittext.requestFocus();
                } else if (category.equals(SELECT_CATEGORY)) {
                    Toast.makeText(context, "Please select a category",
                            Toast.LENGTH_SHORT).show();
                    category_spinner.requestFocus();
                } else if (city.equals(SELECT_CITY)) {
                    Toast.makeText(context, "Please select a city",
                            Toast.LENGTH_SHORT).show();
                    city_spinner.requestFocus();
                } else if (imageUri == null) {
                    Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "You've not selected any image to upload.", Snackbar.LENGTH_LONG)
                            .setActionTextColor(Color.RED)
                            .show();
                } else {
                    final Handler handler = new Handler();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 5s = 5000ms
                            shopRegisterPresenter.registerShop(name, mobile, password, description, address,
                                    category, city, latitude, longitude, imageUri);
                        }
                    }, 500);


                }
            }
        });
        shopRegisterPresenter.requestPreRegistrationDetails();
        return view;

    }

    private boolean checkPermissionForLocation() {

        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//			Toast.makeText(getContext(),"CHeck True",Toast.LENGTH_SHORT).show();
            return true;

        } else {
            //	Toast.makeText(getContext(),"CHeck False",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean requestLocationPermission() {


        Dexter.checkPermission(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {


                LOCATION_REQUEST = true;
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {


                LOCATION_REQUEST = false;
            }

			@Override
			public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

			}


		}, Manifest.permission.ACCESS_FINE_LOCATION);

//		Toast.makeText(getContext(),"REquest True",Toast.LENGTH_SHORT).show();

        return LOCATION_REQUEST;

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST_ID && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            if (imageUri != null) {

                Glide.with(this).load(imageUri).fitCenter().crossFade().into(imageView);

            }

        } else if (requestCode == CAMERA_REQUEST_ID && resultCode == RESULT_OK) {
            imageUri = Uri.fromFile(image);

            if (imageUri != null) {
                Glide.with(this).load(imageUri).fitCenter().crossFade().into(imageView);
            }
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v(this.getClass().getSimpleName(), "onPause()");

        //Disconnect from API onPause()
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

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
            //cardView.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            // cardView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showDialogLoader(boolean show) {
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
    public void onCitiesRecieved(List<CityDetails> cityDatalist) {
        for (int i = 0; i < cityDatalist.size(); i++) {
            CityDetails cityData = cityDatalist.get(i);
            city_array_adapter.add(cityData.getCity_name());
        }
        city_array_adapter.notifyDataSetChanged();
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void showGallery() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST_ID);


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

        } else {
            //If everything went fine lets get latitude and longitude
            latitude = location.getLatitude();
            longitude = location.getLongitude();

            //	Toast.makeText(getContext(),String.valueOf(latitude),Toast.LENGTH_SHORT).show();

            latitudeLongitude.setText("Current Location - " + String.valueOf(latitude)
                    + " , " + longitude);

        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();


        latitudeLongitude.setText("Current Location - " + String.valueOf(latitude)
                + " , " + longitude);

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            /*
             * Google Play services can resolve some errors it detects.
             * If the error has a resolution, try sending an Intent to
             * start a Google Play services activity that can resolve
             * error.
             */
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(getActivity(), CONNECTION_FAILURE_RESOLUTION_REQUEST);
                    /*
                     * Thrown if Google Play services canceled the original
                     * PendingIntent
                     */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
                /*
                 * If no resolution is available, display a dialog to the
                 * user with the error.
                 */
            Log.e("Error", "Location services connection failed with code " + connectionResult.getErrorCode());
        }

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


        for (int i = 0; i < shopPreRegistrationData.getState_list().size(); i++) {
            stateData = shopPreRegistrationData.getState_list().get(i);
            state_array_adapter.add(stateData.getName());
            hashMap.put(stateData.getName(), stateData.getId());
        }

        for (int i = 0; i < shopPreRegistrationData.getCategory_list().size(); i++) {
            CategoryData categoryData = shopPreRegistrationData.getCategory_list().get(i);
            category_array_adapter.add(categoryData.getName());
        }

        state_array_adapter.notifyDataSetChanged();
        category_array_adapter.notifyDataSetChanged();

    }

    @Override
    public void onRegistrationSuccess() {

        Fragment fragment = new ShopOtpFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Bundle args = new Bundle();
        args.putString("mobile", mobile);
        fragment.setArguments(args);
        ft.replace(R.id.container_body, fragment);
        ft.commit();
        // ((ShopActivity) getActivity()).addFragment(new ShopOtpFragment());
    }
}
