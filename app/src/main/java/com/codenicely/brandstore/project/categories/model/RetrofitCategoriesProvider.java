package com.codenicely.brandstore.project.categories.model;

import android.util.Log;

import com.codenicely.brandstore.project.categories.api.Request_Categories;
import com.codenicely.brandstore.project.categories.model.data.CategoriesList;
import com.codenicely.brandstore.project.categories.view.OnCategoriesReceived;
import com.codenicely.brandstore.project.helper.MyApplication;
import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.update_fcm.api.RequestFcmUpdateApi;
import com.codenicely.brandstore.project.update_fcm.model.data.FcmUpdateData;
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
    private Retrofit retrofit;

    public RetrofitCategoriesProvider() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .build();


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

    }

    @Override
    public void getCategories(String access_token, final OnCategoriesReceived onCategoriesReceived) {

        Request_Categories request_categories = retrofit.create(Request_Categories.class);

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


        RequestFcmUpdateApi requestFcmUpdateApi = retrofit.create(RequestFcmUpdateApi.class);

        if (MyApplication.getFcm() != null) {
            Call<FcmUpdateData> call1 = requestFcmUpdateApi.sendFcmUpdateRequest(access_token, MyApplication.getFcm());

            call1.enqueue(new Callback<FcmUpdateData>() {
                @Override
                public void onResponse(Call<FcmUpdateData> call, Response<FcmUpdateData> response) {

                    Log.d("RetCategoriesProvider", "Fcm Token Updated");
                }

                @Override
                public void onFailure(Call<FcmUpdateData> call, Throwable t) {

                    t.printStackTrace();

                }
            });

        }
    }
}
