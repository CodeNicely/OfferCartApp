package com.codenicely.brandstore.project.verify_otp.models;

import com.codenicely.brandstore.project.verify_otp.OtpCallback;

/**
 * Created by aman on 16/10/16.
 */
public interface OtpProvider {
    void requestOtp(String otp, String mobile, OtpCallback otpCallback);
}
