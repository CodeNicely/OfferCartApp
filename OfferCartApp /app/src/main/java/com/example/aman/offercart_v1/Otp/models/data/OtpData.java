package com.codenicely.discountstore.project.verify_otp.models.data;

import com.codenicely.discountstore.project.helper.Urls;



/**
 * Created by aman on 16/10/16.
 */
public class OtpData {

    private String message;
    private boolean success;


    public OtpData(String message, boolean success) {
        this.message = message;
        this.success = success;
    }


    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

}

