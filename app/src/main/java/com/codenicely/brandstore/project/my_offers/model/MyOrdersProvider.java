package com.codenicely.brandstore.project.my_offers.model;

import com.codenicely.brandstore.project.my_offers.view.OnOrdersReceived;

/**
 * Created by iket on 3/11/16.
 */
public interface MyOrdersProvider {

    void getOrders(String access_token, OnOrdersReceived onOrdersReceived);
}
