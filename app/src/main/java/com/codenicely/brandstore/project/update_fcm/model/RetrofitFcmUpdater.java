package com.codenicely.brandstore.project.update_fcm.model;

import android.util.Log;

import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.update_fcm.FcmUpdateCallback;
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
 * Created by ramya on 7/3/17.
 */

public class RetrofitFcmUpdater implements FcmUpdateHelper {
    private final static String TAG="RetrofitFcmUpdater";
    private RequestFcmUpdateApi requestFcmUpdateApi;
    public RetrofitFcmUpdater() {
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
        requestFcmUpdateApi = retrofit.create(RequestFcmUpdateApi.class);

    }
    @Override
    public void sendFcmToken(String access_token, final String fcm, final FcmUpdateCallback fcmUpdateCallback) {

        Call<FcmUpdateData> call=requestFcmUpdateApi.sendFcmUpdateRequest(access_token,fcm);
        call.enqueue(new Callback<FcmUpdateData>() {
            @Override
            public void onResponse(Call<FcmUpdateData> call, Response<FcmUpdateData> response) {
                fcmUpdateCallback.onSuccess(response.body());
                Log.d("RetrofitFcmUpdater","Fcm Token Updated !");
            }
            @Override
            public void onFailure(Call<FcmUpdateData> call, Throwable t) {
                fcmUpdateCallback.onFailure();
            }
        });
    }
}
