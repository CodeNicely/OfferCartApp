package com.codenicely.discountstore.project_new.shop_admin.shop_add_subscription.model.data;

/**
 * Created by aman on 18/5/17.
 */

public class AddSubscriptionDetails {

    String subscription_title;
    int subscription_price, subscription_id;

    public AddSubscriptionDetails(String subscription_title, int subscription_price, int subscription_id) {
        this.subscription_title = subscription_title;
        this.subscription_price = subscription_price;
        this.subscription_id = subscription_id;
    }

    public String getSubscription_title() {
        return subscription_title;
    }

    public int getSubscription_price() {
        return subscription_price;
    }

    public int getSubscription_id() {
        return subscription_id;
    }
}
