package com.codenicely.brandstore.project.verify_otp.view;

/**
 * Created by aman on 16/10/16.
 */
public interface OtpView {

    void showLoading(boolean show);

    void showMessage(String message);

    void onOtpVerified(String token);


}
