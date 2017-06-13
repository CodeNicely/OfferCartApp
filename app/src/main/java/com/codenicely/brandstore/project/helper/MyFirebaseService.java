package com.codenicely.brandstore.project.helper;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import android.support.v4.app.NotificationCompat;

import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.home.HomePage;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by ramya on 23/2/17.
 */

public class MyFirebaseService extends FirebaseMessagingService {

    private String TAG = "MyFirebaseService";
    private static int nid = 0;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

//        Toast.makeText(getApplicationContext(), "OnMessageReceived", Toast.LENGTH_SHORT).show();

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getClickAction());

        }
        sendNotification(remoteMessage);

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

    //we need this module only for creating notification when app is in foreground
    private void sendNotification(RemoteMessage messageBody) {
        Intent intent = new Intent(this, HomePage.class);
/*
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_SINGLE_TOP );
        intent.putExtra("FcmActivity","True");
        intent.putExtra("shop_id",messageBody.getData().get("shop_id"));
        intent.putExtra("shop_name",messageBody.getData().get("shop_name"));
        Log.d("value of id and name",messageBody.getData().get("shop_id")+""+messageBody.getData().get("shop_name"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, nid++ */
/* Request code*//*
 , intent,
*/

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("FcmActivity", "True");
        intent.putExtra("shop_id", messageBody.getData().get("shop_id"));
        intent.putExtra("shop_name", messageBody.getData().get("shop_name"));
        Log.d("value of id and name", messageBody.getData().get("shop_id") + "" + messageBody.getData().get("shop_name"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, nid++ /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("Discount Store")
                .setContentText(messageBody.getData().get("message"))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setSmallIcon(R.drawable.brand_store_logo);

        Log.d("value of nid", String.valueOf(nid));

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(nid  /*ID of notification*/ , notificationBuilder.build());
    }


}

