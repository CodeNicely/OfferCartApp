package com.codenicely.brandstore.project.contact_us;


import com.codenicely.brandstore.project.contact_us.model.data.ContactUsData;

/**
 * Created by meghal on 15/10/16.
 */

public interface ContactUsCallback {

    void onSuccess(ContactUsData contactUsData);

    void onFailure();

}
