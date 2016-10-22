package com.example.aman.offercart_v1.login.view.models;



import com.example.aman.offercart_v1.helper.Urls;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.aman.offercart_v1.login.view.Api.LoginApi;
import  com.example.aman.offercart_v1.login.view.LoginCallback;
import com.example.aman.offercart_v1.login.view.models.data.LoginData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class RetrofitLoginScreenProvider implements LoginProvider{

    private LoginApi loginApi;
    public RetrofitLoginScreenProvider() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson=new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        loginApi  = retrofit.create( LoginApi.class);

    }



public void requestLogin(String name, String mobile, String email, final LoginCallback loginCallback) {

        Call<LoginData> loginDataCall= loginApi.requestLogin(name, mobile, email);

        loginDataCall.enqueue(new Callback<LoginData>() {


       @Override
            public void onResponse(Call<LoginData> call, Response<LoginData> response) {


        loginCallback.onSuccess(response.body());

        }
            @Override
public void onFailure(Call<LoginData> call, Throwable t) {


        t.printStackTrace();
        }
        });
        }
        }

