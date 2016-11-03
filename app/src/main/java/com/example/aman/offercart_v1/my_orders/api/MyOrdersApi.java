package com.example.aman.offercart_v1.my_orders.api;

import com.example.aman.offercart_v1.helper.Urls;
import com.example.aman.offercart_v1.my_orders.model.data.OrdersData;
import com.example.aman.offercart_v1.offer.model.data.OfferScreenList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by iket on 3/11/16.
 */
public interface MyOrdersApi {
    @GET(Urls.REQUEST_ORDERS)
    Call<OrdersData>getOrders(@Query("access_token") String access_token);
}
