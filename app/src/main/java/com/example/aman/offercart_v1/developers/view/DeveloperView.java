package com.example.aman.offercart_v1.developers.view;


import com.example.aman.offercart_v1.developers.model.data.CompanyData;

/**
 * Created by meghal on 17/10/16.
 */

public interface DeveloperView {

    void showLoading(boolean show);

    void showMessage(String message);

    void setData(CompanyData companyData);


}
