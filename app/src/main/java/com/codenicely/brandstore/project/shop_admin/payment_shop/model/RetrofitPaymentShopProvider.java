package com.codenicely.brandstore.project.shop_admin.payment_shop.model;

import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.shop_admin.payment_shop.PaymentShopCallBack;
import com.codenicely.brandstore.project.shop_admin.payment_shop.UpdateShopPaymentCallBack;
import com.codenicely.brandstore.project.shop_admin.payment_shop.api.PaymentApi;
import com.codenicely.brandstore.project.shop_admin.payment_shop.api.PaymentUpdateApi;
import com.codenicely.brandstore.project.shop_admin.payment_shop.model.data.ShopPaymentData;
import com.codenicely.brandstore.project.shop_admin.payment_shop.model.data.UpdateShopPaymentData;
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
 * Created by aman on 19/5/17.
 */

public class RetrofitPaymentShopProvider implements PaymentShopProvider {

    PaymentApi paymentApi;
    PaymentUpdateApi paymentUpdateApi;

    public RetrofitPaymentShopProvider() {


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        paymentApi = retrofit.create(PaymentApi.class);
        paymentUpdateApi=retrofit.create(PaymentUpdateApi.class);

    }

    @Override
    public void requestShopPaymentHash(int id, String access_token, final PaymentShopCallBack paymentShopCallBack) {
        Call<ShopPaymentData>call=paymentApi.getPaymentHash(id,access_token);
        call.enqueue(new Callback<ShopPaymentData>() {
            @Override
            public void onResponse(Call<ShopPaymentData> call, Response<ShopPaymentData> response) {
                paymentShopCallBack.OnSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ShopPaymentData> call, Throwable t) {
                     paymentShopCallBack.OnFailure("UNABLE TO CONNECT");
                t.printStackTrace();
            }
        });


    }

    @Override
    public void updateShopPaymentStatus(String access_token, String transaction_id, Boolean success,final UpdateShopPaymentCallBack updateShopPaymentCallBack) {

        Call<UpdateShopPaymentData>call=paymentUpdateApi.updateShopPaymentStatusDataCall(access_token,transaction_id,success);
        call.enqueue(new Callback<UpdateShopPaymentData>() {
            @Override
            public void onResponse(Call<UpdateShopPaymentData> call, Response<UpdateShopPaymentData> response) {

                updateShopPaymentCallBack.OnSuccess(response.body());


            }

            @Override
            public void onFailure(Call<UpdateShopPaymentData> call, Throwable t) {


                updateShopPaymentCallBack.OnFailure("UNABLE TO CONNECT");
                t.printStackTrace();

            }
        });


    }
}
