package com.example.aman.offercart_v1.city.models;

import com.example.aman.offercart_v1.city.view.OnCitiesReceived;
import com.example.aman.offercart_v1.city.view.OnCitiesSent;

/**
 * Created by aman on 15/10/16.
 */
public interface CityScreenProvider {
    void requestCity(String token,OnCitiesReceived onCitiesReceived);
    void sendSelectedCity(String city_id, OnCitiesSent onCitiesSent);
}
