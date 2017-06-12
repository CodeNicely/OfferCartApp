package com.codenicely.brandstore.project.my_offers.model;

import com.codenicely.brandstore.project.my_offers.model.data.OrderDetails;
import com.codenicely.brandstore.project.my_offers.model.data.OrdersData;
import com.codenicely.brandstore.project.my_offers.view.OnOrdersReceived;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iket on 3/11/16.
 */
public class MockDataProvider implements MyOrdersProvider{
    @Override
    public void getOrders(String access_token, OnOrdersReceived onOrdersReceived) {
        List<OrderDetails> ordersDatas=new ArrayList<>();
        OrderDetails orderDetails=new OrderDetails("","","","Diwali Special","Opposite Sbi Bank,Pandri,Raipur","Dominos","1st December");
        ordersDatas.add(orderDetails);

        orderDetails=new OrderDetails("","","","Big Million Sale","Near Tellibanda Lake,Raipur","Boys Zone","21st December");
        ordersDatas.add(orderDetails);


        OrdersData ordersData=new OrdersData(ordersDatas,"",true);
        onOrdersReceived.onSuccess(ordersData);
        onOrdersReceived.onFailure();




    }
}
