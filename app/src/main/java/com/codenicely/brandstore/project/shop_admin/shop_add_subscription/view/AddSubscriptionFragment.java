package com.codenicely.brandstore.project.shop_admin.shop_add_subscription.view;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.shop_admin.payment_shop.model.RetrofitPaymentShopProvider;
import com.codenicely.brandstore.project.shop_admin.payment_shop.model.data.ShopPaymentData;
import com.codenicely.brandstore.project.shop_admin.payment_shop.presenter.ShopPaymentPresenter;
import com.codenicely.brandstore.project.shop_admin.payment_shop.presenter.ShopPaymentPresenterImpl;
import com.codenicely.brandstore.project.shop_admin.payment_shop.view.PaymentShopView;
import com.codenicely.brandstore.project.shop_admin.shop_add_subscription.model.RetrofitAddSubscriptionProvider;
import com.codenicely.brandstore.project.shop_admin.shop_add_subscription.model.data.AddSubscriptionDetails;
import com.codenicely.brandstore.project.shop_admin.shop_add_subscription.presenter.AddSubscriptionPresenter;
import com.codenicely.brandstore.project.shop_admin.shop_add_subscription.presenter.AddSubscriptionPresenterImpl;
import com.codenicely.brandstore.project.helper.SharedPrefs;
import com.codenicely.brandstore.project.shop_admin.shop_home.ShopHomePage;
import com.codenicely.brandstore.project.shop_admin.shop_offerlist.view.ShopOfferListFragment;
import com.paytm.pgsdk.PaytmClientCertificate;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddSubscriptionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddSubscriptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddSubscriptionFragment extends Fragment implements AddSubscriptionView,PaymentShopView {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	private static final String TAG = AddSubscriptionFragment.class.getSimpleName();

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	private int id;

	private String access_token;
	private int offerId;
	private String transaction;

	/*@BindView(R.id.spinner_add_subscription)
    Spinner subscription_spinner;*/
	@BindView(R.id.addSubacriptionProgressBar)
	ProgressBar subscriptionProgressBar;
	/*@BindView(R.id.add_subscription_button)
    Button add_subscription;
	*/
	@BindView(R.id.recycler_view_shop_subscription)
	RecyclerView recyclerView;

	@BindView(R.id.toolbar)
	Toolbar toolbar;

	private SharedPrefs sharedPrefs;
	private AddSubscriptionPresenter addSubscriptionPresenter;
	private ShopOfferListFragment shopOfferListFragment;

	private OnFragmentInteractionListener mListener;
	private LinearLayoutManager linearLayoutManager;

	private ShopSubscriptionAdapter shopSubscriptionAdapter;
	private ShopPaymentPresenter shopPaymentPresenter;

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

		View view = inflater.inflate(R.layout.fragment_add_subscription, container, false);
		ButterKnife.bind(this, view);

		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().onBackPressed();
			}
		});

		sharedPrefs = new SharedPrefs(getContext());
		addSubscriptionPresenter = new AddSubscriptionPresenterImpl(new RetrofitAddSubscriptionProvider(), this);
		access_token = sharedPrefs.getKeyAccessTokenShop();
		shopSubscriptionAdapter = new ShopSubscriptionAdapter(getContext(), this);
		linearLayoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(linearLayoutManager);
		recyclerView.setAdapter(shopSubscriptionAdapter);
		addSubscriptionPresenter.requestSubscription(access_token);
		shopPaymentPresenter = new ShopPaymentPresenterImpl(new RetrofitPaymentShopProvider(), this);


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
		if (show) {
			subscriptionProgressBar.setVisibility(View.VISIBLE);
		} else {
			subscriptionProgressBar.setVisibility(View.GONE);
		}

	}

	@Override
	public void showMessage(String error) {
		Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();

	}

	@Override
	public void showLoading(boolean show) {
		if (show)
			subscriptionProgressBar.setVisibility(View.VISIBLE);
		else
			subscriptionProgressBar.setVisibility(View.GONE);
	}


	@Override
	public void proceedToShopPayment(ShopPaymentData shopPaymentData,int days,int payment_type) {
   		final Context context = getContext();
		((ShopHomePage)context).setTransaction_id(shopPaymentData.getOrder_id(),access_token,days);
		if (payment_type==2){


		final Checkout co = new Checkout();
		try {
			JSONObject options = new JSONObject();
			options.put("name", "Brand Store");
			options.put("description", "Subscription Charges");
//			options.put("image", "https://rzp-mobile.s3.amazonaws.com/images/rzp.png");
            options.put("id",shopPaymentData.getOrder_id());
			options.put("currency", "INR");
            options.put("amount", String.valueOf(Integer.valueOf(shopPaymentData.getAmount())*100));
			JSONObject preFill = new JSONObject();
//			preFill.put("email", "");
			Log.d(TAG,shopPaymentData.getMobile()+" -- "+shopPaymentData.getEmail());
            preFill.put("contact", shopPaymentData.getMobile());
 		    options.put("prefill", preFill);
			Log.d("PaymentSuccess", "-----------"+"---");
			co.open(getActivity(), options);

		}catch (Exception e){
	     Toast.makeText(context, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
   	     e.printStackTrace();
		}
		}else if (payment_type==1){
		//	Getting the Service Instance. PaytmPGService.getStagingService() will return //the Service pointing to Staging
		//Environment.

		//and PaytmPGService.getProductionService() will return the Service pointing to //Production Environment.

		PaytmPGService Service = PaytmPGService.getStagingService();
		//Service = PaytmPGService.getProductionService();

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

		Service.startPaymentTransaction(getContext(), true, true, new PaytmPaymentTransactionCallback() {

			@Override
			public void someUIErrorOccurred(String inErrorMessage) {
				Log.d("LOG", "UI Error Occur.");
				Toast.makeText(getContext(), " UI Error Occur. ", Toast.LENGTH_LONG).show();
			}

			@Override
			public void onTransactionResponse(Bundle inResponse) {
				Log.d("LOG", "Payment Transaction : " + inResponse);
				Toast.makeText(getContext(), "Payment Transaction response " + inResponse.toString(), Toast.LENGTH_LONG).show();
			}

			@Override
			public void networkNotAvailable() {
				Log.d("LOG", "UI Error Occur.");
				Toast.makeText(getContext(), " UI Error Occur. ", Toast.LENGTH_LONG).show();
			}

			@Override
			public void clientAuthenticationFailed(String inErrorMessage) {
				Log.d("LOG", "UI Error Occur.");
				Toast.makeText(getContext(), " Severside Error " + inErrorMessage, Toast.LENGTH_LONG).show();
			}

			@Override
			public void onErrorLoadingWebPage(int iniErrorCode,
											  String inErrorMessage, String inFailingUrl) {
				Log.d("LOG", "Error Loading Web page");
				Toast.makeText(getContext(), " Error loading webpage " + inErrorMessage, Toast.LENGTH_LONG).show();
			}

			@Override
			public void onBackPressedCancelTransaction() {
				// TODO Auto-generated method stub
				Log.d("LOG", "Error onBackPressedCancelTransaction");
				Toast.makeText(getContext(), " onBackPressedCancelTransaction " , Toast.LENGTH_LONG).show();

			}

			@Override
			public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {
				Log.d("LOG", "Payment Transaction Failed " + inErrorMessage);
				Toast.makeText(getContext(), "Payment Transaction Failed ", Toast.LENGTH_LONG).show();
			}

		});

		}
	}

	public void requestShopPaymentHash(int payment_type,int id,int days) {
		//1--->Paytm
		//2--->Razorpay
			shopPaymentPresenter.requestShopPaymentHash(payment_type,id,days, sharedPrefs.getKeyAccessTokenShop());
	}


//	@Override
//	public void proceedToShopPayment(ShopPaymentData shopPaymentData) {
//		//Getting the Service Instance. PaytmPGService.getStagingService() will return //the Service pointing to Staging
//		//Environment.
//
//		//and PaytmPGService.getProductionService() will return the Service pointing to //Production Environment.
//
//		PaytmPGService Service = PaytmPGService.getStagingService();
//		//Service = PaytmPGService.getProductionService();
//
//		//Create new order Object having all order information.
//		Map<String, String> paramMap = new HashMap<>();
//		paramMap.put("MID", shopPaymentData.getMerchant_id());
//		paramMap.put("ORDER_ID", shopPaymentData.getOrder_id());
//		paramMap.put("CUST_ID", shopPaymentData.getMobile());
//		paramMap.put("INDUSTRY_TYPE_ID", shopPaymentData.getIndustry_type_id());
//		paramMap.put("CHANNEL_ID", shopPaymentData.getChannel_id());
//		paramMap.put("TXN_AMOUNT", shopPaymentData.getAmount());
//		paramMap.put("WEBSITE", shopPaymentData.getWebsite());
//		paramMap.put("CALLBACK_URL", shopPaymentData.getCallback_url());
//		paramMap.put("EMAIL", shopPaymentData.getEmail());
//		paramMap.put("MOBILE_NO", shopPaymentData.getMobile());
//		paramMap.put("CHECKSUMHASH", shopPaymentData.getChecksum_hash());
//
//		//Create Client Certificate object holding the information related to Client Certificate. Filename must be without .p12 extension.
//		//For example, if suppose client.p12 is stored in raw folder, then filename must be client.
//		PaytmClientCertificate Certificate = new PaytmClientCertificate(null, null);
//
//		PaytmOrder Order = new PaytmOrder(paramMap);
//
//		//Set PaytmOrder and PaytmClientCertificate Object. Call this method and set both objects before starting transaction.
//		//        Service.initialize(Order, Certificate);
//		//OR
//		Service.initialize(Order, null);
//
//		//Start the Payment Transaction. Before starting the transaction ensure that initialize method is called.
//
//		Service.startPaymentTransaction(getContext(), true, true, new PaytmPaymentTransactionCallback() {
//
//			@Override
//			public void someUIErrorOccurred(String inErrorMessage) {
//				Log.d("LOG", "UI Error Occur.");
//				Toast.makeText(getContext(), " UI Error Occur. ", Toast.LENGTH_LONG).show();
//			}
//
//			@Override
//			public void onTransactionResponse(Bundle inResponse) {
//				Log.d("LOG", "Payment Transaction : " + inResponse);
//				Toast.makeText(getContext(), "Payment Transaction response " + inResponse.toString(), Toast.LENGTH_LONG).show();
//			}
//
//			@Override
//			public void networkNotAvailable() {
//				Log.d("LOG", "UI Error Occur.");
//				Toast.makeText(getContext(), " UI Error Occur. ", Toast.LENGTH_LONG).show();
//			}
//
//			@Override
//			public void clientAuthenticationFailed(String inErrorMessage) {
//				Log.d("LOG", "UI Error Occur.");
//				Toast.makeText(getContext(), " Severside Error " + inErrorMessage, Toast.LENGTH_LONG).show();
//			}
//
//			@Override
//			public void onErrorLoadingWebPage(int iniErrorCode,
//											  String inErrorMessage, String inFailingUrl) {
//				Log.d("LOG", "Error Loading Web page");
//				Toast.makeText(getContext(), " Error loading webpage " + inErrorMessage, Toast.LENGTH_LONG).show();
//			}
//
//			@Override
//			public void onBackPressedCancelTransaction() {
//				// TODO Auto-generated method stub
//				Log.d("LOG", "Error onBackPressedCancelTransaction");
//				Toast.makeText(getContext(), " onBackPressedCancelTransaction " , Toast.LENGTH_LONG).show();
//
//			}
//
//			@Override
//			public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {
//				Log.d("LOG", "Payment Transaction Failed " + inErrorMessage);
//				Toast.makeText(getContext(), "Payment Transaction Failed ", Toast.LENGTH_LONG).show();
//			}
//
//		});
//
//	}


	@Override
	public void setData(List<AddSubscriptionDetails> addSubscriptionDetailsList) {
		shopSubscriptionAdapter.setSubscriptionData(addSubscriptionDetailsList);
		shopSubscriptionAdapter.notifyDataSetChanged();

/*
        int n= addSubscriptionDetailses.size();
        final int price[] = new int [n];
        final String title[] = new String[n];
        final int id[] = new int[n];
        int i=0;
        while(i<n)
        {
            addSubscriptionDetails = addSubscriptionDetailses.get(i);
           price[i] = addSubscriptionDetails.getSubscription_price();
            title[i] = addSubscriptionDetails.getSubscription_title();
            id[i] = addSubscriptionDetails.getSubscription_id();
            i++;
        }
*/


/*
        adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item,title);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subscription_spinner.setAdapter(adapter);
        subscription_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int t, long l) {
                 offerId=id[t];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
*/


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
