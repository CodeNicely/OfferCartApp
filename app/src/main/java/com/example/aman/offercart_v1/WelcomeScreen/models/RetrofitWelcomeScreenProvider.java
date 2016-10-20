package com.example.aman.offercart_v1.WelcomeScreen.models;

import android.util.Log;

import com.example.aman.offercart_v1.WelcomeScreen.WelcomeScreenCallback;
import com.example.aman.offercart_v1.WelcomeScreen.api.WelcomeScreenRequestApi;
import com.example.aman.offercart_v1.WelcomeScreen.models.data.WelcomeScreenData;
import com.example.aman.offercart_v1.helper.Urls;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aman on 16/10/16.
 */


public class RetrofitWelcomeScreenProvider implements WelcomeScreenProvider {

    private static final String TAG = "RetrofitWelcomeScreenProvider";

    private WelcomeScreenRequestApi welcomeScreenRequestApi;
    @Override
    public void getWelcomeData(final WelcomeScreenCallback welcomeScreenCallback)
    {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        welcomeScreenRequestApi=retrofit.create(WelcomeScreenRequestApi.class);

        Call<WelcomeScreenData> welcomeScreenDataCall= welcomeScreenRequestApi.getWelcomeData();
        welcomeScreenDataCall.enqueue(new Callback<WelcomeScreenData>() {
            @Override
            public void onResponse(Call<WelcomeScreenData> call, Response<WelcomeScreenData> response) {
                welcomeScreenCallback.onSuccess(response.body());
                Log.d("Response recieved",response.message());
            }

            @Override
            public void onFailure(Call<WelcomeScreenData> call, Throwable t) {
                welcomeScreenCallback.onFailure("Unable to connect to api");
                Log.d("error",t.getMessage());
                t.printStackTrace();
            }
        });

    }
}
