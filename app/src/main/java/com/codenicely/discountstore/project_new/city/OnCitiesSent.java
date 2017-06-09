package com.codenicely.discountstore.project_new.city;

import com.codenicely.discountstore.project_new.city.data.SelectedCityData;

/**
 * Created by iket on 19/10/16.
 */
public interface OnCitiesSent {
    void onFailure();

    void onSuccess(SelectedCityData selectedCityData);
}
