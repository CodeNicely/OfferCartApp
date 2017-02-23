package com.codenicely.discountstore.project_new.city.models;

import com.codenicely.discountstore.project_new.city.view.OnCitiesReceived;
import com.codenicely.discountstore.project_new.city.view.OnCitiesSent;

/**
 * Created by aman on 15/10/16.
 */
public interface CityScreenProvider {
    void requestCity(String token, OnCitiesReceived onCitiesReceived);

    void sendSelectedCity(int city_id, String token, String fcm,OnCitiesSent onCitiesSent);
}
