package com.codenicely.discountstore.project_new.shop_admin.shop_signup.model.data;

/**
 * Created by ramya on 26/2/17.
 */

public class ShopSignUpData{
    private String message;
    private boolean success;

    public ShopSignUpData(String message, boolean success) {
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
