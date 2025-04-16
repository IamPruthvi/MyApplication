package com.example.myapplication;

import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import androidx.room.Room;

public class MyNotificationListener extends NotificationListenerService {

    private AppDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "noti-db")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        String pkg = sbn.getPackageName();
        String title = sbn.getNotification().extras.getString("android.title", "");
        String text = sbn.getNotification().extras.getString("android.text", "");

        NotificationData data = new NotificationData();
        data.packageName = pkg;
        data.title = title;
        data.text = text;
        data.timestamp = System.currentTimeMillis();

        // Insert into database in background
        new Thread(() -> db.notificationDao().insert(data)).start();

        Log.d("NotificationListener", "Captured: " + title + " - " + text);
    }
}

