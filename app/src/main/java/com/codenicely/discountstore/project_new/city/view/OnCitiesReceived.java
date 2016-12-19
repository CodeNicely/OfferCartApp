package com.codenicely.discountstore.project_new.city.view;

import com.codenicely.discountstore.project_new.city.models.data.CityScreenData;

import java.util.List;

/**
 * Created by iket on 19/10/16.
 */
public interface OnCitiesReceived {
    void onFailure();

    void onSuccess(List<CityScreenData> cityScreenDatas);
}
