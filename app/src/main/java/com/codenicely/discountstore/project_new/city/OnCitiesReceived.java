package com.codenicely.discountstore.project_new.city;

import com.codenicely.discountstore.project_new.city.data.CityData;

/**
 * Created by iket on 19/10/16.
 */
public interface OnCitiesReceived {
    void onFailure();

    void onSuccess(CityData cityData);
}
