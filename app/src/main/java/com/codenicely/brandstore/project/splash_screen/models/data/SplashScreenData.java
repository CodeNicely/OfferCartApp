package com.codenicely.brandstore.project.splash_screen.models.data;

/**
 * Created by aman on 13/10/16.
 */
public class SplashScreenData {

    private boolean success;
    private String message;
    private int version_code;
    private int compulsory_update;

    public SplashScreenData(int version_code, String message, boolean success, int compulsory_update) {
        this.success = success;
        this.message = message;
        this.version_code = version_code;
        this.compulsory_update = compulsory_update;
    }

    public int getCompulsory_update() {
        return compulsory_update;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getVersion_code() {
        return version_code;
    }


}
