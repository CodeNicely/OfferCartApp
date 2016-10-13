package com.example.aman.offercart_v1.SplashScreen.models.data;

/**
 * Created by aman on 13/10/16.
 */
public class SplashScreenData {

    private boolean success;
    private String message;
    private int version;
    private int compulsory_update;

    public SplashScreenData(boolean success, String message, int version, int compulsory_update)
    {
        this.success = success;
        this.message = message;
        this.version = version;
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

    public int getVersion() {
        return version;
    }


}
