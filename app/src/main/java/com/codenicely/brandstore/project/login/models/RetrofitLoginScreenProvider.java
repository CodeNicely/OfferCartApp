package com.codenicely.brandstore.project.login.models;


import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.login.LoginCallback;
import com.codenicely.brandstore.project.login.api.LoginApi;
import com.codenicely.brandstore.project.login.models.data.LoginData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitLoginScreenProvider implements LoginProvider {

    private LoginApi loginApi;

    public RetrofitLoginScreenProvider() {
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
        loginApi = retrofit.create(LoginApi.class);

    }


    public void requestLogin(String name, String mobile, final LoginCallback loginCallback) {

        Call<LoginData> loginDataCall = loginApi.requestLogin(name, mobile);

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

