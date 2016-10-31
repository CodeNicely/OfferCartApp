package com.example.aman.offercart_v1.verify_otp.models;


import com.example.aman.offercart_v1.verify_otp.api.OtpApi;
import com.example.aman.offercart_v1.verify_otp.models.data.OtpData;
import com.example.aman.offercart_v1.helper.Urls;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.aman.offercart_v1.verify_otp.OtpCallback;


/**
 * Created by aman on 16/10/16.
 */
public class RetrofitOtpProvider implements OtpProvider {

    private OtpApi otpApi;


    public RetrofitOtpProvider() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        otpApi = retrofit.create(OtpApi.class);

    }

    public void requestOtp(String otp, String mobile, final OtpCallback otpCallback) {

        Call<OtpData> otpDataCall = otpApi.requestOtp(otp, mobile);

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

