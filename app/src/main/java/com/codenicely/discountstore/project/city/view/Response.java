package com.codenicely.discountstore.project.city.view;

import com.codenicely.discountstore.project.city.models.data.CityScreenData;

import java.util.List;

/**
 * Created by iket on 19/10/16.
 */
public class Response {
    private List<CityScreenData> city_data;

    public Response(List<CityScreenData> city_data) {
        this.city_data = city_data;
    }

    public List<CityScreenData> getCity_data() {
        return city_data;
    }
}
