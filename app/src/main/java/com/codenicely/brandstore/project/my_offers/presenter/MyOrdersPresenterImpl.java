package com.codenicely.brandstore.project.my_offers.presenter;

import com.codenicely.brandstore.project.my_offers.model.MyOrdersProvider;
import com.codenicely.brandstore.project.my_offers.model.data.OrdersData;
import com.codenicely.brandstore.project.my_offers.view.MyOrdersInterface;
import com.codenicely.brandstore.project.my_offers.view.OnOrdersReceived;

/**
 * Created by iket on 3/11/16.
 */
public class MyOrdersPresenterImpl implements MyOrdersPresenter {


    private MyOrdersInterface myOrdersInterface;
    private MyOrdersProvider myOrdersProvider;

    public MyOrdersPresenterImpl(MyOrdersInterface myOrdersInterface, MyOrdersProvider myOrdersProvider) {
        this.myOrdersInterface = myOrdersInterface;
        this.myOrdersProvider = myOrdersProvider;
    }

    @Override
    public void getOrders(String access_token) {
        myOrdersInterface.showProgressBar(true);
        myOrdersProvider.getOrders(access_token, new OnOrdersReceived() {
            @Override
            public void onSuccess(OrdersData ordersData) {
                if(ordersData.isSuccess())
                {
                    myOrdersInterface.showProgressBar(false);
                    myOrdersInterface.onOfferReceived(ordersData);
                }
                else
                {
                    myOrdersInterface.showProgressBar(false);
                    myOrdersInterface.showMessage(ordersData.getMessage());
                }
            }

            @Override
            public void onFailure() {
                myOrdersInterface.showProgressBar(false);
                myOrdersInterface.showMessage("No Internet Connection");
            }
        });

    }
}
