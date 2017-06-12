package com.codenicely.brandstore.project.my_offers.model;

import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.my_offers.api.MyOrdersApi;
import com.codenicely.brandstore.project.my_offers.model.data.OrdersData;
import com.codenicely.brandstore.project.my_offers.view.OnOrdersReceived;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(client)
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
