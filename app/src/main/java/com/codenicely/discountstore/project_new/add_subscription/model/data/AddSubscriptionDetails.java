package com.codenicely.discountstore.project_new.add_subscription.model.data;

/**
 * Created by aman on 18/5/17.
 */

public class AddSubscriptionDetails {

String subscription_validity;
    int subscription_price,subscription_offer_id;

    public AddSubscriptionDetails(int subscription_offer_id, int subscription_price, String subscription_validity) {
        this.subscription_offer_id = subscription_offer_id;
        this.subscription_price = subscription_price;
        this.subscription_validity = subscription_validity;
    }


    public int getSubscription_offer_id() {
        return subscription_offer_id;
    }

    public int getSubscription_price() {
        return subscription_price;
    }

    public String getSubscription_validity() {
        return subscription_validity;
    }
}
