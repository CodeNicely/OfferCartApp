package com.codenicely.brandstore.project.shop_admin.shop_profile_show.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.helper.SharedPrefs;
import com.codenicely.brandstore.project.helper.image_loader.GlideImageLoader;
import com.codenicely.brandstore.project.helper.image_loader.ImageLoader;
import com.codenicely.brandstore.project.shop_admin.shop_profile_edit.view.EditShopProfileFragment;
import com.codenicely.brandstore.project.shop_admin.shop_profile_show.data.ShowShopProfileData;
import com.codenicely.brandstore.project.shop_admin.shop_profile_show.model.RetrofitShopProfileProvider;
import com.codenicely.brandstore.project.shop_admin.shop_profile_show.presenter.ShowShopProfilePresenter;
import com.codenicely.brandstore.project.shop_admin.shop_profile_show.presenter.ShowShopProfilePresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShowShopProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShowShopProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowShopProfileFragment extends Fragment implements ShowShopProfileView {
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

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.imageProgressBar)
    ProgressBar progressBar1;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.edit_button)
    FloatingActionButton edit_button;


    @BindView(R.id.toolbar)
    Toolbar toolbar;


    ShowShopProfilePresenter shopProfilePresenter;
    private ImageLoader imageLoader;
    SharedPrefs sharedPrefs;
    private OnFragmentInteractionListener mListener;


    String name, description, address, category, city, image;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShowShopProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowShopProfileFragment newInstance(String param1, String param2) {
        ShowShopProfileFragment fragment = new ShowShopProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ShowShopProfileFragment() {
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
        View v = inflater.inflate(R.layout.fragment_shop_profile, container, false);
        ButterKnife.bind(this, v);

		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				getActivity().onBackPressed();
			}
		});
        progressBar.setVisibility(View.GONE);


        // Inflate the layout for this fragment
        shopProfilePresenter = new ShowShopProfilePresenterImpl(this,new RetrofitShopProfileProvider());
        imageLoader = new GlideImageLoader(getContext());
        sharedPrefs = new SharedPrefs(getContext());
        shopProfilePresenter.requestShopProfileDetails(sharedPrefs.getKeyAccessTokenShop());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        edit_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//something to open new fragment
				Fragment fragment=new EditShopProfileFragment();
				FragmentManager fm=getFragmentManager();
				FragmentTransaction ft=fm.beginTransaction();
				Bundle args = new Bundle();
				args.putString("name", name);
				args.putString("description",description);
				args.putString("address",address);
				args.putString("category",category);
				args.putString("city",city);
				args.putString("image",image);
				fragment.setArguments(args);
				ft.replace(R.id.home_layout,fragment);
				ft.addToBackStack(null);
				ft.commit();
			//	EditShopProfileFragment editShopProfileFragment=new EditShopProfileFragment();
			//	((ShopHomePage)getActivity()).addFragment(editShopProfileFragment  ,"Edit Shop Details");
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
        if (show) {
            progressBar1.setVisibility(View.VISIBLE);
        } else {
            progressBar1.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProfileRecieved(ShowShopProfileData shopProfileData) {
        name = shopProfileData.getShop_name();
        description = shopProfileData.getShop_description();
        address = shopProfileData.getShop_address();
        city = shopProfileData.getCity();
        category = shopProfileData.getShop_category();
        image = shopProfileData.getImage();
        progressBar1.setVisibility(View.VISIBLE);
        imageLoader.loadImage(shopProfileData.getImage(), imageView, progressBar1);
//        textViewShopName.setText("Name          : ");
  //      textViewShopName.append(name);
        textViewShopName.setText(name);
//        textViewShopDescription.setText("Description   : ");
  //      textViewShopDescription.append(description);
        textViewShopDescription.setText(description);
//        textViewShopAddress.setText("Address       : ");
  //      textViewShopAddress.append(address);
        textViewShopAddress.setText(address);
//        textViewShopPhoneNo.setText("Mobile number : ");
  //      textViewShopPhoneNo.append(shopProfileData.getMobile_number());
        textViewShopPhoneNo.setText(shopProfileData.getMobile_number()+"");

       // textViewShopCategory.setText("Category      : ");
       // textViewShopCategory.append(category);
        textViewShopCategory.setText(category);
        //textViewShopCity.setText("City          : ");
        textViewShopCity.setText(city);
        //textViewShopCity.append(city);
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
