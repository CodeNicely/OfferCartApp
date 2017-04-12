package com.codenicely.discountstore.project_new.city.presenter;

import android.util.Log;

import com.codenicely.discountstore.project_new.city.models.CityScreenProvider;
import com.codenicely.discountstore.project_new.city.models.data.CityScreenData;
import com.codenicely.discountstore.project_new.city.models.data.SelectedCityData;
import com.codenicely.discountstore.project_new.city.view.CityScreenView;
import com.codenicely.discountstore.project_new.city.view.OnCitiesReceived;
import com.codenicely.discountstore.project_new.city.view.OnCitiesSent;

import java.util.List;

/**
 * Created by aman on 15/10/16.
 */
public class CityScreenPresenterImpl implements CityScreenPresenter {
    private CityScreenView cityScreenView;
    private CityScreenProvider cityScreenProvider;

    public CityScreenPresenterImpl(CityScreenView cityScreenView, CityScreenProvider cityScreenProvider) {
        this.cityScreenView = cityScreenView;
        this.cityScreenProvider = cityScreenProvider;
    }

    @Override
    public void requestCity(String token) {


        cityScreenView.showLoading(true);
        Log.d("Res", "3");

        cityScreenProvider.requestCity(token, new OnCitiesReceived() {
            @Override
            public void onFailure() {
                cityScreenView.showLoading(false);
                cityScreenView.showMessage("Please try again in some time");
            }

            @Override
            public void onSuccess(List<CityScreenData> cityScreenDatas) {
                cityScreenView.showLoading(false);
                cityScreenView.onCityVerified(cityScreenDatas);
            }
        });

    }

    @Override
    public void sendSelectedCity(final String city, int city_id, String token, String fcm) {
        cityScreenView.showLoading(true);
        cityScreenProvider.sendSelectedCity(city_id, token, fcm, new OnCitiesSent() {
            @Override
            public void onFailure() {
                cityScreenView.showLoading(false);
                cityScreenView.showMessage("Please try again in some time");
            }

            @Override
            public void onSuccess(SelectedCityData selectedCityData) {

                if (selectedCityData.isSuccess()) {
                    cityScreenView.showLoading(false);
                    cityScreenView.onCitySent(city);
                } else {
                    cityScreenView.showLoading(false);
                    cityScreenView.showMessage(selectedCityData.getMessage());

                }


            }
        });


    }
}
