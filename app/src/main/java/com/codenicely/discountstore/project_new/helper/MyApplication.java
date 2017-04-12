package com.codenicely.discountstore.project_new.helper;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.codenicely.discountstore.project_new.update_fcm.FcmUpdaterView;
import com.codenicely.discountstore.project_new.update_fcm.presenter.FcmTokenUpdater;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

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
        FontsOverride.setDefaultFont(this, "SERIF", "fonts/itim.ttf");
    //    FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/patrick_hand.ttf");
<<<<<<< HEAD
=======

>>>>>>> 06339525a66c3054f66ec38aa3315f1974956e0d
    }

    public static String getFcm() {
        return FirebaseInstanceId.getInstance().getToken();
    }

    public static Context getContext() {
        return context;
    }

}
