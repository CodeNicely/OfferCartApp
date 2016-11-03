package com.example.aman.offercart_v1.about_us.model;


import com.example.aman.offercart_v1.about_us.AboutUsCallBack;

/**
 * Created by meghal on 13/10/16.
 */

public interface AboutUsProvider {


    void requestAboutUs(AboutUsCallBack aboutUsCallBack);

    void onDestroy();

}
