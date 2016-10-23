package com.example.aman.offercart_v1.helper;

import android.app.Application;
import android.content.Context;

/**
 * Created by meghal on 23/10/16.
 */

public class MyApplication extends Application {

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
   //     FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/comfortaa.ttf");
    //    FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/homemade.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "fonts/dosis.ttf");
    //    FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/patrick_hand.ttf");

    }

    public static Context getContext() {
        return context;
    }


}
