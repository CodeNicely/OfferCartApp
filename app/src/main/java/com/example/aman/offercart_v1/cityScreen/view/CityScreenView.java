package com.example.aman.offercart_v1.cityScreen.view;

import com.example.aman.offercart_v1.cityScreen.models.data.CityScreenData;

import java.util.List;

/**
 * Created by aman on 15/10/16.
 */
public interface CityScreenView {


    void showLoading(boolean show);
    void showMessage(String message);
    void onCityVerified(List<CityScreenData> cityScreenDataList);

}

