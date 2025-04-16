package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface NotificationDao {

    @Insert
    void insert(NotificationData notification);

    @Query("SELECT * FROM notifications ORDER BY timestamp DESC")
    List<NotificationData> getAll();
}

