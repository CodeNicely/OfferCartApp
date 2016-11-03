package com.example.aman.offercart_v1.city.view;

import com.example.aman.offercart_v1.city.models.data.CityScreenData;

import java.util.List;

/**
 * Created by aman on 15/10/16.
 */
public interface CityScreenView {


    void showLoading(boolean show);

    void showMessage(String message);

    void onCityVerified(List<CityScreenData> cityScreenDataList);

    void onCitySelected(String city_id, String city_name);

    void onCitySent(String city);

}

