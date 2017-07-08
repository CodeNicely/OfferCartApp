package com.codenicely.brandstore.project.shop_admin.shop_add_subscription.view;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.helper.SharedPrefs;
import com.codenicely.brandstore.project.shop_admin.shop_add_subscription.model.data.AddSubscriptionDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ujjwal on 4/6/17.
 */
public class ShopSubscriptionAdapter extends RecyclerView.Adapter<ShopSubscriptionAdapter.MyViewHolder> {

	List<AddSubscriptionDetails> addSubscriptionDetailsList=new ArrayList<>();
	Context context;
	AddSubscriptionFragment addSubscriptionFragment;
	private LayoutInflater layoutInflater;
	SharedPrefs sharedPrefs;

	public ShopSubscriptionAdapter(Context context, AddSubscriptionFragment addSubscriptionFragment) {
		this.context = context;
		this.addSubscriptionFragment = addSubscriptionFragment;
		this.layoutInflater = LayoutInflater.from(context);

	}

	@Override
	public ShopSubscriptionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		sharedPrefs=new SharedPrefs(context);
		View view = layoutInflater.inflate(R.layout.shop_subscription_item, parent, false);
		return new ShopSubscriptionAdapter.MyViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ShopSubscriptionAdapter.MyViewHolder holder, int position) {
		final AddSubscriptionDetails addSubscriptionDetails = addSubscriptionDetailsList.get(position);
		holder.title.setText(addSubscriptionDetails.getSubscription_title());
		holder.validity.setText(addSubscriptionDetails.getSubscription_period()+"");
		holder.validity.append(" days");
		holder.description.setText(addSubscriptionDetails.getSubscription_description());
		holder.cost.setText(R.string.Rs);
		holder.cost.append(" "+addSubscriptionDetails.getSubscription_price()+"");


		holder.register.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
						addSubscriptionFragment.requestShopPaymentHash(2,addSubscriptionDetails.getSubscription_id(),addSubscriptionDetails.getSubscription_period());

//				addSubscriptionFragment.getRegisterrationDetails(sharedPrefs.getAccessToken(),addSubscriptionDetails.getSubscription_id());
//
//				final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
//				final RadioGroup slots_group;
//				Button done;
//				final View alertLayout = layoutInflater.inflate(R.layout.subscription_payment_item, new LinearLayout(context));
//				alertDialog.setView(alertLayout);
//				alertDialog.setCancelable(true);
//
//				slots_group = (RadioGroup) alertLayout.findViewById(R.id.radio_group);
//				done = (Button) alertLayout.findViewById(R.id.done);
//
//				assert slots_group != null;
//				slots_group.setOrientation(LinearLayout.VERTICAL);
//
//				done.setOnClickListener(new View.OnClickListener() {
//					@Override
//					public void onClick(View v) {
//						alertDialog.hide();
//					}
//				});
//
//
//				RadioButton radioButtonPaytm = (RadioButton) alertLayout.findViewById(R.id.paytm);
//				RadioButton radioButtonRazorPay=(RadioButton) alertLayout.findViewById(R.id.razorpay);
//				View.OnClickListener first_radio_listener = new View.OnClickListener(){
//					public void onClick(View v) {
//						addSubscriptionFragment.requestShopPaymentHash(1,addSubscriptionDetails.getSubscription_id(),addSubscriptionDetails.getSubscription_period());
//						alertDialog.hide();
//						slots_group.setClickable(false);
//					}
//				};
//				radioButtonPaytm.setOnClickListener(first_radio_listener);
//
////				radioButtonPaytm.setOnClickListener(new View.OnClickListener() {
////					@Override
////					public void onClick(View v) {
////
////					}
////				});
//				View.OnClickListener second_radio_listener = new View.OnClickListener(){
//					public void onClick(View v) {
//						addSubscriptionFragment.requestShopPaymentHash(2,addSubscriptionDetails.getSubscription_id(),addSubscriptionDetails.getSubscription_period());
//						alertDialog.hide();
//						slots_group.setClickable(false);
//					}
//				};
//				radioButtonRazorPay.setOnClickListener(second_radio_listener);
//
//				alertDialog.show();
			}
		});

	}

	public void setSubscriptionData(List<AddSubscriptionDetails> addSubscriptionDetailsList) {
		this.addSubscriptionDetailsList = addSubscriptionDetailsList;
	}

	@Override
	public int getItemCount()
	{
		return addSubscriptionDetailsList.size();
	}


	public class MyViewHolder extends RecyclerView.ViewHolder {
	    private TextView title, cost, validity, description;
        private Button register;

        private MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            cost = (TextView) itemView.findViewById(R.id.cost);
            validity = (TextView) itemView.findViewById(R.id.validity);
            register = (Button) itemView.findViewById(R.id.register);
        }
	}

}
