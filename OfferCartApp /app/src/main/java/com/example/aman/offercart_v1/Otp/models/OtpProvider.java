package com.example.aman.offercart_v1.Otp.models;

import com.example.aman.offercart_v1.Otp.OtpCallback;

/**
 * Created by aman on 16/10/16.
 */
public interface OtpProvider {
    void requestOtp(String otp,String mobile, OtpCallback otpCallback);
}
