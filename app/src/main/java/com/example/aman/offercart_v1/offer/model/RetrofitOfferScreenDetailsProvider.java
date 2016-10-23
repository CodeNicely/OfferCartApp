package com.example.aman.offercart_v1.offer.model;

import com.example.aman.offercart_v1.helper.Urls;
import com.example.aman.offercart_v1.offer.OfferScreenDetailsCallback;
import com.example.aman.offercart_v1.offer.api.OfferScreenRequestApi;
import com.example.aman.offercart_v1.offer.model.data.OfferScreenList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aman on 19/10/16.
 */

public class RetrofitOfferScreenDetailsProvider implements OfferScreenDetailsProvider {


    @Override
    public void requestOfferList(final OfferScreenDetailsCallback offerScreenDetailsCallback) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        final OfferScreenRequestApi offerScreenRequestApi=retrofit.create(OfferScreenRequestApi.class);

        final Call<OfferScreenList> offerScreenDataCall=offerScreenRequestApi.getCategoryListData();

            offerScreenDataCall.enqueue(new Callback<OfferScreenList>() {
                @Override
                public void onResponse(Call<OfferScreenList> call, Response<OfferScreenList> response) {

                    offerScreenDetailsCallback.onSuccess(response.body());
                }

                @Override
                public void onFailure(Call<OfferScreenList> call, Throwable t) {
                    t.printStackTrace();

                }
            });


    }
}
