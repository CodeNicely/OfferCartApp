package com.codenicely.discountstore.project1.my_orders.model;

import com.codenicely.discountstore.project1.helper.Urls;
import com.codenicely.discountstore.project1.my_orders.api.MyOrdersApi;
import com.codenicely.discountstore.project1.my_orders.model.data.OrdersData;
import com.codenicely.discountstore.project1.my_orders.view.OnOrdersReceived;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by iket on 3/11/16.
 */
public class RetrofitOrdersProvider implements  MyOrdersProvider {

    private MyOrdersApi myOrdersApi;

    public RetrofitOrdersProvider() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        myOrdersApi=retrofit.create(MyOrdersApi.class);
    }

    @Override
    public void getOrders(String access_token, final OnOrdersReceived onOrdersReceived) {
        Call<OrdersData> call=myOrdersApi.getOrders(access_token);
        call.enqueue(new Callback<OrdersData>() {
            @Override
            public void onResponse(Call<OrdersData> call, Response<OrdersData> response) {
                onOrdersReceived.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<OrdersData> call, Throwable t) {
                onOrdersReceived.onFailure();
                t.printStackTrace();
            }
        });
    }
}
