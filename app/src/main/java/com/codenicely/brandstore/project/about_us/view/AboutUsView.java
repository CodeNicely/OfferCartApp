package com.codenicely.brandstore.project.about_us.view;


import com.codenicely.brandstore.project.about_us.data.AboutUsData;

/**
 * Created by meghal on 13/10/16.
 */

public interface AboutUsView {


    void showMessage(String message);

    void showLoader(boolean show);

    void setData(AboutUsData aboutUsData);


}
