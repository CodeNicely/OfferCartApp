package com.example.aman.offercart_v1.Otp.models;



import com.example.aman.offercart_v1.Otp.api.OtpApi;
import com.example.aman.offercart_v1.Otp.models.data.OtpData;
import com.example.aman.offercart_v1.helper.Urls;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.aman.offercart_v1.Otp.OtpCallback;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * Created by aman on 16/10/16.
 */
public class RetrofitOtpProvider implements OtpProvider{

    OtpApi otpApi;



    void RetrofitLoginScreenProvider(){
        Gson gson=new GsonBuilder()
                .setLenient()
                .create();
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(Urls.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    otpApi  = retrofit.create( OtpApi.class);

}
    public void requestOtp(String otp ,String mobile,final OtpCallback otpCallback) {

        Call<OtpData> otpDataCall= otpApi.requestOtp(otp,mobile);

        otpDataCall.enqueue(new Callback<OtpData>() {


            @Override
            public void onResponse(Call<OtpData> call, Response<OtpData> response) {


                otpCallback.onSuccess(response.body());

            }
            @Override
            public void onFailure(Call<OtpData> call, Throwable t) {


                t.printStackTrace();
            }
        });
    }
}

