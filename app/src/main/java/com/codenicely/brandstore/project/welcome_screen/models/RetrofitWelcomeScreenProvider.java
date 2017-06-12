package com.codenicely.brandstore.project.welcome_screen.models;

import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.welcome_screen.WelcomeScreenCallback;
import com.codenicely.brandstore.project.welcome_screen.api.WelcomeScreenRequestApi;
import com.codenicely.brandstore.project.welcome_screen.models.data.WelcomeScreenData;
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
    private Retrofit retrofit;

    public RetrofitWelcomeScreenProvider() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


    }

    @Override
    public void getWelcomeData(final WelcomeScreenCallback welcomeScreenCallback) {
        welcomeScreenRequestApi = retrofit.create(WelcomeScreenRequestApi.class);

        Call<WelcomeScreenData> welcomeScreenDataCall = welcomeScreenRequestApi.getWelcomeData();
        welcomeScreenDataCall.enqueue(new Callback<WelcomeScreenData>() {
            @Override
            public void onResponse(Call<WelcomeScreenData> call, Response<WelcomeScreenData> response) {

                welcomeScreenCallback.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<WelcomeScreenData> call, Throwable t) {

                t.printStackTrace();
                welcomeScreenCallback.onFailure("Unable to connect to api");
            }
        });

    }
}
