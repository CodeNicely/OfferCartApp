package com.example.aman.offercart_v1.verify_otp.presenter;

import com.example.aman.offercart_v1.verify_otp.OtpCallback;
import com.example.aman.offercart_v1.verify_otp.models.OtpProvider;
import com.example.aman.offercart_v1.verify_otp.models.data.OtpData;
import com.example.aman.offercart_v1.verify_otp.view.OtpView;

/**
 * Created by aman on 16/10/16.
 */
public class OtpPresenterImpl implements OtpPresenter{ private OtpProvider otpProvider;
    private OtpView otpView;

    public OtpPresenterImpl(OtpView otpView, OtpProvider otpProvider) {
        this.otpView = otpView;
        this.otpProvider = otpProvider;
    }

    @Override
    public void requestOtp(String otp, String mobile) {

        otpView.showLoading(true);

        otpProvider.requestOtp(otp, mobile, new OtpCallback() {
            @Override
            public void onSuccess(OtpData otpData) {


                otpView.showLoading(false);

                otpView.showMessage("success");
                otpView.onOtpVerified();
            }

            public void onFailure(String error) {
                otpView.showLoading(false);
                otpView.showMessage("Failed");

            }
        });


    }
}
