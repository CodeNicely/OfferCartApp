package com.example.aman.offercart_v1.offer.model;

import com.example.aman.offercart_v1.helper.Urls;
import com.example.aman.offercart_v1.offer.OnBuyOffer;
import com.example.aman.offercart_v1.offer.api.BuyOfferApi;
import com.example.aman.offercart_v1.offer.model.data.BuyOfferData;
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

public class RetrofitBuyOfferProvider implements BuyOfferProvider {

    private BuyOfferApi buyOfferApi;

    RetrofitBuyOfferProvider() {

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

        Call<BuyOfferData> buyOfferDataCall = buyOfferApi.buyOffer(offer_id, access_token);
        buyOfferDataCall.enqueue(new Callback<BuyOfferData>() {
            @Override
            public void onResponse(Call<BuyOfferData> call, Response<BuyOfferData> response) {

                onBuyOffer.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<BuyOfferData> call, Throwable t) {

                onBuyOffer.onFailure();
                t.printStackTrace();
            }
        });


    }
}
