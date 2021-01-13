package com.bishoyosama.metroguide;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MetroViewModel extends AndroidViewModel {
    private MetroRepo metroRepo;
    private LiveData<List<TripInfo>> allMetro;

    public MetroViewModel(@NonNull Application application) {
        super(application);
        metroRepo = new MetroRepo(application);
        allMetro=metroRepo.getAllMetro();
    }
    public void insert (TripInfo ti){
        metroRepo.insert(ti);

    }

    public void delete (TripInfo ti){
        metroRepo.delete(ti);

    }
    public void deleteAllMetro (){
        metroRepo.deleteAllMetro();

    }
    public LiveData<List<TripInfo>> getAllMetro(){
        return allMetro;
    }
}
