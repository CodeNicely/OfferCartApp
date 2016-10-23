package com.example.aman.offercart_v1.city.view;

import com.example.aman.offercart_v1.city.models.data.SelectedCityData;

/**
 * Created by iket on 19/10/16.
 */
public interface OnCitiesSent {
    void onFailure();
    void onSuccess(SelectedCityData selectedCityData);
}
