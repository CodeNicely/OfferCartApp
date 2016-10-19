package com.example.aman.offercart_v1.cityScreen.models;

import com.example.aman.offercart_v1.cityScreen.api.CityScreenRequestApi;
import com.example.aman.offercart_v1.cityScreen.view.OnCitiesReceived;
import com.example.aman.offercart_v1.cityScreen.view.Response;
import com.example.aman.offercart_v1.helper.Urls;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitCityScreenProvider implements CityScreenProvider {
   private CityScreenRequestApi cityScreenRequestApi;

    public RetrofitCityScreenProvider()
    {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        cityScreenRequestApi = retrofit.create(CityScreenRequestApi.class);
    }


    @Override
    public void requestCity(final OnCitiesReceived onCitiesReceived) {


        Call<Response> call=cityScreenRequestApi.getCities();
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                onCitiesReceived.onSuccess(response.body().getCity_data());
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                t.printStackTrace();
                onCitiesReceived.onFailure();

            }
        });




    }
}
