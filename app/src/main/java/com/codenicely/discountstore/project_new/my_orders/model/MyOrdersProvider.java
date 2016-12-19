package com.codenicely.discountstore.project_new.my_orders.model;

import com.codenicely.discountstore.project_new.my_orders.view.OnOrdersReceived;

/**
 * Created by iket on 3/11/16.
 */
public interface MyOrdersProvider {

    void getOrders(String access_token, OnOrdersReceived onOrdersReceived);
}
