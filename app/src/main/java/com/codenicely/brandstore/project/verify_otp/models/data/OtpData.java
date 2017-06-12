package com.codenicely.brandstore.project.verify_otp.models.data;


/**
 * Created by aman on 16/10/16.
 */
public class OtpData {

    private String message;
    private boolean success;
    private String access_token;

    public OtpData(String message, boolean success, String access_token) {
        this.message = message;
        this.success = success;
        this.access_token = access_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

}

