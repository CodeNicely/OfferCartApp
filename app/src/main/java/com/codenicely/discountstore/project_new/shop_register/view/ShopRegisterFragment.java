package com.codenicely.discountstore.project_new.shop_register.view;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
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
import android.widget.Toast;

import com.codenicely.discountstore.project_new.R;
import com.codenicely.discountstore.project_new.helper.utils.BitmapUtils;
import com.codenicely.discountstore.project_new.helper.utils.UriUtils;
import com.codenicely.discountstore.project_new.shop_otp.view.ShopOtpFragment;
import com.codenicely.discountstore.project_new.shop_register.data.CategoryData;
import com.codenicely.discountstore.project_new.shop_register.data.CityData;
import com.codenicely.discountstore.project_new.shop_register.data.ShopPreRegistrationData;
import com.codenicely.discountstore.project_new.shop_register.presenter.ShopRegisterPresenter;
import com.codenicely.discountstore.project_new.shop_register.presenter.ShopRegisterPresenterImpl;
import com.codenicely.discountstore.project_new.shop_register.providers.RetrofitShopRegisterHelper;
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
 * {@link ShopRegisterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShopRegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopRegisterFragment extends Fragment implements ShopRegisterView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "ShopRegisterFragment";
    private static final String SELECT_CITY = "Select City";
    private static final String SELECT_CATEGORY = "Select Category";
    private boolean CAMERA_REQUEST = false;
    private boolean GALLERY_REQUEST = false;
    private static final int CAMERA_REQUEST_ID = 100;
    private final int GALLERY_REQUEST_ID = 1;
    private ShopRegisterPresenter shopRegisterPresenter;
    private File image = null;


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

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.cardView)
    CardView cardView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private Bitmap bitmap;
    private Uri imageUri = null;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private ArrayAdapter<String> city_array_adapter;
    private ArrayAdapter<String> category_array_adapter;
    private ProgressDialog progressDialog;
	String mobile;

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
    public  ShopRegisterFragment newInstance(String param1, String param2) {
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

        context = getContext();
        ButterKnife.bind(this, view);
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait . . .");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);

        city_array_adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1);
        category_array_adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1);

        city_array_adapter.add(SELECT_CITY);
        category_array_adapter.add(SELECT_CATEGORY);

        city_spinner.setAdapter(city_array_adapter);
        category_spinner.setAdapter(category_array_adapter);

        Dexter.initialize(context);
        shopRegisterPresenter = new ShopRegisterPresenterImpl(this, new RetrofitShopRegisterHelper(context));

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

        registerShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = name_edittext.getText().toString();
                mobile = mobile_edittext.getText().toString();
                String password = password_edittext.getText().toString();
                String confirm_password = confirm_password_edittext.getText().toString();
                String description = description_edittext.getText().toString();
                String address = address_edittext.getText().toString();
                String city = city_spinner.getSelectedItem().toString();
                String category = category_spinner.getSelectedItem().toString();

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
                } else if (imageUri == null) {
                    Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "You've not selected any image to upload.", Snackbar.LENGTH_LONG)
                            .setActionTextColor(Color.RED)
                            .show();
                } else {

                    shopRegisterPresenter.registerShop(name, mobile, password, description, address,
                            category, city, imageUri);
                }
            }
        });
        shopRegisterPresenter.requestPreRegistrationDetails();
        return view;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode, data);

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


        for (int i = 0; i < shopPreRegistrationData.getCity_list().size(); i++) {
            CityData cityData = shopPreRegistrationData.getCity_list().get(i);
            city_array_adapter.add(cityData.getName());
        }

        for (int i = 0; i < shopPreRegistrationData.getCategory_list().size(); i++) {
            CategoryData categoryData = shopPreRegistrationData.getCategory_list().get(i);
            category_array_adapter.add(categoryData.getName());
        }
        city_array_adapter.notifyDataSetChanged();
        category_array_adapter.notifyDataSetChanged();
    }

    @Override
    public void onRegistrationSuccess() {

		Fragment fragment=new ShopOtpFragment();
		FragmentManager fm=getFragmentManager();
		FragmentTransaction ft=fm.beginTransaction();
		Bundle args = new Bundle();
		args.putString("mobile", mobile);
		fragment.setArguments(args);
		ft.replace(R.id.container_body,fragment);
		ft.commit();

       // ((ShopActivity) getActivity()).addFragment(new ShopOtpFragment());
    }
}
