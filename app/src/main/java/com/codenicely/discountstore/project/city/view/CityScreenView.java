package com.codenicely.discountstore.project.city.view;

import com.codenicely.discountstore.project.city.models.data.CityScreenData;

import java.util.List;

/**
 * Created by aman on 15/10/16.
 */
public interface CityScreenView {


    void showLoading(boolean show);

    void showMessage(String message);

    void onCityVerified(List<CityScreenData> cityScreenDataList);

    void onCitySelected(int city_id, String city_name);

    void onCitySent(String city);

}

