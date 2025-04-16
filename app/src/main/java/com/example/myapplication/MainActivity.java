package com.example.myapplication;

import android.content.Intent;
import android.provider.Settings;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NotificationAdapter adapter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Ask for notification access if not already granted
        if (!isNotificationServiceEnabled()) {
            Intent intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
            startActivity(intent);
            Toast.makeText(this, "Please enable notification access", Toast.LENGTH_LONG).show();
        }

        // Load DB
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "noti-db")
                .allowMainThreadQueries() // OK for demo, not production
                .build();

        loadNotifications();
    }

    private void loadNotifications() {
        List<NotificationData> notifications = db.notificationDao().getAll();
        adapter = new NotificationAdapter(notifications);
        recyclerView.setAdapter(adapter);
    }

    private boolean isNotificationServiceEnabled() {
        String pkgName = getPackageName();
        String enabledListeners = Settings.Secure.getString(
                getContentResolver(), "enabled_notification_listeners"
        );
        return enabledListeners != null && enabledListeners.contains(pkgName);
    }
}

