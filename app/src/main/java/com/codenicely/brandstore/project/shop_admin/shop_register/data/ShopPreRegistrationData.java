package com.codenicely.brandstore.project.shop_admin.shop_register.data;

import java.util.List;

/**
 * Created by meghal on 14/4/17.
 */

public class ShopPreRegistrationData {

    private boolean success;
    private String message;
    private List<StateData> state_list;
    private List<CategoryData> category_list;

    public ShopPreRegistrationData(boolean success, String message, List<StateData> state_list, List<CategoryData> category_list) {
        this.success = success;
        this.message = message;
        this.state_list = state_list;
        this.category_list = category_list;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<StateData> getState_list() {
        return state_list;
    }

    public List<CategoryData> getCategory_list() {
        return category_list;
    }
}



