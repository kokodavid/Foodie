package com.koko.foodie.Activities.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.koko.foodie.Activities.Favorite.Favorite;
import com.koko.foodie.Activities.Favorite.FavoriteDAO;

@Database(entities = {Favorite.class}, version = 1)
public abstract class FoodieRoomDB extends RoomDatabase {


    public abstract FavoriteDAO favoriteDAO();

    private static FoodieRoomDB instance;

    public static FoodieRoomDB getInstance(Context context) {
        if (instance == null)
            instance = Room.databaseBuilder(context, FoodieRoomDB.class,"FoodieRoomDB")
                    .allowMainThreadQueries()
                    .build();
        return instance;
    }
}
