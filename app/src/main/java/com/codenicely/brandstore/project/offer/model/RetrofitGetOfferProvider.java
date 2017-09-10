package com.codenicely.brandstore.project.offer.model;

import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.offer.OnGetOffer;
import com.codenicely.brandstore.project.offer.api.GetOfferApi;
import com.codenicely.brandstore.project.offer.model.data.OfferGetData;
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

public class RetrofitGetOfferProvider implements GetOffer_Provider {

    private GetOfferApi getOfferApi;

    public RetrofitGetOfferProvider() {

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

        getOfferApi = retrofit.create(GetOfferApi.class);
    }

    @Override
    public void getOffer(int offer_id, String access_token, final OnGetOffer onGetOffer) {


        Call<OfferGetData> getOfferDataCall = getOfferApi.getOffer(offer_id, access_token);
        getOfferDataCall.enqueue(new Callback<OfferGetData>() {
            @Override
            public void onResponse(Call<OfferGetData> call, Response<OfferGetData> response) {

                onGetOffer.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<OfferGetData> call, Throwable t) {
                onGetOffer.onFailure();
                t.printStackTrace();
            }
        });
    }
}
