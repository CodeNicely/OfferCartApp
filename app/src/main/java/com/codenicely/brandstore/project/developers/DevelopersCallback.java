package com.codenicely.brandstore.project.developers;


import com.codenicely.brandstore.project.developers.model.data.DeveloperData;

/**
 * Created by meghal on 17/10/16.
 */

public interface DevelopersCallback {

    void onSuccess(DeveloperData developerData);

    void onFailed();


}
