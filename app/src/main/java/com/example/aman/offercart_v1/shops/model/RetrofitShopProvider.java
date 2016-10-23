package com.example.aman.offercart_v1.shops.model;

import com.example.aman.offercart_v1.helper.Urls;
import com.example.aman.offercart_v1.shops.api.ShopsApi;
import com.example.aman.offercart_v1.shops.model.data.ShopList;
import com.example.aman.offercart_v1.shops.view.OnShopsReceived;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by iket on 23/10/16.
 */
public class RetrofitShopProvider implements ShopProvider {
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Urls.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    final ShopsApi shopsApi=retrofit.create(ShopsApi.class);

    @Override
    public void getShops(String category_id, final OnShopsReceived onShopsReceived) {
        Call<ShopList> call=shopsApi.getShops(category_id);
        call.enqueue(new Callback<ShopList>() {
            @Override
            public void onResponse(Call<ShopList> call, Response<ShopList> response) {
                onShopsReceived.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ShopList> call, Throwable t) {
                onShopsReceived.onFailure();

            }
        });
    }
}
