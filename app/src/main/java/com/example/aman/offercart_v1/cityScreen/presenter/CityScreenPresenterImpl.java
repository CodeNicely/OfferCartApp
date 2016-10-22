package com.example.aman.offercart_v1.cityScreen.presenter;

import com.example.aman.offercart_v1.cityScreen.models.CityScreenProvider;
import com.example.aman.offercart_v1.cityScreen.models.data.CityScreenData;
import com.example.aman.offercart_v1.cityScreen.models.data.SelectedCityData;
import com.example.aman.offercart_v1.cityScreen.view.CityScreenView;
import com.example.aman.offercart_v1.cityScreen.view.OnCitiesReceived;
import com.example.aman.offercart_v1.cityScreen.view.OnCitiesSent;

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
    public void requestCity() {
        cityScreenView.showLoading(true);
        cityScreenProvider.requestCity(new OnCitiesReceived() {
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
    public void sendSelectedCity(String city_id) {
        cityScreenView.showLoading(true);
        cityScreenProvider.sendSelectedCity(city_id, new OnCitiesSent() {
            @Override
            public void onFailure() {
                cityScreenView.showLoading(false);
                cityScreenView.showMessage("Please try again in some time");
            }

            @Override
            public void onSuccess(SelectedCityData selectedCityData) {
                cityScreenView.showLoading(false);
                cityScreenView.showMessage("Done!");
                cityScreenView.onCitySent();

            }
        });



    }
}
