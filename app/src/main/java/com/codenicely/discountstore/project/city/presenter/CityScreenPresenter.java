package com.codenicely.discountstore.project.city.presenter;

/**
 * Created by aman on 15/10/16.
 */
public interface CityScreenPresenter {
    void requestCity(String token);

    void sendSelectedCity(String city, String city_id, String token);
}
