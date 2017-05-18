package com.codenicely.discountstore.project_new.add_subscription.model;

import com.codenicely.discountstore.project_new.add_subscription.AddSubscriptionCallBack;

/**
 * Created by aman on 18/5/17.
 */

public interface AddSubscriptionProvider {


    void requestSubscription(String access_token, AddSubscriptionCallBack addSubscriptionCallBack);
}
