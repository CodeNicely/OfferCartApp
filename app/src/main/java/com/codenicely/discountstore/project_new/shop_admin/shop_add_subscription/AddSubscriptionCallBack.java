package com.codenicely.discountstore.project_new.shop_admin.shop_add_subscription;

import com.codenicely.discountstore.project_new.shop_admin.shop_add_subscription.model.data.AddSubscriptionData;

/**
 * Created by aman on 18/5/17.
 */

public interface AddSubscriptionCallBack {
    void OnSuccess(AddSubscriptionData addSubscriptionData);
    void OnFailure(String error);
}
