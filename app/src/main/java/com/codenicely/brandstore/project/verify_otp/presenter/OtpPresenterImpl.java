package com.codenicely.brandstore.project.verify_otp.presenter;

import com.codenicely.brandstore.project.verify_otp.OtpCallback;
import com.codenicely.brandstore.project.verify_otp.models.OtpProvider;
import com.codenicely.brandstore.project.verify_otp.models.data.OtpData;
import com.codenicely.brandstore.project.verify_otp.view.OtpView;

/**
 * Created by aman on 16/10/16.
 */
public class OtpPresenterImpl implements OtpPresenter {
    private OtpProvider otpProvider;
    private OtpView otpView;

    public OtpPresenterImpl(OtpView otpView, OtpProvider otpProvider) {
        this.otpView = otpView;
        this.otpProvider = otpProvider;
    }

    @Override
    public void requestOtp(String otp, String mobile) {

        otpView.showLoading(false);

        otpProvider.requestOtp(otp, mobile, new OtpCallback() {
            @Override
            public void onSuccess(OtpData otpData) {
                if (otpData.isSuccess()) {
                    otpView.showLoading(false);
                    otpView.onOtpVerified(otpData.getAccess_token());
                } else {
                    otpView.showLoading(false);
                    otpView.showMessage(otpData.getMessage());
                }
            }
            public void onFailure(String error) {
                otpView.showLoading(false);
                otpView.showMessage("Failed");
            }
        });
    }
}
