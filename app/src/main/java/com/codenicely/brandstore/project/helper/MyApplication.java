package com.codenicely.brandstore.project.helper;

import android.app.Application;
import android.content.Context;

import com.google.firebase.iid.FirebaseInstanceId;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

/**
 * Created by meghal on 23/10/16.
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        context = this;
        //     FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/comfortaa.ttf");
        //    FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/homemade.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "fonts/comfortaa_regular.ttf");
        //    FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/patrick_hand.ttf");
    }

    public static String getFcm() {
        return FirebaseInstanceId.getInstance().getToken();
    }

    public static Context getContext() {
        return context;
    }

}
