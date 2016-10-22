package com.example.aman.offercart_v1.cityScreen.models;

import com.example.aman.offercart_v1.cityScreen.view.OnCitiesReceived;
import com.example.aman.offercart_v1.cityScreen.view.OnCitiesSent;

/**
 * Created by aman on 15/10/16.
 */
public interface CityScreenProvider {
    void requestCity(OnCitiesReceived onCitiesReceived);
    void sendSelectedCity(String city_id, OnCitiesSent onCitiesSent);
}
