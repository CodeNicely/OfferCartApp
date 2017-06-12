package com.codenicely.brandstore.project.my_offers.api;

import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.my_offers.model.data.OrdersData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by iket on 3/11/16.
 */
public interface MyOrdersApi {
    @GET(Urls.SUB_URL_MY_ORDERS)
    Call<OrdersData>getOrders(@Query("access_token") String access_token);
}
