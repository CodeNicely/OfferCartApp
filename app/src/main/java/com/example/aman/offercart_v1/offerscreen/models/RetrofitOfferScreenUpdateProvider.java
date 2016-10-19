package com.example.aman.offercart_v1.offerscreen.models;

import com.example.aman.offercart_v1.helper.Urls;
import com.example.aman.offercart_v1.offerscreen.OfferScreenUpdateDataCallback;
import com.example.aman.offercart_v1.offerscreen.api.OfferScreenResponseApi;
import com.example.aman.offercart_v1.offerscreen.models.data.OfferScreenData;
import com.example.aman.offercart_v1.offerscreen.models.data.OfferScreenUpdateData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aman on 20/10/16.
 */

public class RetrofitOfferScreenUpdateProvider implements OfferScreenUpdateProvider{


    @Override
    public void responseProductList(String offer, final OfferScreenUpdateDataCallback offerScreenUpdateDataCallback) {

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Urls.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            final OfferScreenResponseApi offerScreenResponseApi=retrofit.create(OfferScreenResponseApi.class);

            Call<OfferScreenUpdateData> call= offerScreenResponseApi.sendOfferData(offer);

            call.enqueue(new Callback<OfferScreenUpdateData>() {
                @Override
                public void onResponse(Call<OfferScreenUpdateData> call, Response<OfferScreenUpdateData> response) {
                    offerScreenUpdateDataCallback.onSuccess(response.body());
                }

                @Override
                public void onFailure(Call<OfferScreenUpdateData> call, Throwable t) {

                    t.printStackTrace();
                    offerScreenUpdateDataCallback.onFailure("Error Try Again");

                }
            });


    }
}
