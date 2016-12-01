package com.codenicely.discountstore.project1.city.view;

import com.codenicely.discountstore.project1.city.models.data.SelectedCityData;

/**
 * Created by iket on 19/10/16.
 */
public interface OnCitiesSent {
    void onFailure();

    void onSuccess(SelectedCityData selectedCityData);
}
