package com.codenicely.discountstore.project.offer.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.codenicely.discountstore.project.R;
import com.codenicely.discountstore.project.helper.Keys;
import com.codenicely.discountstore.project.helper.SharedPrefs;
import com.codenicely.discountstore.project.helper.image_loader.GlideImageLoader;
import com.codenicely.discountstore.project.helper.image_loader.ImageLoader;
import com.codenicely.discountstore.project.offer.model.RetrofitBuyOfferProvider;
import com.codenicely.discountstore.project.offer.model.RetrofitOfferScreenDetailsProvider;
import com.codenicely.discountstore.project.offer.model.data.OfferData;
import com.codenicely.discountstore.project.offer.model.data.OfferScreenDetails;
import com.codenicely.discountstore.project.offer.model.data.OfferScreenList;
import com.codenicely.discountstore.project.offer.presenter.BuyOfferPresenter;
import com.codenicely.discountstore.project.offer.presenter.OfferScreenDetailsPresenter;
import com.codenicely.discountstore.project.offer.presenter.OfferScreenDetailsPresenterImpl;
import com.codenicely.discountstore.project.offer.presenter.BuyOfferPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OfferFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OfferFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OfferFragment extends Fragment implements OfferScreenView, BuyOfferView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String access_token;
    @BindView(R.id.offersRecycler)
    RecyclerView recyclerView;
    @BindView(R.id.offersProgressBar)
    ProgressBar progressBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imageView)
    ImageView shopImage;

    @BindView(R.id.scrollView)
    ScrollView scrollView;

    @BindView(R.id.shop_name)
    TextView shopName;

    @BindView(R.id.shop_description)
    TextView shopDescription;

    @BindView(R.id.shop_address)
    TextView shopAddress;

    @BindView(R.id.imageProgressBar)
    ProgressBar imageProgressBar;

    @BindView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    private ImageLoader imageLoader;

    private ProgressDialog progressDialog;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OfferScreenAdapter offerScreenAdapter;
    private OfferScreenDetailsPresenter offerScreenDetailsPresenter;
    private LinearLayoutManager linearLayoutManager;
    private SharedPrefs sharedPrefs;
    private OnFragmentInteractionListener mListener;
    private BuyOfferPresenter buyOfferPresenter;
    public OfferFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OfferFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OfferFragment newInstance(String param1, String param2) {
        OfferFragment fragment = new OfferFragment();
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
        View view = inflater.inflate(R.layout.fragment_offers, container, false);
        ButterKnife.bind(this, view);

        imageLoader=new GlideImageLoader(getContext());

        initialize();
        buyOfferPresenter=new BuyOfferPresenterImpl(this,new RetrofitBuyOfferProvider());
        toolbar.setTitle("Offers");
        toolbar.setNavigationIcon(ContextCompat.getDrawable(getContext(), R.drawable.ic_arrow_back_white_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        Bundle bundle=this.getArguments();
        int shop_id=bundle.getInt(Keys.KEY_SHOP_ID);
        offerScreenDetailsPresenter.requestOfferList(sharedPrefs.getAccessToken(), shop_id);
        return view;
    }

    void initialize() {
        sharedPrefs = new SharedPrefs(getContext());
        access_token = sharedPrefs.getAccessToken();
        linearLayoutManager = new LinearLayoutManager(getContext());
        offerScreenAdapter = new OfferScreenAdapter(getContext(), this);
        recyclerView.setHasFixedSize(true);
        offerScreenDetailsPresenter = new OfferScreenDetailsPresenterImpl(this, new RetrofitOfferScreenDetailsProvider());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(offerScreenAdapter);
    //    recyclerView.setNestedScrollingEnabled(true);
      //  scrollView.setNestedScrollingEnabled(true);
        progressDialog = new ProgressDialog(getContext());

        progressDialog.setMessage("Buying Offer");
        progressDialog.setTitle("Connecting . . .");

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
    public void showMessage(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }

    }

    @Override
    public void onOfferReceived(OfferScreenList offerScreenList) {

        setCollapsingToolbarLayoutTitle(offerScreenList.getShop_name()+" - Offers");
     //   toolbar.setTitle(offerScreenList.getShop_name()+" - Offers ");
        imageLoader.loadImage(offerScreenList.getShop_image(),shopImage,imageProgressBar);
        shopName.setText(offerScreenList.getShop_name());
        shopDescription.setText(offerScreenList.getShop_description());
        shopAddress.setText(offerScreenList.getShop_address());

        offerScreenAdapter.setdata(offerScreenList.getOffer_list());
        offerScreenAdapter.notifyDataSetChanged();
    }

    @Override
    public void onOfferSelected(OfferScreenDetails offerScreenDetails) {
    }

    @Override
    public void onOfferSent() {

    }

    @Override
    public void showSnackMessage(String message) {


    }

    @Override
    public void showLoadingDialog(boolean show) {

        if (show) {
            progressDialog.show();
        } else {
            progressDialog.hide();
        }


    }

    @Override
    public void onOfferBuy(OfferData buyOfferData) {
        String status;
        if(buyOfferData.isSuccess()){
            status="Successful";
        }else {
            status = "Failed";
        }

        final AlertDialog ad = new AlertDialog.Builder(getActivity())
                .create();
        ad.setIcon(R.drawable.discount_store_logo);

        ad.setCancelable(false);
        ad.setTitle("Buying Offer "+ status);
        ad.setMessage(buyOfferData.getMessage()+"\n\n"+"You can checkout your order status in My Orders Section");
        ad.setCancelable(false);
        ad.setButton(DialogInterface.BUTTON_POSITIVE, "Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              //  ((HomePage)getActivity()).addFragment);
                ad.cancel();
            }
        });
        ad.show();

    }

    public void buyOffer(final int offer_id, int offer_price) {

        final AlertDialog ad = new AlertDialog.Builder(getActivity())
                .create();
        ad.setCancelable(false);
        ad.setIcon(R.drawable.discount_store_logo);
        ad.setTitle("Do you really want to buy this offer ??");
        ad.setMessage("This offer cost Rs. "+offer_price+" \nThis amount will be deducted from your account wallet!");
        ad.setCancelable(false);
        ad.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                buyOfferPresenter.buyOffer(offer_id,sharedPrefs.getAccessToken());

                ad.cancel();
            }
        });
        ad.setButton(DialogInterface.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ad.cancel();
            }
        });
        ad.show();

    }

    private void setCollapsingToolbarLayoutTitle(String title) {
        collapsingToolbarLayout.setTitle(title);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBarPlus1);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBarPlus1);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
