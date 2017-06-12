package com.codenicely.brandstore.project.shop_admin.shop_add_subscription.model;

import android.util.Log;

import com.codenicely.brandstore.project.shop_admin.shop_add_subscription.AddSubscriptionCallBack;
import com.codenicely.brandstore.project.shop_admin.shop_add_subscription.api.AddSubscriptionDataApi;
import com.codenicely.brandstore.project.shop_admin.shop_add_subscription.model.data.AddSubscriptionData;
import com.codenicely.brandstore.project.helper.Urls;
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
 * Created by aman on 18/5/17.
 */

public class RetrofitAddSubscriptionProvider implements AddSubscriptionProvider{



    public AddSubscriptionDataApi addSubscriptionDataApi;

    public RetrofitAddSubscriptionProvider() {
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
        addSubscriptionDataApi = retrofit.create(AddSubscriptionDataApi.class);
    }

    @Override
    public void requestSubscription(String access_token, final AddSubscriptionCallBack addSubscriptionCallBack) {
     Call<AddSubscriptionData> call=addSubscriptionDataApi.getSubscription(access_token);
        Log.d("CALL--","sdsdds");

        call.enqueue(new Callback<AddSubscriptionData>() {
            @Override
            public void onResponse(Call<AddSubscriptionData> call, Response<AddSubscriptionData> response) {
                addSubscriptionCallBack.OnSuccess(response.body());
                Log.d("SUCCESS--","sdsdds");
            }

            @Override
            public void onFailure(Call<AddSubscriptionData> call, Throwable t) {
                  addSubscriptionCallBack.OnFailure("UNAABLE TO CONNECT");
                Log.d("FAILURE--","sdsdds");
                t.printStackTrace();
            }
        });
    }
}
