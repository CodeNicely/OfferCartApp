package com.codenicely.discountstore.project_new.add_subscription.view;

import com.codenicely.discountstore.project_new.add_subscription.model.data.AddSubscriptionData;
import com.codenicely.discountstore.project_new.shop_offerlist.model.data.ShopOfferListData;

/**
 * Created by aman on 18/5/17.
 */

public interface AddSubscriptionView {


    void showProgressBar(boolean show);
    void showMessage(String error);
    void setData(AddSubscriptionData addSubscriptionData);
}
