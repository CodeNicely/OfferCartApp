package com.codenicely.discountstore.project1.contact_us.view;


import com.codenicely.discountstore.project1.contact_us.model.data.ContactUsData;

/**
 * Created by meghal on 15/10/16.
 */

public interface ContactUsView {


    void showLoader(boolean show);

    void showMessage(String message);

    void setData(ContactUsData contactUsData);

}
