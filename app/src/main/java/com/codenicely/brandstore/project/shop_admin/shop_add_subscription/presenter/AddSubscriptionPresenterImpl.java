package com.codenicely.brandstore.project.shop_admin.shop_add_subscription.presenter;

import com.codenicely.brandstore.project.shop_admin.shop_add_subscription.AddSubscriptionCallBack;
import com.codenicely.brandstore.project.shop_admin.shop_add_subscription.model.AddSubscriptionProvider;
import com.codenicely.brandstore.project.shop_admin.shop_add_subscription.model.data.AddSubscriptionData;
import com.codenicely.brandstore.project.shop_admin.shop_add_subscription.view.AddSubscriptionView;
import com.paytm.pgsdk.Log;

/**
 * Created by aman on 18/5/17.
 */

public class AddSubscriptionPresenterImpl implements AddSubscriptionPresenter {

private AddSubscriptionProvider addSubscriptionProvider;
    private AddSubscriptionView addSubscriptionView;

    public AddSubscriptionPresenterImpl(AddSubscriptionProvider addSubscriptionProvider, AddSubscriptionView addSubscriptionView) {
        this.addSubscriptionProvider = addSubscriptionProvider;
        this.addSubscriptionView = addSubscriptionView;
    }

    @Override
    public void requestSubscription(String access_token) {
        addSubscriptionView.showProgressBar(true);
        addSubscriptionProvider.requestSubscription(access_token, new AddSubscriptionCallBack() {
            @Override
            public void OnSuccess(AddSubscriptionData addSubscriptionData) {
                if(addSubscriptionData.isSuccess()) {
                    addSubscriptionView.showProgressBar(false);
                    Log.d("PRESENTER----","Success");
                    addSubscriptionView.setData(addSubscriptionData.getSubscription_offer_data());
                }else
                {
                    addSubscriptionView.showProgressBar(false);
					android.util.Log.d("FAILURE--","sdsdds");
					addSubscriptionView.showMessage(addSubscriptionData.getMessage());
                }
            }

            @Override
            public void OnFailure(String error) {
                addSubscriptionView.showProgressBar(false);
                addSubscriptionView.showMessage(error);

            }
        });

    }
}
