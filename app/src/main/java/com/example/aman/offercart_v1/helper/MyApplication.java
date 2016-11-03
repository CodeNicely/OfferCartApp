package com.example.aman.offercart_v1.helper;

import android.app.Application;
import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by meghal on 23/10/16.
 */

public class MyApplication extends Application {

    private static Context context;
    private static String fcm;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
   //     FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/comfortaa.ttf");
    //    FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/homemade.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "fonts/dosis.ttf");
    //    FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/patrick_hand.ttf");

        fcm = FirebaseInstanceId.getInstance().getToken();
        Log.d("Resp",fcm);


    }

    public static String getFcm() {
        return fcm;
    }

    public static Context getContext() {
        return context;
    }


}
