package com.example.aman.offercart_v1.home.model;

import com.example.aman.offercart_v1.cityScreen.api.CityScreenRequestApi;
import com.example.aman.offercart_v1.cityScreen.api.SendSelectedCityApi;
import com.example.aman.offercart_v1.cityScreen.models.RetrofitCityScreenProvider;
import com.example.aman.offercart_v1.helper.Urls;
import com.example.aman.offercart_v1.home.api.Request_Categories;
import com.example.aman.offercart_v1.home.view.CategoriesList;
import com.example.aman.offercart_v1.home.view.OnCategoriesReceived;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by iket on 19/10/16.
 */
public class RetrofitCategoriesProvider implements CategoriesProvider {
    private Request_Categories  request_categories;

    public RetrofitCategoriesProvider()
    {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        request_categories=retrofit.create(Request_Categories.class);

    }

    @Override
    public void getCategories(OnCategoriesReceived onCategoriesReceived) {
        Call<CategoriesList> call=request_categories.getCategories();
        call.enqueue(new Callback<CategoriesList>() {
            @Override
            public void onResponse(Call<CategoriesList> call, Response<CategoriesList> response) {
                response.body().getCategoryDatas();
            }

            @Override
            public void onFailure(Call<CategoriesList> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}