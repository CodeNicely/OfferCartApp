package com.example.aman.offercart_v1.cityScreen.models.data;

/**
 * Created by iket on 19/10/16.
 */
public class SelectedCityData {
    private String message;
    private boolean success;

    public SelectedCityData(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }
\
    public boolean isSuccess() {
        return success;
    }
}
