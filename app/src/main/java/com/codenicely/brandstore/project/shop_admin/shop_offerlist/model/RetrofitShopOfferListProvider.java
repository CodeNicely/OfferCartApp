package com.codenicely.brandstore.project.shop_admin.shop_offerlist.model;

import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.shop_admin.shop_offerlist.ShopOfferCallBack;
import com.codenicely.brandstore.project.shop_admin.shop_offerlist.ShopOfferDeleteCallBack;
import com.codenicely.brandstore.project.shop_admin.shop_offerlist.api.ShopOfferDeleteApi;
import com.codenicely.brandstore.project.shop_admin.shop_offerlist.api.ShopOfferListApi;
import com.codenicely.brandstore.project.shop_admin.shop_offerlist.model.data.ShopOfferDeleteData;
import com.codenicely.brandstore.project.shop_admin.shop_offerlist.model.data.ShopOfferListData;
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
 * Created by aman on 16/5/17.
 */

public class RetrofitShopOfferListProvider implements ShopOfferListProvider {
    private ShopOfferListApi shopOfferListApi;
    private Retrofit retrofit;
    private ShopOfferDeleteApi shopOfferDeleteApi;

    @Override
    public void requestShopOffer(String access_token, final  ShopOfferCallBack shopOfferCallBack) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client= new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson= new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        shopOfferListApi= retrofit.create(ShopOfferListApi.class);
        Call<ShopOfferListData>call=shopOfferListApi.getShopData(access_token);
        call.enqueue(new Callback<ShopOfferListData>() {
            @Override
            public void onResponse(Call<ShopOfferListData> call, Response<ShopOfferListData> response) {
                shopOfferCallBack.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<ShopOfferListData> call, Throwable t) {
                       t.printStackTrace();
                shopOfferCallBack.onFailure("Unable To Connect");
            }

        });
    }

    @Override
    public void delete(String access_token, int offer_id, final ShopOfferDeleteCallBack shopOfferDeleteCallBack) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client= new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson= new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        shopOfferDeleteApi=retrofit.create(ShopOfferDeleteApi.class);
        Call<ShopOfferDeleteData>call=shopOfferDeleteApi.getShopDelete(access_token,offer_id);
        call.enqueue(new Callback<ShopOfferDeleteData>() {
            @Override
            public void onResponse(Call<ShopOfferDeleteData> call, Response<ShopOfferDeleteData> response) {
                shopOfferDeleteCallBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ShopOfferDeleteData> call, Throwable t) {
                t.printStackTrace();
                shopOfferDeleteCallBack.onFailure("Unable To Connect");

            }
        });
    }


}
