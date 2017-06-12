package com.codenicely.brandstore.project.shop_admin.shop_add_subscription.model.data;

/**
 * Created by aman on 18/5/17.
 */

public class AddSubscriptionDetails {

    int subscription_id;
    int subscription_price;
    int subscription_period;
    String subscription_title;
    String subscription_description;

    public AddSubscriptionDetails(int subscription_id, int subscription_price, int subscription_period,
                                  String subscription_title, String subscription_description) {
        this.subscription_id = subscription_id;
        this.subscription_price = subscription_price;
        this.subscription_period = subscription_period;
        this.subscription_title = subscription_title;
        this.subscription_description = subscription_description;
    }

    public int getSubscription_id() {
        return subscription_id;
    }

    public int getSubscription_price() {
        return subscription_price;
    }

    public int getSubscription_period() {
        return subscription_period;
    }

    public String getSubscription_title() {
        return subscription_title;
    }

    public String getSubscription_description() {
        return subscription_description;
    }
}
