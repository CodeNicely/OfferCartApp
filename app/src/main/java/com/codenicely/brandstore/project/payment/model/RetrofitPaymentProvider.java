package com.codenicely.brandstore.project.payment.model;

import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.payment.OnPaymentCallback;
import com.codenicely.brandstore.project.payment.OnPaymentUpdateCallback;
import com.codenicely.brandstore.project.payment.api.PaymentRequestApi;
import com.codenicely.brandstore.project.payment.api.UpdatePaymentStatusApi;
import com.codenicely.brandstore.project.payment.model.data.PaymentData;
import com.codenicely.brandstore.project.payment.model.data.UpdatePaymentStatusData;
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

    private Retrofit retrofit;

    public RetrofitPaymentProvider() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


    }

    @Override
    public void requestPaymentHash(double amount, String access_token, final OnPaymentCallback onPaymentCallback) {

        PaymentRequestApi paymentRequestApi = retrofit.create(PaymentRequestApi.class);

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

    @Override
    public void updatePaymentStatus(String access_token, String transaction_id, final OnPaymentUpdateCallback onPaymentUpdateCallback) {
        UpdatePaymentStatusApi updatePaymentStatusApi = retrofit.create(UpdatePaymentStatusApi.class);
        Call<UpdatePaymentStatusData> updatePaymentStatusDataCall=updatePaymentStatusApi.
                updatePaymentStatusDataCall(access_token,transaction_id);

        updatePaymentStatusDataCall.enqueue(new Callback<UpdatePaymentStatusData>() {
            @Override
            public void onResponse(Call<UpdatePaymentStatusData> call, Response<UpdatePaymentStatusData> response) {

                onPaymentUpdateCallback.onSuccess(response.body());

            }
            @Override
            public void onFailure(Call<UpdatePaymentStatusData> call, Throwable t) {

                t.printStackTrace();
                onPaymentUpdateCallback.onFailure();

            }
        });
    }


}
