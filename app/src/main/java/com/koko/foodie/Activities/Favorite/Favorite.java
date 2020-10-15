package com.koko.foodie.Activities.Favorite;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Favorite")
public class Favorite {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    public String ID;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "image")
    public String image;
}
