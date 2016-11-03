package com.codenicely.discountstore.project.verify_otp.presenter;

import com.codenicely.discountstore.project.verify_otp.models.OtpProvider;
import com.codenicely.discountstore.project.verify_otp.view.OtpView;

import com.codenicely.discountstore.project.verify_otp.models.data.OtpData;
import com.codenicely.discountstore.project.verify_otp.OtpCallback;
/**
 * Created by aman on 16/10/16.
 */
public class OtpPresenterImpl implements OtpPresenter {
    private OtpProvider otpProvider;
    private OtpView otpView;

    public OtpPresenterImpl(OtpView otpnView, OtpProvider otpProvider) {
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