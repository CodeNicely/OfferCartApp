package com.codenicely.brandstore.project.login.models.data;

/**
 * Created by aman on 15/10/16.
 */
public class LoginData {

    private String message;
    private boolean success;


    public LoginData(String message, boolean success) {
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
