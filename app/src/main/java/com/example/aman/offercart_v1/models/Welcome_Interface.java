package com.example.aman.offercart_v1.models;

/**
 * Created by aman on 11/10/16.
 */
import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;


public interface Welcome_Interface {


    @GET("/Welcome")
    void getImages(Callback<List<Welcome>> callback);




}