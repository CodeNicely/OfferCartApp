package com.example.aman.offercart_v1.verify_otp;

import com.example.aman.offercart_v1.verify_otp.models.data.OtpData;

/**
 * Created by aman on 16/10/16.
 */
public interface OtpCallback {
    void onSuccess(OtpData otpData);

    void onFailure(String error);
}
