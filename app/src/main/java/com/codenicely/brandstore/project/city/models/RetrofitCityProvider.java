package com.codenicely.brandstore.project.city.models;

import com.codenicely.brandstore.project.city.api.CityRequestApi;
import com.codenicely.brandstore.project.city.api.SendSelectedCityApi;
import com.codenicely.brandstore.project.city.data.CityData;
import com.codenicely.brandstore.project.city.data.SelectedCityData;
import com.codenicely.brandstore.project.city.OnCitiesReceived;
import com.codenicely.brandstore.project.city.OnCitiesSent;
import com.codenicely.brandstore.project.helper.Urls;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitCityProvider implements CityProvider {
    private CityRequestApi cityRequestApi;
    private SendSelectedCityApi sendSelectedCityApi;

    public RetrofitCityProvider() {

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
        cityRequestApi = retrofit.create(CityRequestApi.class);
        sendSelectedCityApi = retrofit.create(SendSelectedCityApi.class);
    }


    @Override
    public void requestCity(String access_token, int state_id, final OnCitiesReceived onCitiesReceived) {


        Call<CityData> call = cityRequestApi.getCities(access_token,state_id);
        call.enqueue(new Callback<CityData>() {
            @Override
            public void onResponse(Call<CityData> call, retrofit2.Response<CityData> response) {
                onCitiesReceived.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<CityData> call, Throwable t) {
                t.printStackTrace();
                onCitiesReceived.onFailure();

            }
        });
    }

    @Override
    public void sendSelectedCity(int city_id, String token,final OnCitiesSent onCitiesSent) {
        Call<SelectedCityData> call = sendSelectedCityApi.sendCity(city_id, token);
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
