package com.example.aman.offercart_v1.welcome_screen.models.data;

import java.util.List;

/**
 * Created by aman on 16/10/16.
 */
public class WelcomeImageDetails {

    private List<String> viewPagerImageList;
    private String image;
    private String url;

    public WelcomeImageDetails(List<String> viewPagerImageList, String image,String url)
    {
        this.viewPagerImageList=viewPagerImageList;
        this.url=url;
        this.image=image;

    }

    public String getImage() {
        return image;
    }

    public String getUrl() {
        return url;
    }






}
