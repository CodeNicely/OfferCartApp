package com.codenicely.brandstore.project.developers.model;


import com.codenicely.brandstore.project.developers.DevelopersCallback;

/**
 * Created by meghal on 17/10/16.
 */

public interface DeveloperProvider {

    void requestDevelopersData(DevelopersCallback developersCallback);

    void onDestroy();
}
