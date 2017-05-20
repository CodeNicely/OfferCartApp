package com.codenicely.discountstore.project_new.shop_admin.shop_add_subscription.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.codenicely.discountstore.project_new.R;
import com.codenicely.discountstore.project_new.shop_admin.shop_add_subscription.model.RetrofitAddSubscriptionProvider;
import com.codenicely.discountstore.project_new.shop_admin.shop_add_subscription.model.data.AddSubscriptionData;
import com.codenicely.discountstore.project_new.shop_admin.shop_add_subscription.model.data.AddSubscriptionDetails;
import com.codenicely.discountstore.project_new.shop_admin.shop_add_subscription.presenter.AddSubscriptionPresenter;
import com.codenicely.discountstore.project_new.shop_admin.shop_add_subscription.presenter.AddSubscriptionPresenterImpl;
import com.codenicely.discountstore.project_new.helper.SharedPrefs;
import com.codenicely.discountstore.project_new.shop_admin.payment_shop.view.ShopPaymentFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddSubscriptionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddSubscriptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddSubscriptionFragment extends Fragment implements AddSubscriptionView{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


   private String access_token;
    private  int offerPrice;
    @BindView(R.id.spinner_add_subscription)
    Spinner subscription_spinner;
    @BindView(R.id.addSubacriptionProgressBar)
    ProgressBar subscriptionProgressBar;
    @BindView(R.id.add_subscription_button)
    Button add_subscription;

private SharedPrefs sharedPrefs;
    private AddSubscriptionPresenter addSubscriptionPresenter;
private AddSubscriptionDetails addSubscriptionDetails;

    private OnFragmentInteractionListener mListener;

    public AddSubscriptionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddSubscriptionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddSubscriptionFragment newInstance(String param1, String param2) {
        AddSubscriptionFragment fragment = new AddSubscriptionFragment();
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
        View view =inflater.inflate(R.layout.fragment_add_subscription, container, false);

        ButterKnife.bind(this,view);
        sharedPrefs = new SharedPrefs(getContext());
        addSubscriptionPresenter=new AddSubscriptionPresenterImpl(new RetrofitAddSubscriptionProvider(),this);
        addSubscriptionPresenter.requestSubscription(access_token);
access_token=sharedPrefs.getKeyAccessTokenShop();
        add_subscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShopPaymentFragment shopPaymentFragment=ShopPaymentFragment.newInstance(offerPrice);

                //call pay u money gateway
//selected offer"s ID is stored in variable offer_id

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
    public void showProgressBar(boolean show) {
        if(show){
            subscriptionProgressBar.setVisibility(View.VISIBLE);
        }
        else{
            subscriptionProgressBar.setVisibility(View.GONE);

        }

    }

    @Override
    public void showMessage(String error) {
        Toast.makeText(getContext(),error,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setData(AddSubscriptionData addSubscriptionData) {
        List<AddSubscriptionDetails> addSubscriptionDetailses = new ArrayList<AddSubscriptionDetails>(addSubscriptionData.getSubscription_offer_data());


        ArrayAdapter<String> adapter;
        int n= addSubscriptionDetailses.size();
        final int price[] = new int [n];
        final String validity[] = new String[n];
        final int id[] = new int[n];
        int i=0;
        while(i<n)
        {
            addSubscriptionDetails = addSubscriptionDetailses.get(i);
           price[i] = addSubscriptionDetails.getSubscription_price();
            validity[i] = addSubscriptionDetails.getSubscription_validity()+" days validity for Rs"+addSubscriptionDetails.getSubscription_price();
            id[i] = addSubscriptionDetails.getSubscription_offer_id();
            i++;
        }


        adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item,validity);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subscription_spinner.setAdapter(adapter);
        subscription_spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int t, long l) {
                offerPrice=price[t];

            }
        });



    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
