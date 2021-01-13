package com.bishoyosama.metroguide;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class MyReceiver extends BroadcastReceiver {
Context c;
    private static final String TAG = "recver";

    @Override
    public void onReceive(Context context, Intent intent) {
//        Toast.makeText(context, " plapa", Toast.LENGTH_SHORT).show();
       c=context;
        Log.d(TAG,"intent 1");

        Intent in=new Intent(context.getApplicationContext(),MainActivity.class);
        in.putExtra("name","hello");
        PendingIntent pe=PendingIntent.getActivity(context.getApplicationContext(),0,in,0);

        NotificationManager manager= (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

//        createNotificationChannel();
        Log.d(TAG,"intent 2");
        NotificationCompat.Builder builder=new NotificationCompat.Builder(context,"notify");
        builder.setTicker("Destination reached")
                .setContentTitle("Destination reached")
                .setContentText("you are within 100m of your Destination")
                .setSmallIcon(R.mipmap.logo_round)
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_MAX)
                .setContentIntent(pe);
        Log.d(TAG,"intent 3");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = "Your_channel_id";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
            builder.setChannelId(channelId);
        }
        Notification notification = builder.build();

        manager.notify(1,notification);
        Log.d(TAG,"intent 4");
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "com.bishoyosama.metro";
            String description = "Destination reached";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("Destination reached", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = c.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}