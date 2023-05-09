package com.mirea.mobiledev;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Data {
    @PrimaryKey
    public int uid;
    @ColumnInfo(name = "line")
    public String line;

    public Data(int uid, String line) {
        this.uid = uid;
        this.line = line;
    }

    public String getLine() {
        return line;
    }
}
