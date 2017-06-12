package com.codenicely.brandstore.project.shop_admin.shop_profile_edit.data;

/**
 * Created by ujjwal on 17/05/17.
 */

public class ShopEditData {

    boolean success;
    String message;

    public ShopEditData(boolean success, String message) {
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
