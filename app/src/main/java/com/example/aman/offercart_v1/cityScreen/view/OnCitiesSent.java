package com.example.aman.offercart_v1.cityScreen.view;

import com.example.aman.offercart_v1.cityScreen.models.data.SelectedCityData;

/**
 * Created by iket on 19/10/16.
 */
public interface OnCitiesSent {
    void onFailure();
    void onSuccess(SelectedCityData selectedCityData);
}
