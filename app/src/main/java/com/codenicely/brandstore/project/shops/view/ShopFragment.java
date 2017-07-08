package com.codenicely.brandstore.project.shops.view;

import android.Manifest;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.helper.Keys;
import com.codenicely.brandstore.project.helper.MyApplication;
import com.codenicely.brandstore.project.helper.SharedPrefs;
import com.codenicely.brandstore.project.shops.model.RetrofitShopProvider;
import com.codenicely.brandstore.project.shops.model.data.ShopData;
import com.codenicely.brandstore.project.shops.presenter.ShopPresenter;
import com.codenicely.brandstore.project.shops.presenter.ShopPresenterImpl;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShopFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopFragment extends Fragment implements ShopView,LocationListener
          , GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.shops_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.shop_progressbar)
    ProgressBar progressBar;

	@BindView(R.id.tv_no_shop)
	TextView tv_no_shop;
 /*
	@BindView(R.id.latitude_longitude)
	TextView latitudeLongitude;
*/

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private ShopPresenter shopPresenter;
    private ShopAdapter shopAdapter;
    private LinearLayoutManager linearLayoutManager;
    private SharedPrefs sharedPrefs;

	private boolean LOCATION_REQUEST = false;
	private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private double latitude;
    private double longitude;
    private boolean gps_enabled;
    private boolean network_enabled;
	static final int LOCATION_SETTINGS_REQUEST = 1;
	int category_id;
    public ShopFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShopFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShopFragment newInstance(String param1, String param2) {
        ShopFragment fragment = new ShopFragment();
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

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        ButterKnife.bind(this, view);
        /*AdView adView = (AdView)view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);*/

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
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

        /*try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }
        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }
        if (!gps_enabled) {
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

		*//*	if (receiver == null) {
				IntentFilter filter = new IntentFilter("Hello World");
				receiver = new BroadcastReceiver() {
					@Override
					public void onReceive(Context context, Intent intent) {
						if (intent.getAction().contentEquals("Hello World")) {
							lat = intent.getDoubleExtra("lat", 0.0);
							lng = intent.getDoubleExtra("lng", 0.0);
							world.lat = lat;
							world.lng = lng;

							profile.lat = lat;
							profile.lng = lng;

							if (needSomethingTweet || needSomethingWorld) {
								needSomethingWorld = false;
								needSomethingTweet = false;
								getAllPosts(count);
							}
						}
					}
				};
				registerReceiver(receiver, filter);
			}
		*//*
        }
		*/

		Dexter.initialize(getContext());
		if (checkPermissionForLocation()){

		}else {
			if (requestLocationPermission()){

			}

		}


		Bundle bundle=this.getArguments();
        category_id=bundle.getInt(Keys.KEY_CATEGORY_ID);

        sharedPrefs = new SharedPrefs(getContext());
        initialize();
        Log.d("fcm", MyApplication.getFcm());

		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				// Do something after 5s = 5000ms
				shopPresenter.getShops(sharedPrefs.getAccessToken(), category_id ,latitude,longitude);
			}
		}, 500);

        return view;
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

	private boolean checkPermissionForLocation() {
		if (ContextCompat.checkSelfPermission(getContext(),
				Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ){
//			Toast.makeText(getContext(),"CHeck True",Toast.LENGTH_SHORT).show();
			return true;

		}
		else{
		//	Toast.makeText(getContext(),"CHeck False",Toast.LENGTH_SHORT).show();
			return false;
		}

	}

	void initialize() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        shopAdapter = new ShopAdapter(getContext());
        recyclerView.setHasFixedSize(true);
        shopPresenter = new ShopPresenterImpl(this, new RetrofitShopProvider());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(shopAdapter);

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

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showLoading(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void OnShopsDataReceived(List<ShopData> shopDataList) {
    	if (shopDataList.size()==0){
			tv_no_shop.setVisibility(View.VISIBLE);
		}else {
			tv_no_shop.setVisibility(View.GONE);
			shopAdapter.setData(shopDataList);
			shopAdapter.notifyDataSetChanged();
		}


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

         //   Toast.makeText(getContext(),String.valueOf(latitude),Toast.LENGTH_SHORT).show();

            //  latitudeLongitude.setText("Current Location - " + String.valueOf(latitude) + " , " + longitude);

        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();


//        latitudeLongitude.setText("Current Location - " + String.valueOf(latitude)

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
