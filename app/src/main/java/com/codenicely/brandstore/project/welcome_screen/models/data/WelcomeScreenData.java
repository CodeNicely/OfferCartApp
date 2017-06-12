package com.codenicely.brandstore.project.welcome_screen.models.data;

import java.util.List;

/**
 * Created by aman on 16/10/16.
 */
public class WelcomeScreenData {
    private boolean success;
    private String message;
    private List<WelcomeImageDetails> slider_data;

    public WelcomeScreenData(boolean success, String message, List<WelcomeImageDetails> slider_data) {
        this.success = success;
        this.message = message;
        this.slider_data = slider_data;
    }


    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<WelcomeImageDetails> getSlider_data() {
        return slider_data;
    }
}
