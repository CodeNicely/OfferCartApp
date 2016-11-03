package com.example.aman.offercart_v1.developers.model;


import com.example.aman.offercart_v1.developers.DevelopersCallback;

/**
 * Created by meghal on 17/10/16.
 */

public interface DeveloperProvider {

    void requestDevelopersData(DevelopersCallback developersCallback);
    void onDestroy();
}
