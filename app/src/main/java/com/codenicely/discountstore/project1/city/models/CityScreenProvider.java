package com.codenicely.discountstore.project1.city.models;

import com.codenicely.discountstore.project1.city.view.OnCitiesReceived;
import com.codenicely.discountstore.project1.city.view.OnCitiesSent;

/**
 * Created by aman on 15/10/16.
 */
public interface CityScreenProvider {
    void requestCity(String token, OnCitiesReceived onCitiesReceived);

    void sendSelectedCity(int city_id, String token, OnCitiesSent onCitiesSent);
}
