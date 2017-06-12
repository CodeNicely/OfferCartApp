package com.codenicely.brandstore.project.welcome_screen.models.data;

/**
 * Created by aman on 18/10/16.
 */

public class WelcomeImageDetails {
    private String image_id;
    private String message;
    private String image_url;

    public WelcomeImageDetails(String image_id, String message, String image_url) {
        this.image_id = image_id;
        this.message = message;
        this.image_url = image_url;
    }

    public String getImage_id() {
        return image_id;
    }

    public String getMessage() {
        return message;
    }

    public String getImage_url() {
        return image_url;
    }
}



