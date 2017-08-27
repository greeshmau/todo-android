package com.umapathi.greeshma.todoapplication.Receiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.umapathi.greeshma.todoapplication.R;

/**
 * Created by Greeshma on 8/23/17.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        String title = intent.getStringExtra("title");
        String EditingStatus = intent.getStringExtra("titleId");

        android.support.v4.app.NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Item due today")
                .setContentText(title);

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //NotificationManager.notify()
        mNotificationManager.notify(001, mBuilder.build());
        Log.i("GRREGEX", "receied " + intent.getAction());
    }
}
