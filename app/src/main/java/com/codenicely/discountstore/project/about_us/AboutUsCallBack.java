package com.codenicely.discountstore.project.about_us;


import com.codenicely.discountstore.project.about_us.model.data.AboutUsData;

/**
 * Created by meghal on 13/10/16.
 */

public interface AboutUsCallBack {


    void onSuccess(AboutUsData aboutUsData);

    void onFailure();

}

