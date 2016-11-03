package com.example.aman.offercart_v1.my_orders.view;

import com.example.aman.offercart_v1.my_orders.model.data.OrdersData;

/**
 * Created by iket on 3/11/16.
 */
public interface OnOrdersReceived {
    void onSuccess(OrdersData ordersData);
    void onFailure();

}
