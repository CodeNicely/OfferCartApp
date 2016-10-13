package com.example.aman.offercart_v1.SplashScreen.models;

import com.example.aman.offercart_v1.SplashScreen.SplashScreenCallback;
import com.example.aman.offercart_v1.SplashScreen.api.SplashScreenRequestApi;
import com.example.aman.offercart_v1.SplashScreen.models.data.SplashScreenData;
import com.example.aman.offercart_v1.helper.Urls;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aman on 13/10/16.
 */
public class RetrofitSplashScreenProvider implements SplashScreenProvider {

    private SplashScreenRequestApi splashScreenRequestApi;

    public RetrofitSplashScreenProvider() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        splashScreenRequestApi = retrofit.create(SplashScreenRequestApi.class);

    }
    @Override
    public void requestSplash(int version, String message, boolean success, int compulsory_update,
                              final SplashScreenCallback splashScreenCallback)
    {
        Call<SplashScreenData> splashScreenDataCall= splashScreenRequestApi.requestSplash
                (version,success,message,compulsory_update);

        splashScreenDataCall.enqueue(new Callback<SplashScreenData>()
        {
            @Override
            public void onResponse(Call<SplashScreenData> call, Response<SplashScreenData> response)
            {


                splashScreenCallback.onSuccess(response.body());

            }
            @Override
            public void onFailure(Call<SplashScreenData> call, Throwable t) {


                t.printStackTrace();
            }
        });







    }
}
