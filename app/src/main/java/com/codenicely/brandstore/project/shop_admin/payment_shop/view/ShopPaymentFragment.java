package com.codenicely.brandstore.project.shop_admin.payment_shop.view;

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

import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.helper.SharedPrefs;
import com.codenicely.brandstore.project.shop_admin.payment_shop.model.RetrofitPaymentShopProvider;
import com.codenicely.brandstore.project.shop_admin.payment_shop.model.data.ShopPaymentData;
import com.codenicely.brandstore.project.shop_admin.payment_shop.presenter.ShopPaymentPresenter;
import com.codenicely.brandstore.project.shop_admin.payment_shop.presenter.ShopPaymentPresenterImpl;
import com.codenicely.brandstore.project.shop_admin.shop_home.ShopHomePage;
import com.codenicely.brandstore.project.shop_admin.shop_offerlist.view.ShopOfferListFragment;
import com.paytm.pgsdk.PaytmClientCertificate;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;




public class ShopPaymentFragment extends Fragment implements PaymentShopView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String SUBSCRIPTION = "trans";
    private Context context;

    // TODO: Rename and change types of parameters
    private int id;
    private String transaction;
    @BindView(R.id.progressBarShop)
    ProgressBar progressBar;

    private SharedPrefs sharedPrefs;
    private ShopPaymentPresenter shopPaymentPresenter;
    private ShopOfferListFragment shopOfferListFragment;


    private OnFragmentInteractionListener mListener;

    public ShopPaymentFragment() {

        // Required empty public constructor

    }



    // TODO: Rename and change types and number of parameters
    public static ShopPaymentFragment newInstance(int iD) {
        ShopPaymentFragment fragment = new ShopPaymentFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt("subscription_id");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop_payment, container, false);
        ButterKnife.bind(this, view);
        context = getContext();
        shopPaymentPresenter = new ShopPaymentPresenterImpl(new RetrofitPaymentShopProvider(), this);
        sharedPrefs = new SharedPrefs(getContext());
        shopOfferListFragment = new ShopOfferListFragment();
        shopPaymentPresenter.requestShopPaymentHash(id,1, 0,sharedPrefs.getKeyAccessTokenShop());
        return (view);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


   /* public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("OnActivityResult", "activity");
        if (requestCode == PayUmoneySdkInitilizer.PAYU_SDK_PAYMENT_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {
                Log.d("CityData", "ok");
                String paymentId = data.getStringExtra(SdkConstants.PAYMENT_ID);

                shopPaymentPresenter.updateShopPaymentStatus(sharedPrefs.getKeyAccessTokenShop(), transaction, true);

                ((ShopHomePage) getContext()).setFragment(shopOfferListFragment, "HOME");

            } else if (resultCode == RESULT_CANCELED) {
                Log.d("CityData", "failure");

                shopPaymentPresenter.updateShopPaymentStatus(sharedPrefs.getKeyAccessTokenShop(), transaction, false);

                ((ShopHomePage) getContext()).setFragment(shopOfferListFragment, "HOME");
            } else if (resultCode == PayUmoneySdkInitilizer.RESULT_FAILED) {
                Log.i("app_activity", "failure");
                Log.d("CityData", "failure");
                shopPaymentPresenter.updateShopPaymentStatus(sharedPrefs.getKeyAccessTokenShop(), transaction, false);


                ((ShopHomePage) getContext()).setFragment(shopOfferListFragment, "HOME");
            } else if (resultCode == PayUmoneySdkInitilizer.RESULT_BACK) {
                Log.i("CityData", "User returned without login");
                Log.d("CityData", "failure");
                Toast.makeText(getContext(), "User Returned Without Login to PayuMoney", Toast.LENGTH_SHORT).show();
                shopPaymentPresenter.updateShopPaymentStatus(sharedPrefs.getKeyAccessTokenShop(), transaction, false);

                Log.d("BACK AMAN", "back");


            }
        }
    }*/

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading(boolean show) {
        if (show)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }



    @Override
    public void proceedToShopPayment(ShopPaymentData shopPaymentData,int days,int payment_type) {
        //Getting the Service Instance. PaytmPGService.getStagingService() will return //the Service pointing to Staging
        //Environment.

//and PaytmPGService.getProductionService() will return the Service pointing to //Production Environment.

        PaytmPGService Service = PaytmPGService.getStagingService();
//                Service = PaytmPGService.getProductionService();

//Create new order Object having all order information.
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("MID", shopPaymentData.getMerchant_id());
        paramMap.put("ORDER_ID", shopPaymentData.getOrder_id());
        paramMap.put("CUST_ID", shopPaymentData.getMobile());
        paramMap.put("INDUSTRY_TYPE_ID", shopPaymentData.getIndustry_type_id());
        paramMap.put("CHANNEL_ID", shopPaymentData.getChannel_id());
        paramMap.put("TXN_AMOUNT", shopPaymentData.getAmount());
        paramMap.put("WEBSITE", shopPaymentData.getWebsite());
        paramMap.put("CALLBACK_URL", shopPaymentData.getCallback_url());
        paramMap.put("EMAIL", shopPaymentData.getEmail());
        paramMap.put("MOBILE_NO", shopPaymentData.getMobile());
        paramMap.put("CHECKSUMHASH", shopPaymentData.getChecksum_hash());

//Create Client Certificate object holding the information related to Client Certificate. Filename must be without .p12 extension.
//For example, if suppose client.p12 is stored in raw folder, then filename must be client.
        PaytmClientCertificate Certificate = new PaytmClientCertificate(null, null);

        PaytmOrder Order = new PaytmOrder(paramMap);

//Set PaytmOrder and PaytmClientCertificate Object. Call this method and set both objects before starting transaction.
//        Service.initialize(Order, Certificate);
//OR
        Service.initialize(Order, null);

//Start the Payment Transaction. Before starting the transaction ensure that initialize method is called.

        Service.startPaymentTransaction(context, true, true, new PaytmPaymentTransactionCallback() {

            @Override
            public void someUIErrorOccurred(String inErrorMessage) {
                Log.d("LOG", "UI Error Occur.");
                Toast.makeText(context, " UI Error Occur. ", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onTransactionResponse(Bundle inResponse) {
                Log.d("LOG", "Payment Transaction : " + inResponse);
                Toast.makeText(context, "Payment Transaction response " + inResponse.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void networkNotAvailable() {
                Log.d("LOG", "UI Error Occur.");
                Toast.makeText(context, " UI Error Occur. ", Toast.LENGTH_LONG).show();
            }

            @Override
            public void clientAuthenticationFailed(String inErrorMessage) {
                Log.d("LOG", "UI Error Occur.");
                Toast.makeText(context, " Severside Error " + inErrorMessage, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onErrorLoadingWebPage(int iniErrorCode,
                                              String inErrorMessage, String inFailingUrl) {
                Log.d("LOG", "Error Loading Web page");
                Toast.makeText(context, " Error loading webpage " + inErrorMessage, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onBackPressedCancelTransaction() {
                // TODO Auto-generated method stub
                Log.d("LOG", "Error onBackPressedCancelTransaction");
                Toast.makeText(context, " onBackPressedCancelTransaction " , Toast.LENGTH_LONG).show();

            }

            @Override
            public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {
                Log.d("LOG", "Payment Transaction Failed " + inErrorMessage);
                Toast.makeText(context, "Payment Transaction Failed ", Toast.LENGTH_LONG).show();
            }

        });

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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
