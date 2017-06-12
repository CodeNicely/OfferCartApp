package com.codenicely.brandstore.project.city.presenter;

/**
 * Created by aman on 15/10/16.
 */
public interface CityPresenter {
    void requestCity(String token,int state_id);

    void sendSelectedCity(String city, int city_id, String token);
}
