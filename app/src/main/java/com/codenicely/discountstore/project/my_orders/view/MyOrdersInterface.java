package com.codenicely.discountstore.project.my_orders.view;

import com.codenicely.discountstore.project.my_orders.model.data.OrdersData;

/**
 * Created by iket on 3/11/16.
 */
public interface MyOrdersInterface {
    void showMessage(String error);

    void showProgressBar(boolean show);

    void onOfferReceived(OrdersData ordersData);

}

