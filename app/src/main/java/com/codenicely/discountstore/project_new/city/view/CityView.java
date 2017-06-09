package com.codenicely.discountstore.project_new.city.view;

import com.codenicely.discountstore.project_new.city.data.CityDetails;

import java.util.List;

/**
 * Created by aman on 15/10/16.
 */
public interface CityView {


    void showLoading(boolean show);

    void showMessage(String message);

    void onCityListRecieved(List<CityDetails> cityDetailsList);

    void onCitySelected(int city_id, String city_name);

    void onCitySelectSuccess(String city);

}

