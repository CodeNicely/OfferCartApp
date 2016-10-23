package com.example.aman.offercart_v1.splash_screen.models;


import android.util.Log;

import com.example.aman.offercart_v1.splash_screen.SplashScreenCallback;
import com.example.aman.offercart_v1.splash_screen.api.SplashScreenRequestApi;
import com.example.aman.offercart_v1.splash_screen.models.data.SplashScreenData;
import com.example.aman.offercart_v1.helper.Urls;

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
 * Created by aman on 13/10/16.
 */
public class RetrofitSplashScreenProvider implements SplashScreenProvider {

    private static final String TAG = "RetrofitSplashScreen" ;
    private SplashScreenRequestApi splashScreenRequestApi;
    private static final String LOG_TAG = "SplashScreenActivity";

    @Override
    public void requestSplash(final SplashScreenCallback splashScreenCallback)
    {

/*
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
*/        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
         //       .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        splashScreenRequestApi = retrofit.create(SplashScreenRequestApi.class);
        Call<SplashScreenData> call = splashScreenRequestApi.requestSplash();

        call.enqueue(new Callback<SplashScreenData>()
        {

            @Override
            public void onResponse(Call<SplashScreenData> call, Response<SplashScreenData> response)
            {

               // if(response.body().isSuccess()) {
//                    Log.d(TAG,response.body().toString());
                    splashScreenCallback.onSuccess(response.body());
              //  }

            }

            @Override
            public void onFailure(Call<SplashScreenData> call, Throwable t)
            {
                t.printStackTrace();
                splashScreenCallback.onFailure("Unable to connect to api");

            }
        });



    }
}

/*public RetrofitSplashScreenProvider() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        splashScreenRequestApi = retrofit.create(SplashScreenRequestApi.class);

    }*/