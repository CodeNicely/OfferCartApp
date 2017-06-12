package com.codenicely.brandstore.project.shops.model;

import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.shops.api.ShopsApi;
import com.codenicely.brandstore.project.shops.model.data.ShopList;
import com.codenicely.brandstore.project.shops.view.OnShopsReceived;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by iket on 23/10/16.
 */
public class RetrofitShopProvider implements ShopProvider {

    private ShopsApi shopsApi;

    public RetrofitShopProvider() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        shopsApi = retrofit.create(ShopsApi.class);

    }

    @Override
    public void getShops(String access_token, int category_id,Double latitude,Double longitude, final OnShopsReceived onShopsReceived) {
        Call<ShopList> call = shopsApi.getShops(access_token, category_id,latitude,longitude);
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
