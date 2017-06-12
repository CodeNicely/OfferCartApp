package com.codenicely.brandstore.project.city.data;

/**
 * Created by aman on 15/10/16.
 */
public class CityDetails {
    private int city_id;
    private String city_name;

    public CityDetails(int city_id, String city_name) {
        this.city_id = city_id;
        this.city_name = city_name;
    }

    public int getCity_id() {
        return city_id;
    }

    public String getCity_name() {
        return city_name;
    }

}
