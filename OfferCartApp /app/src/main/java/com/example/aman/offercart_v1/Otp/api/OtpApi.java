package com.codenicely.discountstore.project.verify_otp.api;

import com.codenicely.discountstore.project.verify_otp.models.data.OtpData;
import com.codenicely.discountstore.project.helper.Urls;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import com.codenicely.discountstore.project.verify_otp.models.OtpProvider;

/**
 * Created by aman on 16/10/16.
 */
public interface OtpApi {


    @POST(Urls.VERIFY_OTP)
    Call<OtpData> requestOtp(@Query("otp") String otp,@Query("mobile")String mobile);
}
