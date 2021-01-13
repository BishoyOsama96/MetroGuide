package com.bishoyosama.metroguide;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MetroDAO {
    @Insert
    void insert(TripInfo ti);
    @Delete
    void delete(TripInfo ti);

    // delete all rows
    @Query("DELETE FROM TripInfo")
    void deleteAllMetro();

    @Query ("SELECT * FROM TripInfo ORDER BY datetime DESC")
    //List<TripInfo> getAllRows();
    LiveData<List<TripInfo>> getAllMetro();

}
