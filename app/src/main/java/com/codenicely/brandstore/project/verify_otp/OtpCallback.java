package com.codenicely.brandstore.project.verify_otp;

import com.codenicely.brandstore.project.verify_otp.models.data.OtpData;

/**
 * Created by aman on 16/10/16.
 */
public interface OtpCallback {
    void onSuccess(OtpData otpData);

    void onFailure(String error);
}
