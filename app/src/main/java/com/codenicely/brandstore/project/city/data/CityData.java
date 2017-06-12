package com.codenicely.brandstore.project.city.data;

import java.util.List;

/**
 * Created by ujjwal on 09/06/16.
 */
public class CityData {
    private boolean success;
    private String message;
    private List<CityDetails> city_data;

    public CityData(boolean success, String message, List<CityDetails> city_data) {
        this.success = success;
        this.message = message;
        this.city_data = city_data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

	public List<CityDetails> getCity_data() {
		return city_data;
	}
}
