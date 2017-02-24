package com.codenicely.discountstore.project_new.developers;


import com.codenicely.discountstore.project_new.developers.model.data.DeveloperData;

/**
 * Created by meghal on 17/10/16.
 */

public interface DevelopersCallback {

    void onSuccess(DeveloperData developerData);

    void onFailed();


}
