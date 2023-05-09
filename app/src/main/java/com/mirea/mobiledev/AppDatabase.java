package com.mirea.mobiledev;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Data.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract Dao dao();
}