package com.codenicely.discountstore.project_new.shop_admin.payment_shop.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.codenicely.discountstore.project_new.R;
import com.codenicely.discountstore.project_new.helper.Keys;
import com.codenicely.discountstore.project_new.helper.SharedPrefs;
import com.codenicely.discountstore.project_new.shop_admin.payment_shop.model.RetrofitPaymentShopProvider;
import com.codenicely.discountstore.project_new.shop_admin.payment_shop.model.data.ShopPaymentData;
import com.codenicely.discountstore.project_new.shop_admin.payment_shop.presenter.ShopPaymentPresenter;
import com.codenicely.discountstore.project_new.shop_admin.payment_shop.presenter.ShopPaymentPresenterImpl;
import com.codenicely.discountstore.project_new.shop_admin.shop_home.ShopHomePage;
import com.codenicely.discountstore.project_new.shop_admin.shop_offerlist.view.ShopOfferListFragment;
import com.payUMoney.sdk.PayUmoneySdkInitilizer;
import com.payUMoney.sdk.SdkConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShopPaymentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShopPaymentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopPaymentFragment extends Fragment implements PaymentShopView{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String SUBSCRIPTION = "trans";


    // TODO: Rename and change types of parameters
    private int id;
    private  String transaction;
    @BindView(R.id.progressBarShop)
    ProgressBar progressBar;

    private SharedPrefs sharedPrefs;
    private ShopPaymentPresenter shopPaymentPresenter;
   private ShopOfferListFragment shopOfferListFragment;



    private OnFragmentInteractionListener mListener;

    public ShopPaymentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment ShopPaymentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShopPaymentFragment newInstance(int iD) {
        ShopPaymentFragment fragment = new ShopPaymentFragment();
        Bundle args = new Bundle();
        args.putInt(SUBSCRIPTION,iD);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(SUBSCRIPTION);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_shop_payment, container, false);
        ButterKnife.bind(this,view);
        shopPaymentPresenter = new ShopPaymentPresenterImpl(new RetrofitPaymentShopProvider(),this);
        sharedPrefs = new SharedPrefs(getContext());
                shopOfferListFragment=new ShopOfferListFragment();
        shopPaymentPresenter.requestShopPaymentHash(id,sharedPrefs.getKeyAccessTokenShop());
        return(view);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


   public void onActivityResult(int requestCode, int resultCode, Intent data) {
       Log.d("OnActivityResult", "activity");
        if (requestCode == PayUmoneySdkInitilizer.PAYU_SDK_PAYMENT_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {
                Log.d("Response", "ok");
                String paymentId = data.getStringExtra(SdkConstants.PAYMENT_ID);

                shopPaymentPresenter.updateShopPaymentStatus(sharedPrefs.getKeyAccessTokenShop(), transaction,true);

                ((ShopHomePage)getContext()).setFragment(shopOfferListFragment,"HOME");

            } else if (resultCode == RESULT_CANCELED) {
                Log.d("Response", "failure");

                shopPaymentPresenter.updateShopPaymentStatus(sharedPrefs.getKeyAccessTokenShop(), transaction,false);

                ((ShopHomePage)getContext()).setFragment(shopOfferListFragment,"HOME");
            } else if (resultCode == PayUmoneySdkInitilizer.RESULT_FAILED) {
                Log.i("app_activity", "failure");
                Log.d("Response", "failure");
                shopPaymentPresenter.updateShopPaymentStatus(sharedPrefs.getKeyAccessTokenShop(), transaction,false);


                ((ShopHomePage)getContext()).setFragment(shopOfferListFragment,"HOME");
            } else if (resultCode == PayUmoneySdkInitilizer.RESULT_BACK) {
                Log.i("Response", "User returned without login");
                Log.d("Response", "failure");
                Toast.makeText(getContext(), "User Returned Without Login to PayuMoney", Toast.LENGTH_SHORT).show();
                shopPaymentPresenter.updateShopPaymentStatus(sharedPrefs.getKeyAccessTokenShop(), transaction,false);

                Log.d("BACK AMAN","back");


            }
        }
   }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading(boolean show) {
        if (show)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);


    }

    @Override
    public void proceedToShopPayment(ShopPaymentData shopPaymentData) {
        Log.d("PAYMENT","proceed_to_payment");
        PayUmoneySdkInitilizer.PaymentParam.Builder builder = new PayUmoneySdkInitilizer.PaymentParam.Builder();



        transaction= shopPaymentData.getTransaction_id();
        builder
                .setMerchantId(shopPaymentData.getMerchant_id())
                .setKey(shopPaymentData.getKey())
                .setIsDebug(false)
                .setAmount(shopPaymentData.getAmount())
                .setTnxId(shopPaymentData.getTransaction_id())
                .setPhone(shopPaymentData.getMobile())
                .setProductName(shopPaymentData.getProduct_name())
                .setFirstName(shopPaymentData.getName())
                .setEmail(shopPaymentData.getEmail())
                .setsUrl("https://www.payumoney.com/mobileapp/payumoney/success.php")
                .setfUrl("https://www.payumoney.com/mobileapp/payumoney/failure.php")
                .setUdf1("")
                .setUdf2("")
                .setUdf3("")
                .setUdf4("")
                .setUdf5("");
        Log.d("PAYMENT2","proceed_to_payment2");
        PayUmoneySdkInitilizer.PaymentParam paymentParam = builder.build();
        paymentParam.setMerchantHash(shopPaymentData.getServer_hash());
        Log.d("PAYMENT3","proceed_to_payment3");
        PayUmoneySdkInitilizer.startPaymentActivityForResult(getActivity(), paymentParam);

        Log.d("BACK AMAN","back");
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
