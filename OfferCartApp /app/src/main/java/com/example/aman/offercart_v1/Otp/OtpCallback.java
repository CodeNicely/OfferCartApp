package com.example.aman.offercart_v1.Otp;

import com.example.aman.offercart_v1.Otp.models.data.OtpData;

/**
 * Created by aman on 16/10/16.
 */
public interface OtpCallback {
    void onSuccess(OtpData otpData);
    void onFailure(String error);
}
