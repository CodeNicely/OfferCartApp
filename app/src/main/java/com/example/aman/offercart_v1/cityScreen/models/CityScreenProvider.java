package com.example.aman.offercart_v1.cityScreen.models;

import com.example.aman.offercart_v1.cityScreen.view.OnCitiesReceived;

/**
 * Created by aman on 15/10/16.
 */
public interface CityScreenProvider {
    void requestCity(OnCitiesReceived onCitiesReceived);
}
