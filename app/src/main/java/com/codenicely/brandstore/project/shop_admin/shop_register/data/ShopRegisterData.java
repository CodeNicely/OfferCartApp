package com.codenicely.brandstore.project.shop_admin.shop_register.data;

/**
 * Created by meghal on 12/10/16.
 */

public class ShopRegisterData {

    boolean success;
    String message;

    public ShopRegisterData(boolean success, String message) {
        this.success = success;
        this.message = message;
    }


    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
