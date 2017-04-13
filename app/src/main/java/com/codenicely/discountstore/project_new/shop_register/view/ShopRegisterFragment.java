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
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.codenicely.discountstore.project_new.R;
import com.codenicely.discountstore.project_new.helper.utils.BitmapUtils;
import com.codenicely.discountstore.project_new.helper.utils.UriUtils;
import com.codenicely.discountstore.project_new.shop_register.model.RetrofitShopRegisterHelper;
import com.codenicely.discountstore.project_new.shop_register.presenter.ShopRegisterPresenter;
import com.codenicely.discountstore.project_new.shop_register.presenter.ShopRegisterPresenterImpl;
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
    private boolean CAMERA_REQUEST = false;
    private boolean GALLERY_REQUEST = false;
    private static final int CAMERA_REQUEST_ID = 100;
    private final int GALLERY_REQUEST_ID = 1;
    private ShopRegisterPresenter shopRegisterPresenter;
    private File image = null;

    private ProgressDialog progressDialog;

    private Context context;

    @BindView(R.id.description)
    EditText descriptionEditText;

    @BindView(R.id.galleryButton)
    Button galleryButton;

    @BindView(R.id.cameraButton)
    Button cameraButton;

    @BindView(R.id.registerShop)
    Button submitButton;

    @BindView(R.id.name)
    EditText nameEditText;

    @BindView(R.id.email)
    EditText emailEditText;

    @BindView(R.id.mobile)
    EditText mobileEditText;

    @BindView(R.id.location)
    EditText locationEditText;

    @BindView(R.id.imageView)
    ImageView imageView;

    private Bitmap bitmap;
    private Uri imageUri = null;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

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
    public static ShopRegisterFragment newInstance(String param1, String param2) {
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
        View view = inflater.inflate(R.layout.fragment_upload_spot, container, false);

        context = getContext();
        ButterKnife.bind(this, view);
        Dexter.initialize(context);
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Submitting . . .");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
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

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameEditText.getText().toString();
                String mobile = mobileEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String location = locationEditText.getText().toString();
                String description = descriptionEditText.getText().toString();

                if (name.equals("") || name.equals(null)) {
                    nameEditText.setError("Please enter Name");
                    nameEditText.requestFocus();
                } else if (mobile.equals("") || mobile.equals(null)) {
                    mobileEditText.setError("Please enter mobile");
                    mobileEditText.requestFocus();
                } else if (email.equals("") || email.equals(null)) {
                    emailEditText.setError("Please enter Email");
                    emailEditText.requestFocus();
                } else if (location.equals("") || location.equals(null)) {
                    locationEditText.setError("Please enter location");
                    locationEditText.requestFocus();
                } else if (description.equals("") || description.equals(null)) {
                    descriptionEditText.setError("Please describe the location");
                    descriptionEditText.requestFocus();
                } else if (imageUri == null) {
                    Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "You've not selected any image to upload.", Snackbar.LENGTH_LONG)
                            //     .setAction("Undo", mOnClickListener)
                            .setActionTextColor(Color.RED)
                            .show();
                } else {
                    shopRegisterPresenter.uploadSpot(nameEditText.getText().toString(),
                            mobileEditText.getText().toString(), emailEditText.getText().toString(),
                            locationEditText.getText().toString(), descriptionEditText.getText().toString(), imageUri);

                }
            }
        });

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == GALLERY_REQUEST_ID && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                //Getting the Bitmap from Gallery
                // bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                if (imageUri != null) {
                    bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageUri);
                    imageView.setImageBitmap(bitmap);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (requestCode == CAMERA_REQUEST_ID && resultCode == RESULT_OK) {

            //    imageUri=data.getData();
            imageUri = Uri.fromFile(image);
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

        if (show)
            progressDialog.show();
        else
            progressDialog.hide();
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
}
