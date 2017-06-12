package com.codenicely.brandstore.project.contact_us.api;

import com.codenicely.brandstore.project.contact_us.model.data.ContactUsData;
import com.codenicely.brandstore.project.helper.Urls;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by meghal on 15/10/16.
 */

public interface ContactUsApi {


    @GET(Urls.SUB_URL_CONTACT_US)
    Call<ContactUsData> requestContactUs();

}
