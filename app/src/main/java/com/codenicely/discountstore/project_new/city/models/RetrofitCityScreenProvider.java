package com.codenicely.discountstore.project_new.city.models;

import com.codenicely.discountstore.project_new.city.api.CityScreenRequestApi;
import com.codenicely.discountstore.project_new.city.api.SendSelectedCityApi;
import com.codenicely.discountstore.project_new.city.models.data.SelectedCityData;
import com.codenicely.discountstore.project_new.city.view.OnCitiesReceived;
import com.codenicely.discountstore.project_new.city.view.OnCitiesSent;
import com.codenicely.discountstore.project_new.city.view.Response;
import com.codenicely.discountstore.project_new.helper.Urls;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitCityScreenProvider implements CityScreenProvider {
    private CityScreenRequestApi cityScreenRequestApi;
    private SendSelectedCityApi sendSelectedCityApi;

    public RetrofitCityScreenProvider() {

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
        cityScreenRequestApi = retrofit.create(CityScreenRequestApi.class);
        sendSelectedCityApi = retrofit.create(SendSelectedCityApi.class);
    }


    @Override
    public void requestCity(String token, final OnCitiesReceived onCitiesReceived) {


        Call<Response> call = cityScreenRequestApi.getCities(token);
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

    @Override
    public void sendSelectedCity(int city_id, String token, String fcm, final OnCitiesSent onCitiesSent) {
        Call<SelectedCityData> call = sendSelectedCityApi.sendCity(city_id, token,fcm);
        call.enqueue(new Callback<SelectedCityData>() {
            @Override
            public void onResponse(Call<SelectedCityData> call, retrofit2.Response<SelectedCityData> response) {
                onCitiesSent.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SelectedCityData> call, Throwable t) {
                onCitiesSent.onFailure();
                t.printStackTrace();

            }
        });

    }
}
