package com.bishoyosama.metroguide;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="TripInfo")
public class TripInfo {
    @PrimaryKey(autoGenerate=true)
    public int id;
    public String  source,destination,datetime;
    public int stations,tickets,cost;


}
