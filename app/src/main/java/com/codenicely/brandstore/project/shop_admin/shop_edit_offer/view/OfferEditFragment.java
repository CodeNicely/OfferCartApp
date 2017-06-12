package com.codenicely.brandstore.project.shop_admin.shop_edit_offer.view;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.helper.SharedPrefs;
import com.codenicely.brandstore.project.helper.image_loader.GlideImageLoader;
import com.codenicely.brandstore.project.helper.image_loader.ImageLoader;
import com.codenicely.brandstore.project.helper.utils.BitmapUtils;
import com.codenicely.brandstore.project.helper.utils.UriUtils;
import com.codenicely.brandstore.project.shop_admin.shop_edit_offer.model.RetrofitOfferEditHelper;
import com.codenicely.brandstore.project.shop_admin.shop_edit_offer.presenter.OfferEditPresenter;
import com.codenicely.brandstore.project.shop_admin.shop_edit_offer.presenter.OfferEditPresenterImpl;
import com.codenicely.brandstore.project.shop_admin.shop_home.ShopHomePage;
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
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OfferEditFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OfferEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OfferEditFragment extends Fragment implements OfferEditView, DatePickerDialog.OnDateSetListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private Context context;
    private File image = null;
    private Uri imageUri = null;
    Bitmap bitmap;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    SharedPrefs sharedPrefs;
    @BindView(R.id.name)
    EditText editTextname;

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

    @BindView(R.id.offer_expiry)
    TextView tvofferExpiry;

    @BindView(R.id.toolbar)
    Toolbar toolbar;


/*
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.cardView)
    CardView cardView;
*/


    DatePickerDialog datePickerDialog;
    int year, month, date;

    private ProgressDialog progressDialog;
    private static final String TAG = "OfferEditFragment";
    private boolean CAMERA_REQUEST = false;
    private boolean GALLERY_REQUEST = false;
    private static final int CAMERA_REQUEST_ID = 100;
    private final int GALLERY_REQUEST_ID = 1;

    String offer_id;
    OfferEditPresenter offerAddPresenter;
    private OnFragmentInteractionListener mListener;

    ImageLoader imageLoader;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OfferEditFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OfferEditFragment newInstance(String param1, String param2) {
        OfferEditFragment fragment = new OfferEditFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public OfferEditFragment() {
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
        context = getContext();
        offer_id = getArguments().getString("offer_id");
        String offer_name = getArguments().getString("offer_name");
        String offer_description = getArguments().getString("offer_description");
        String offer_image = getArguments().getString("offer_image");
        final String expiry_date = getArguments().getString("offer_expiry_date");
        Log.d("Offer Id ------", offer_id);
        Log.d("Name ------", offer_name);
        Log.d("Description ---", offer_description);
        Log.d("Image----", offer_image);
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        View view = inflater.inflate(R.layout.fragment_edit_offer, container, false);
        ButterKnife.bind(this, view);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        editTextname.setText(offer_name);
        editTextdescription.setText(offer_description);
        tvofferExpiry.setText("Offer Expiry Date");
        tvofferExpiry.append(" : " + expiry_date);

        datePickerDialog = new DatePickerDialog(
                context, R.style.DatePickerDialogTheme, OfferEditFragment.this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));

        imageLoader = new GlideImageLoader(getContext());

        imageLoader.loadImage(offer_image, imageView, progressBar);

/*
        toolbar.setNavigationIcon(ContextCompat.getDrawable(context, R.drawable.ic_arrow_back_white_24dp));

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
        tvofferExpiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        offerAddPresenter = new OfferEditPresenterImpl(this, new RetrofitOfferEditHelper(context));
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
                String description = editTextdescription.getText().toString();


                if (tvofferExpiry.getText().toString().equals("Offer Expiry Date : " + expiry_date)) {
                    Log.d("REQUIRED---", tvofferExpiry.getText().toString());
                    Log.d("PRESENTD---", "Offer Expiry Date : " + expiry_date);

                    int year0 = expiry_date.charAt(0) - 48;
                    int year1 = expiry_date.charAt(1) - 48;
                    int year2 = expiry_date.charAt(2) - 48;
                    int year3 = expiry_date.charAt(3) - 48;
                    Log.d("PRESENTD---", "Offer Expiry Date : " + year0);
                    Log.d("PRESENTD---", "Offer Expiry Date : " + year1);
                    Log.d("PRESENTD---", "Offer Expiry Date : " + year2);
                    Log.d("PRESENTD---", "Offer Expiry Date : " + year3);

                    int month0 = expiry_date.charAt(5) - 48;
                    int month1 = expiry_date.charAt(6) - 48;

                    int date0 = expiry_date.charAt(8) - 48;
                    int date1 = expiry_date.charAt(9) - 48;


                    date = date0 * 10 + date1;
                    month = month0 * 10 + month1;
                    year = year0 * 1000 + year1 * 100 + year2 * 10 + year3;
                    //	Toast.makeText(getContext(),date+"/"+month+"/"+year,Toast.LENGTH_LONG).show();
                }


                if (name.equals("") || name.equals(null)) {
                    editTextname.setError("Please enter Offer Name");
                    editTextname.requestFocus();
                } else if (description.equals("") || description.equals(null)) {
                    editTextdescription.setError("Please enter Offer description");
                    editTextdescription.requestFocus();
                } else if (imageUri == null) {
                   /* Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "You've not selected any image to upload.", Snackbar.LENGTH_LONG)
                            .setActionTextColor(Color.RED)
                            .show();
                */
                    offerAddPresenter.requestEditOfferOffer(sharedPrefs.getKeyAccessTokenShop(), offer_id,
                            name, description, date, month, year, null);


                } else {

                    offerAddPresenter.requestEditOfferOffer(sharedPrefs.getKeyAccessTokenShop(), offer_id,
                            name, description, date, month, year, imageUri);

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
            //          cardView.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
//            cardView.setVisibility(View.VISIBLE);
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

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
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
    public void onOfferEdited() {
        Intent intent = new Intent(getContext(), ShopHomePage.class);
        startActivity(intent);
        getActivity().finish();

        //Go to home page now.
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = month + 1;
        this.date = dayOfMonth;
        tvofferExpiry.setText("Offer Expiry Date");
        tvofferExpiry.append(" : " + date + "/" + this.month + "/" + this.year);

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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
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

}
