package com.example.aman.offercart_v1.verify_otp.api;

import com.example.aman.offercart_v1.verify_otp.models.data.OtpData;
import com.example.aman.offercart_v1.helper.Urls;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by aman on 16/10/16.
 */
public interface OtpApi {

    @FormUrlEncoded
    @POST(Urls.VERIFY_OTP)
    Call<OtpData> requestOtp(@Field("access_token")String otpToken,@Field("otp") String otp, @Field("mobile")String mobile);
}
