package com.codenicely.brandstore.project.my_offers.view;

import com.codenicely.brandstore.project.my_offers.model.data.OrdersData;

/**
 * Created by iket on 3/11/16.
 */
public interface MyOrdersInterface {
    void showMessage(String error);

    void showProgressBar(boolean show);

    void onOfferReceived(OrdersData ordersData);

}

