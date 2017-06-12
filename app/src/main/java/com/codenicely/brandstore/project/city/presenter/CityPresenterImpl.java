package com.codenicely.brandstore.project.city.presenter;

import android.util.Log;

import com.codenicely.brandstore.project.city.data.CityData;
import com.codenicely.brandstore.project.city.models.CityProvider;
import com.codenicely.brandstore.project.city.data.SelectedCityData;
import com.codenicely.brandstore.project.city.view.CityView;
import com.codenicely.brandstore.project.city.OnCitiesReceived;
import com.codenicely.brandstore.project.city.OnCitiesSent;

/**
 * Created by aman on 15/10/16.
 */
public class CityPresenterImpl implements CityPresenter {
    private CityView cityView;
    private CityProvider cityProvider;

    public CityPresenterImpl(CityView cityView, CityProvider cityProvider) {
        this.cityView = cityView;
        this.cityProvider = cityProvider;
    }

    @Override
    public void requestCity(String token, int state_id) {


        cityView.showLoading(true);
        Log.d("Res", "3");

        cityProvider.requestCity(token,state_id, new OnCitiesReceived() {
            @Override
            public void onFailure() {
                cityView.showLoading(false);
                cityView.showMessage("Please try again in some time");
            }

            @Override
            public void onSuccess(CityData cityData) {
                cityView.showLoading(false);
                cityView.onCityListRecieved(cityData.getCity_data());
            }
        });

    }

    @Override
    public void sendSelectedCity(final String city, int city_id, String token) {
        cityView.showLoading(true);
        cityProvider.sendSelectedCity(city_id, token,new OnCitiesSent() {
            @Override
            public void onFailure() {
                cityView.showLoading(false);
                cityView.showMessage("Please try again in some time");
            }

            @Override
            public void onSuccess(SelectedCityData selectedCityData) {

                if (selectedCityData.isSuccess()) {
                    cityView.showLoading(false);
                    cityView.onCitySelectSuccess(city);
                } else {
                    cityView.showLoading(false);
                    cityView.showMessage(selectedCityData.getMessage());

                }


            }
        });


    }
}
