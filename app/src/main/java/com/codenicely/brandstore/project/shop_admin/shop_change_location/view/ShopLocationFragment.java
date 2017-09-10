package com.codenicely.brandstore.project.shop_admin.shop_change_location.view;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.helper.SharedPrefs;
import com.codenicely.brandstore.project.shop_admin.shop_change_location.data.ShopGetLocationData;
import com.codenicely.brandstore.project.shop_admin.shop_change_location.model.RetrofitShopLocationHelper;
import com.codenicely.brandstore.project.shop_admin.shop_change_location.presenter.ShopLocationPresenter;
import com.codenicely.brandstore.project.shop_admin.shop_change_location.presenter.ShopLocationPresenterImpl;
import com.codenicely.brandstore.project.shop_admin.shop_home.ShopHomePage;
import com.codenicely.brandstore.project.shop_admin.shop_profile_show.model.RetrofitShopProfileProvider;
import com.codenicely.brandstore.project.shop_admin.shop_profile_show.presenter.ShowShopProfilePresenterImpl;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShopLocationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShopLocationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopLocationFragment extends Fragment
		implements OnMapReadyCallback,
						   GoogleApiClient.ConnectionCallbacks,
						   GoogleApiClient.OnConnectionFailedListener,
						   LocationListener, ShopLocationView {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;
	Double latitude, longitude,latitude1,longitude1;
	@BindView(R.id.progressBar)
	ProgressBar progressBar;

	@BindView(R.id.my_location_button)
	FloatingActionButton my_location_button;
	@BindView(R.id.button_location)
	Button button_location;

	@BindView(R.id.toolbar)
	Toolbar toolbar;

	LocationRequest mLocationRequest;
	GoogleApiClient mGoogleApiClient;
	ShopLocationPresenter shopLocationPresenter;
	Context context;
	private OnFragmentInteractionListener mListener;
	private SharedPrefs sharedPrefs;

	public ShopLocationFragment() {
		// Required empty public constructor
	}

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param param1 Parameter 1.
	 * @param param2 Parameter 2.
	 * @return A new instance of fragment ShopLocationFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static ShopLocationFragment newInstance(String param1, String param2) {
		ShopLocationFragment fragment = new ShopLocationFragment();
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
		View view = inflater.inflate(R.layout.fragment_shop_location, container, false);
		ButterKnife.bind(this, view);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				getActivity().onBackPressed();
			}
		});
		progressBar.setVisibility(View.GONE);

		shopLocationPresenter = new ShopLocationPresenterImpl(this, new RetrofitShopLocationHelper());
		context = getContext();
		sharedPrefs = new SharedPrefs(context);

		button_location.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				shopLocationPresenter.requestChangeLocation(sharedPrefs.getKeyAccessTokenShop(), latitude, longitude);
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

		shopLocationPresenter.requestShopLocation(sharedPrefs.getKeyAccessTokenShop());

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
//		if (context instanceof OnFragmentInteractionListener) {
//			mListener = (OnFragmentInteractionListener) context;
//		} else {
//			throw new RuntimeException(context.toString()
//											   + " must implement OnFragmentInteractionListener");
//		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
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
			latitude1 = location.getLatitude();
			longitude1 = location.getLongitude();

			//	Toast.makeText(getContext(),String.valueOf(latitude),Toast.LENGTH_SHORT).show();

//			latitudeLongitude.setText("Current Location - " + String.valueOf(latitude)
//											  + " , " + longitude);

		}
	}

	@Override
	public void onConnectionSuspended(int i) {

	}

	@Override
	public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

	}

	@Override
	public void onLocationChanged(Location location) {
		latitude1 = location.getLatitude();
		longitude1 = location.getLongitude();

	}

	@Override
	public void onMapReady(final GoogleMap googleMap) {
		LatLng latLng = new LatLng(latitude, longitude);
		googleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
			@Override
			public void onMarkerDragStart(Marker arg0) {
				// TODO Auto-generated method stub
				Log.d("System out", "onMarkerDragStart..." + arg0.getPosition().latitude + "..." + arg0.getPosition().longitude);
			}

			@SuppressWarnings("unchecked")
			@Override
			public void onMarkerDragEnd(Marker arg0) {
				// TODO Auto-generated method stub
				Log.d("System out", "onMarkerDragEnd..." + arg0.getPosition().latitude + "..." + arg0.getPosition().longitude);
				latitude = arg0.getPosition().latitude;
				longitude = arg0.getPosition().longitude;
				googleMap.animateCamera(CameraUpdateFactory.newLatLng(arg0.getPosition()));
			}

			@Override
			public void onMarkerDrag(Marker arg0) {
				// TODO Auto-generated method stub
				Log.i("System out", "onMarkerDrag...");
			}
		});

		googleMap.addMarker(new MarkerOptions().position(latLng)
									.title("Your shop Location ").draggable(true));
		googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
		if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			// TODO: Consider calling
			//    ActivityCompat#requestPermissions
			// here to request the missing permissions, and then overriding
			//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
			//                                          int[] grantResults)
			// to handle the case where the user grants the permission. See the documentation
			// for ActivityCompat#requestPermissions for more details.
			return;
		}
		googleMap.setMyLocationEnabled(true);

		googleMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
			@Override
			public boolean onMyLocationButtonClick() {
				googleMap.clear();
				latitude=latitude1;
				longitude=longitude1;
				LatLng latLng2 = new LatLng(latitude1, longitude1);
				googleMap.addMarker(new MarkerOptions().position(latLng2)
											.title("Your shop Location ").draggable(true));
				googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng2));

				return false;
			}
		});
	}

	@Override
	public void showProgressbar(boolean show) {
		if (show) {
			progressBar.setVisibility(View.VISIBLE);
		} else {
			progressBar.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	public void showMessage(String message) {
		Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onLocationRecieved(ShopGetLocationData shopGetLocationData) {
		latitude =  Double.parseDouble(shopGetLocationData.getLatitude());
		longitude = Double.parseDouble(shopGetLocationData.getLongitude());
		SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
																	  .findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);
	}

	@Override
	public void onShopLocationChanged() {
		AlertDialog.Builder builder;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Light_Dialog_Alert);
		} else {
			builder = new AlertDialog.Builder(getContext());
		}
		builder.setTitle("Shop Location Changed")
				.setMessage("Your shop location has been changed successfully")
				.setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						onDestroy();
						Intent intent = new Intent((ShopHomePage)context, ShopHomePage.class);
						startActivity(intent);
				}
		}).show();
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
