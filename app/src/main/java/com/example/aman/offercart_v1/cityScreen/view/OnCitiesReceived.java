package com.example.aman.offercart_v1.cityScreen.view;

import com.example.aman.offercart_v1.cityScreen.models.data.CityScreenData;

import java.util.List;

/**
 * Created by iket on 19/10/16.
 */
public interface OnCitiesReceived {
    void onFailure();
    void onSuccess(List<CityScreenData> cityScreenDatas);
}
