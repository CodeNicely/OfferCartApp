package com.example.aman.offercart_v1.my_orders.view;

import com.example.aman.offercart_v1.my_orders.model.data.OrdersData;
import com.example.aman.offercart_v1.offer.model.data.OfferScreenList;

/**
 * Created by iket on 3/11/16.
 */
public interface MyOrdersInterface {
    void showMessage(String error);

    void showProgressBar(boolean show);

    void onOfferReceived(OrdersData ordersData);

}

