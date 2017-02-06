package com.codenicely.discountstore.project_new.developers.model;


import com.codenicely.discountstore.project_new.developers.DevelopersCallback;

/**
 * Created by meghal on 17/10/16.
 */

public interface DeveloperProvider {

    void requestDevelopersData(DevelopersCallback developersCallback);

    void onDestroy();
}
