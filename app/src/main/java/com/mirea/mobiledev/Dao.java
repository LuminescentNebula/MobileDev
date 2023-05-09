package com.mirea.mobiledev;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@androidx.room.Dao
public interface Dao {
    @Query("SELECT * FROM data")
    List<Data> getAll();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Data data);
}