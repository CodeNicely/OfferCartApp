package com.codenicely.brandstore.project.shop_admin.shop_add_subscription.view;

import com.codenicely.brandstore.project.shop_admin.shop_add_subscription.model.data.AddSubscriptionDetails;

import java.util.List;

/**
 * Created by aman on 18/5/17.
 */

public interface AddSubscriptionView {


    void showProgressBar(boolean show);
    void showMessage(String error);
    void setData(List<AddSubscriptionDetails> addSubscriptionDetailsList);
}
