package com.umapathi.greeshma.todoapplication;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.Service;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.os.PowerManager;
import 	android.content.Intent;
import android.content.Context;
import android.app.PendingIntent;
import android.app.Notification;

/**
 * Created by greeshma on 8/23/2017.
 */

public class NotificationService extends IntentService {
    private static final String ACTION_SHOW_NOTIFICATION = "my.app.service.action.show";
    private static final String ACTION_HIDE_NOTIFICATION = "my.app.service.action.hide";


    public NotificationService() {
        super("ShowNotificationIntentService");
    }

    public static void startActionShow(Context context) {
        Intent intent = new Intent(context, NotificationService.class);
        intent.setAction(ACTION_SHOW_NOTIFICATION);
        context.startService(intent);
    }

    public static void startActionHide(Context context) {
        Intent intent = new Intent(context, NotificationService.class);
        intent.setAction(ACTION_HIDE_NOTIFICATION);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_SHOW_NOTIFICATION.equals(action)) {
                handleActionShow();
            } else if (ACTION_HIDE_NOTIFICATION.equals(action)) {
                handleActionHide();
            }
        }
    }

    private void handleActionShow() {
        showStatusBarIcon(NotificationService.this);
    }

    private void handleActionHide() {
        //hideStatusBarIcon(NotificationService.this);
    }

    public static void showStatusBarIcon(Context ctx) {
        Context context = ctx;
        android.support.v4.app.NotificationCompat.Builder builder = new 	android.support.v4.app.NotificationCompat.Builder(ctx)
                .setContentTitle("Notif"/*ctx.getString(R.string.notification_message)*/)
                .setSmallIcon(R.drawable.calendar)
                .setOngoing(true);
        Intent intent = new Intent(context, ListAddActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, 001, intent, 0);
        builder.setContentIntent(pIntent);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notif = builder.build();
        notif.flags |= Notification.FLAG_ONGOING_EVENT;
        mNotificationManager.notify(001, notif);
    }
    /**
     * A constructor is required, and must call the super IntentService(String)
     * constructor with a name for the worker thread.
     */
    /*public NotificationService() {
        super("NotificationService");
    }
    Alarm alarm = new Alarm();
    public void onStart(Context context,Intent intent, int startId)
    {
        alarm.SetAlarm(context);
    }

    public void onReceive(Context context, Intent intent)
    {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
        {
            alarm.SetAlarm(context);
        }
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
    /**
     * The IntentService calls this method from the default worker thread with
     * the intent that started the service. When this method returns, IntentService
     * stops the service, as appropriate.

    @Override
    protected void onHandleIntent(Intent intent) {
        // Normally we would do some work here, like download a file.
        // For our sample, we just sleep for 5 seconds.
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // Restore interrupt status.
            Thread.currentThread().interrupt();
        }
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent,flags,startId);
    }*/
}
