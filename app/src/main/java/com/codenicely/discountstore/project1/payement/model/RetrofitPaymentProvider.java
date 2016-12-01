package com.codenicely.discountstore.project1.payement.model;

import com.codenicely.discountstore.project1.helper.Urls;
import com.codenicely.discountstore.project1.payement.OnPaymentCallback;
import com.codenicely.discountstore.project1.payement.api.PaymentRequestApi;
import com.codenicely.discountstore.project1.payement.model.data.PaymentData;
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
 * Created by meghal on 1/12/16.
 */

public class RetrofitPaymentProvider implements PaymentProvider {

    private PaymentRequestApi paymentRequestApi;

    public RetrofitPaymentProvider() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        paymentRequestApi = retrofit.create(PaymentRequestApi.class);

    }

    @Override
    public void requestPaymentHash(double amount, String access_token, final OnPaymentCallback onPaymentCallback) {

        Call<PaymentData> paymentDataCall = paymentRequestApi.getPaymentHash(amount, access_token);
        paymentDataCall.enqueue(new Callback<PaymentData>() {
            @Override
            public void onResponse(Call<PaymentData> call, Response<PaymentData> response) {
                onPaymentCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PaymentData> call, Throwable t) {
                onPaymentCallback.onFailure();
                t.printStackTrace();
            }
        });
    }
}
