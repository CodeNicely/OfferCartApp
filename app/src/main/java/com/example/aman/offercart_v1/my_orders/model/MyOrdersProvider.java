package com.example.aman.offercart_v1.my_orders.model;

import com.example.aman.offercart_v1.my_orders.view.OnOrdersReceived;

/**
 * Created by iket on 3/11/16.
 */
public interface MyOrdersProvider {

    void getOrders(String access_token, OnOrdersReceived onOrdersReceived);
}
