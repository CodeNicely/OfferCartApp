package com.example.aman.offercart_v1.Otp.models.data;

import com.example.aman.offercart_v1.helper.Urls;



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

