package com.codenicely.discountstore.project_new.city.models;

import com.codenicely.discountstore.project_new.city.OnCitiesReceived;
import com.codenicely.discountstore.project_new.city.OnCitiesSent;

/**
 * Created by aman on 15/10/16.
 */
public interface CityProvider {
    void requestCity(String access_token, int state_id, OnCitiesReceived onCitiesReceived);

    void sendSelectedCity(int city_id, String token,OnCitiesSent onCitiesSent);
}
