package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notifications")
public class NotificationData {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String packageName;
    public String title;
    public String text;
    public long timestamp;
}
