package com.codenicely.brandstore.project.city.data;

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

    public boolean isSuccess() {
        return success;
    }
}
