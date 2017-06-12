package com.codenicely.brandstore.project.city;

import com.codenicely.brandstore.project.city.data.SelectedCityData;

/**
 * Created by iket on 19/10/16.
 */
public interface OnCitiesSent {
    void onFailure();

    void onSuccess(SelectedCityData selectedCityData);
}
