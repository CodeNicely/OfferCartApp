package com.codenicely.discountstore.project_new.city.presenter;

/**
 * Created by aman on 15/10/16.
 */
public interface CityScreenPresenter {
    void requestCity(String token);

    void sendSelectedCity(String city, int city_id, String token,String fcm);
}