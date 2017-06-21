package com.codenicely.brandstore.project.about_us.data;

/**
 * Created by meghal on 13/10/16.
 */

public class AboutUsData {


    private boolean success;
    private String message;
    private String image_url;
    private String title;
    private String description;

    public AboutUsData(boolean success, String message, String image_url, String title, String description) {
        this.success = success;
        this.message = message;
        this.image_url = image_url;
        this.title = title;
        this.description = description;
    }


    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
