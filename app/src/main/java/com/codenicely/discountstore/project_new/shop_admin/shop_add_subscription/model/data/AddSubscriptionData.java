package com.codenicely.discountstore.project_new.shop_admin.shop_add_subscription.model.data;

import java.util.List;

/**
 * Created by aman on 18/5/17.
 */

public class AddSubscriptionData {
    boolean success;
    String message;
    List<AddSubscriptionDetails> subscription_list;

    public AddSubscriptionData(boolean success, String message, List<AddSubscriptionDetails> subscription_offer_data) {
        this.success = success;
        this.message = message;
        this.subscription_list = subscription_offer_data;
    }


    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<AddSubscriptionDetails> getSubscription_offer_data() {
        return subscription_list;
    }
}
