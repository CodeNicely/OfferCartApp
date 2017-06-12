package com.codenicely.brandstore.project.shop_admin.shop_add_subscription.model;

import com.codenicely.brandstore.project.shop_admin.shop_add_subscription.AddSubscriptionCallBack;

/**
 * Created by aman on 18/5/17.
 */

public interface AddSubscriptionProvider {


    void requestSubscription(String access_token, AddSubscriptionCallBack addSubscriptionCallBack);
}
