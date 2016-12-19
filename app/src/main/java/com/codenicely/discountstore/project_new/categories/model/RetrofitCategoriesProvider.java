package com.codenicely.discountstore.project_new.categories.model;

import com.codenicely.discountstore.project_new.categories.api.Request_Categories;
import com.codenicely.discountstore.project_new.categories.model.data.CategoriesList;
import com.codenicely.discountstore.project_new.categories.view.OnCategoriesReceived;
import com.codenicely.discountstore.project_new.helper.Urls;
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
 * Created by iket on 19/10/16.
 */
public class RetrofitCategoriesProvider implements CategoriesProvider {
    private Request_Categories request_categories;

    public RetrofitCategoriesProvider() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .build();


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        request_categories = retrofit.create(Request_Categories.class);

    }

    @Override
    public void getCategories(String access_token, final OnCategoriesReceived onCategoriesReceived) {
        Call<CategoriesList> call = request_categories.getCategories(access_token);
        call.enqueue(new Callback<CategoriesList>() {
            @Override
            public void onResponse(Call<CategoriesList> call, Response<CategoriesList> response) {
                onCategoriesReceived.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<CategoriesList> call, Throwable t) {
                onCategoriesReceived.onFailure();
                t.printStackTrace();
            }
        });

    }
}
