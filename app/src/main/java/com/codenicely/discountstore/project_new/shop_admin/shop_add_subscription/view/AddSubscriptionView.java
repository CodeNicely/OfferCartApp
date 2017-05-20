package com.codenicely.discountstore.project_new.shop_admin.shop_add_subscription.view;

import com.codenicely.discountstore.project_new.shop_admin.shop_add_subscription.model.data.AddSubscriptionData;

/**
 * Created by aman on 18/5/17.
 */

public interface AddSubscriptionView {


    void showProgressBar(boolean show);
    void showMessage(String error);
    void setData(AddSubscriptionData addSubscriptionData);
}
