package com.example.aman.offercart_v1.Otp.api;

import com.example.aman.offercart_v1.Otp.models.data.OtpData;
import com.example.aman.offercart_v1.helper.Urls;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import com.example.aman.offercart_v1.Otp.models.OtpProvider;

/**
 * Created by aman on 16/10/16.
 */
public interface OtpApi {


    @POST(Urls.VERIFY_OTP)
    Call<OtpData> requestOtp(@Query("otp") String otp);
}
