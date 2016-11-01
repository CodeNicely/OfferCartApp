package com.example.aman.offercart_v1.offer.view;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.aman.offercart_v1.R;
import com.example.aman.offercart_v1.helper.SharedPrefs;
import com.example.aman.offercart_v1.home.HomePage;
import com.example.aman.offercart_v1.offer.model.RetrofitOfferScreenDetailsProvider;
import com.example.aman.offercart_v1.offer.model.data.OfferScreenDetails;
import com.example.aman.offercart_v1.offer.model.data.OfferScreenList;
import com.example.aman.offercart_v1.offer.presenter.OfferScreenDetailsPresenter;
import com.example.aman.offercart_v1.offer.presenter.OfferScreenDetailsPresenterImpl;
import com.example.aman.offercart_v1.shops.model.MockShopProvider;
import com.example.aman.offercart_v1.shops.presenter.ShopPresenterImpl;
import com.example.aman.offercart_v1.shops.view.ShopAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShopOfferFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShopOfferFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopOfferFragment extends Fragment implements OfferScreenView{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String shop_id;
    private OfferScreenAdapter offerScreenAdapter;
    private OfferScreenDetailsPresenter offerScreenDetailsPresenter;
    private LinearLayoutManager linearLayoutManager;
    private SharedPrefs sharedPrefs;

    String access_token;

    @BindView(R.id.offersRecycler)
    RecyclerView recyclerView;
    @BindView(R.id.offersProgressBar)
    ProgressBar progressBar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.imageView)
    ImageView shopImage;

    private OnFragmentInteractionListener mListener;

    public ShopOfferFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShopOfferFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShopOfferFragment newInstance(String param1, String param2) {
        ShopOfferFragment fragment = new ShopOfferFragment();
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

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        View view= inflater.inflate(R.layout.activity_offerscreen, container, false);
        ButterKnife.bind(this,view);
        HomePage homePage=new HomePage();
        shop_id=homePage.getShop_id();
        initialize();

        toolbar.setTitle("Offers");
        toolbar.setNavigationIcon(ContextCompat.getDrawable(getContext(),R.drawable.ic_arrow_back_white_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        offerScreenDetailsPresenter.requestOfferList(sharedPrefs.getAccessToken(),shop_id);
        return view;
    }
    void initialize()
    {
        sharedPrefs=new SharedPrefs(getContext());
        access_token=sharedPrefs.getAccessToken();
        linearLayoutManager=new LinearLayoutManager(getContext());
        offerScreenAdapter=new OfferScreenAdapter(getContext());
        recyclerView.setHasFixedSize(true);
        offerScreenDetailsPresenter=new OfferScreenDetailsPresenterImpl(this,new RetrofitOfferScreenDetailsProvider());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(offerScreenAdapter);
        recyclerView.setNestedScrollingEnabled(true);
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

        Picasso.with(getContext()).load(offerScreenList.getShop_image()).fit().into(shopImage);
        offerScreenAdapter.setdata(offerScreenList.getOffer_list());
        offerScreenAdapter.notifyDataSetChanged();
    }

    @Override
    public void onOfferSelected(OfferScreenDetails offerScreenDetails) {

    }

    @Override
    public void onOfferSent() {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
