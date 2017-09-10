package com.codenicely.brandstore.project.offer.model;

import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.offer.OfferScreenDetailsCallback;
import com.codenicely.brandstore.project.offer.api.OfferScreenRequestApi;
import com.codenicely.brandstore.project.offer.model.data.OfferData;
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
 * Created by aman on 19/10/16.
 */

public class RetrofitOfferScreenDetailsProvider implements OfferScreenDetailsProvider {


    private OfferScreenRequestApi offerScreenRequestApi;

    public RetrofitOfferScreenDetailsProvider() {

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
        offerScreenRequestApi = retrofit.create(OfferScreenRequestApi.class);


    }

    @Override
    public void requestOfferList(String accessToken, int shop_id, final OfferScreenDetailsCallback offerScreenDetailsCallback) {


        final Call<OfferData> offerScreenDataCall = offerScreenRequestApi.getCategoryListData(accessToken, shop_id);

        offerScreenDataCall.enqueue(new Callback<OfferData>() {
            @Override
            public void onResponse(Call<OfferData> call, Response<OfferData> response) {

                offerScreenDetailsCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<OfferData> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }

}
