package com.codenicely.discountstore.project_new.shop_admin.shop_signup.model.data;

/**
 * Created by ramya on 26/2/17.
 */

public class CitiesDataDetails {
    private String city_name;
    private int city_id;

    public CitiesDataDetails(String city_name, int city_id) {
        this.city_name = city_name;
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public int getCity_id() {
        return city_id;
    }
}
