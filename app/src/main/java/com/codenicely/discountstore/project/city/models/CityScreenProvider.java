package com.codenicely.discountstore.project.city.models;

import com.codenicely.discountstore.project.city.view.OnCitiesReceived;
import com.codenicely.discountstore.project.city.view.OnCitiesSent;

/**
 * Created by aman on 15/10/16.
 */
public interface CityScreenProvider {
    void requestCity(String token, OnCitiesReceived onCitiesReceived);

    void sendSelectedCity(String city_id, String token, OnCitiesSent onCitiesSent);
}
