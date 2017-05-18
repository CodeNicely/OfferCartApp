package com.codenicely.discountstore.project_new.shop_offerlist.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.codenicely.discountstore.project_new.R;
import com.codenicely.discountstore.project_new.helper.SharedPrefs;
import com.codenicely.discountstore.project_new.shop_offerlist.model.MockShopOfferListProvider;
import com.codenicely.discountstore.project_new.shop_offerlist.model.RetrofitShopOfferListProvider;
import com.codenicely.discountstore.project_new.shop_offerlist.model.data.ShopOfferListData;
import com.codenicely.discountstore.project_new.shop_offerlist.model.data.ShopOfferListDetails;
import com.codenicely.discountstore.project_new.shop_offerlist.presenter.ShopOfferListPresenter;
import com.codenicely.discountstore.project_new.shop_offerlist.presenter.ShopOfferListPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShopOfferListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShopOfferListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopOfferListFragment extends Fragment implements ShopOfferListView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

     String access_token;

    @BindView(R.id.shop_name1)
    TextView name;
    @BindView(R.id.shop_subscription)
    TextView subscription;
    @BindView(R.id.add_button1)
    Button add_offer;
    @BindView(R.id.progressBar_shop1)
    ProgressBar progressBar;
    @BindView(R.id.recycler_view_shop_offer)
    RecyclerView recyclerView;
    private ShopOfferAdapter shopOfferAdapter;
    private SharedPrefs sharedPrefs;
    private LinearLayoutManager linearLayoutManager;
    private ShopOfferListPresenter shopOfferListPresenter;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ShopOfferListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShopOfferListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShopOfferListFragment newInstance(String param1, String param2) {
        ShopOfferListFragment fragment = new ShopOfferListFragment();
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
        View view=inflater.inflate(R.layout.fragment_shop_offerlist, container, false);
        ButterKnife.bind(this,view);
        sharedPrefs = new SharedPrefs(getContext());
      //  shopOfferListPresenter = new ShopOfferListPresenterImpl(new RetrofitShopOfferListProvider(),this);
        shopOfferListPresenter = new ShopOfferListPresenterImpl(new MockShopOfferListProvider(),this);
        shopOfferAdapter = new ShopOfferAdapter(getContext(),this);
        linearLayoutManager= new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(shopOfferAdapter);

       shopOfferListPresenter.requestShopOffer(sharedPrefs.getKeyAccessTokenShop());

add_offer.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        //call add offer fragment
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

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void showProgressBar(boolean show) {
        if(show){
            progressBar.setVisibility(View.VISIBLE);
        }
        else{
            progressBar.setVisibility(View.GONE);

        }

    }

    @Override
    public void showMessage(String error) {
        Toast.makeText(getContext(),error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setData(ShopOfferListData shopOfferListData) {
           name.setText(shopOfferListData.getShop_name());
        subscription.setText(shopOfferListData.getSubscription_validity());
        shopOfferAdapter.setData(shopOfferListData.getShop_offer_list());
        shopOfferAdapter.notifyDataSetChanged();











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
