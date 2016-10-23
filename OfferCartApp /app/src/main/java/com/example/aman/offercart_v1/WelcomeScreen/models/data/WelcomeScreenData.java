package com.example.aman.offercart_v1.welcome_screen.models.data;

import java.util.List;

/**
 * Created by aman on 16/10/16.
 */
public class WelcomeScreenData {
    private boolean success;
    private String message;
    private List<WelcomeImageDetails> homeDetailsList;

    public WelcomeScreenData(boolean success, String message, List<WelcomeImageDetails> homeDetailsList) {

        this.success = success;
        this.message = message;
        this.homeDetailsList = homeDetailsList;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<WelcomeImageDetails> getHomeDetailsList() {
        return homeDetailsList;
    }
}
