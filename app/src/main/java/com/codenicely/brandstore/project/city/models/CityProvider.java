package com.codenicely.brandstore.project.city.models;

import com.codenicely.brandstore.project.city.OnCitiesReceived;
import com.codenicely.brandstore.project.city.OnCitiesSent;

/**
 * Created by aman on 15/10/16.
 */
public interface CityProvider {
    void requestCity(String access_token, int state_id, OnCitiesReceived onCitiesReceived);

    void sendSelectedCity(int city_id, String token,OnCitiesSent onCitiesSent);
}
