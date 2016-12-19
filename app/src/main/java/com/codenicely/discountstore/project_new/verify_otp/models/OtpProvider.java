package com.codenicely.discountstore.project_new.verify_otp.models;

import com.codenicely.discountstore.project_new.verify_otp.OtpCallback;

/**
 * Created by aman on 16/10/16.
 */
public interface OtpProvider {
    void requestOtp(String otp, String mobile, OtpCallback otpCallback);
}
