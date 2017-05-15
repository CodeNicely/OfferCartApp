package com.codenicely.discountstore.project_new.shop_offer_list.model;

import com.codenicely.discountstore.project_new.helper.Urls;
import com.codenicely.discountstore.project_new.shop_offer_list.OnBuyOffer;
import com.codenicely.discountstore.project_new.shop_offer_list.api.BuyOfferApi;
import com.codenicely.discountstore.project_new.shop_offer_list.model.data.OfferData;
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
 * Created by meghal on 2/11/16.
 */

public class RetrofitBuyOfferProvider implements BuyOffer_Provider {

    private BuyOfferApi buyOfferApi;

    public RetrofitBuyOfferProvider() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).
                connectTimeout(5, TimeUnit.MINUTES).readTimeout(5, TimeUnit.MINUTES).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        buyOfferApi = retrofit.create(BuyOfferApi.class);
    }

    @Override
    public void buyOffer(int offer_id, String access_token, final OnBuyOffer onBuyOffer) {

//        Call<OfferData> call=buyOfferApi

        Call<OfferData> buyOfferDataCall = buyOfferApi.buyOffer(offer_id, access_token);
        buyOfferDataCall.enqueue(new Callback<OfferData>() {
            @Override
            public void onResponse(Call<OfferData> call, Response<OfferData> response) {

                onBuyOffer.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<OfferData> call, Throwable t) {
                onBuyOffer.onFailure();
                t.printStackTrace();
            }
        });
    }
}
