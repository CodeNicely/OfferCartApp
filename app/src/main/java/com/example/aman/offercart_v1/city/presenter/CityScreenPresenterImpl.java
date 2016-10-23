package com.example.aman.offercart_v1.city.presenter;

import android.util.Log;

import com.example.aman.offercart_v1.city.models.CityScreenProvider;
import com.example.aman.offercart_v1.city.models.data.CityScreenData;
import com.example.aman.offercart_v1.city.models.data.SelectedCityData;
import com.example.aman.offercart_v1.city.view.CityScreenView;
import com.example.aman.offercart_v1.city.view.OnCitiesReceived;
import com.example.aman.offercart_v1.city.view.OnCitiesSent;

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
        Log.d("Res","3");

        cityScreenProvider.requestCity(token,new OnCitiesReceived() {
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
        Log.d("Res","2");
        cityScreenView.showLoading(true);
        Log.d("Res","3");
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
