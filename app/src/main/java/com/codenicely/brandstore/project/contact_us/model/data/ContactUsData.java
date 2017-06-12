package com.codenicely.brandstore.project.contact_us.model.data;

/**
 * Created by meghal on 15/10/16.
 */

public class ContactUsData {


    private boolean success;
    private String message;
    private String email;
    private String mobile;
    private String address;
    private String facebook;
    private String image;


    public ContactUsData(boolean success, String message, String email, String mobile, String address, String facebook, String image) {
        this.success = success;
        this.message = message;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.facebook = facebook;
        this.image = image;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }

    public String getFacebook() {
        return facebook;
    }

    /*  public String getTwitter() {
          return twitter;
      }

      public String getInstagram() {
          return instagram;
      }

      public String getWebsite() {
          return website;
      }
  */
    public String getImage() {
        return image;
    }
}
