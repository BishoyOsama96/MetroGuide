package com.bishoyosama.metroguide;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities={TripInfo.class},version=1)
public abstract class MetroDB extends RoomDatabase {
    public abstract MetroDAO metroDAO();
    // single tone design pattern
    private static MetroDB ourInstance;
    // synchronized => only one thread at the time can access the method below
    public static synchronized MetroDB getInstance(Context context)
    {
        if(ourInstance==null)
        {
            ourInstance= Room.databaseBuilder(context,
                    MetroDB.class,"metro.db")
                    .createFromAsset("db/metro.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return ourInstance;
    }

}
