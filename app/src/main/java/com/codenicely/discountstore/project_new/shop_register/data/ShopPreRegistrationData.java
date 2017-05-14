package com.codenicely.discountstore.project_new.shop_register.data;

import java.util.List;

/**
 * Created by meghal on 14/4/17.
 */

public class ShopPreRegistrationData {

    private boolean success;
    private String message;
    private List<CityData> city_list;
    private List<CategoryData> category_list;

    public ShopPreRegistrationData(boolean success, String message, List<CityData> city_list, List<CategoryData> category_list) {
        this.success = success;
        this.message = message;
        this.city_list = city_list;
        this.category_list = category_list;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<CityData> getCity_list() {
        return city_list;
    }

    public List<CategoryData> getCategory_list() {
        return category_list;
    }
}



