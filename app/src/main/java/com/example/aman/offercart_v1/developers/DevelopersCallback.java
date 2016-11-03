package com.example.aman.offercart_v1.developers;


import com.example.aman.offercart_v1.developers.model.data.DeveloperData;

/**
 * Created by meghal on 17/10/16.
 */

public interface DevelopersCallback {

    void onSuccess(DeveloperData developerData);
    void onFailed();


}
