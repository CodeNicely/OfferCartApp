package com.codenicely.discountstore.project_new.shop_admin.shop_signup.model.data;

import java.util.List;

/**
 * Created by ramya on 26/2/17.
 */

public class CitiesData {
    private String message;
    private boolean success;
    private List<CitiesDataDetails> citiesList;

    public CitiesData(String message, boolean success,List<CitiesDataDetails> citiesList) {
        this.message = message;
        this.success = success;
        this.citiesList = citiesList;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<CitiesDataDetails> getCitiesList() {
        return citiesList;
    }
}
