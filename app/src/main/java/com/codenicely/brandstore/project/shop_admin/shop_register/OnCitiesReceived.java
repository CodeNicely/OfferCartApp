package com.codenicely.brandstore.project.shop_admin.shop_register;

import com.codenicely.brandstore.project.city.data.CityData;

/**
 * Created by iket on 19/10/16.
 */
public interface OnCitiesReceived {
    void onFailure();

    void onSuccess(CityData cityData);
}
