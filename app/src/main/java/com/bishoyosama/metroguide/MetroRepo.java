package com.bishoyosama.metroguide;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;

import java.util.List;

import javax.xml.transform.Result;

public class MetroRepo {
    private MetroDAO metroDAO;
    private LiveData<List<TripInfo>> allMetro;
    public MetroRepo(Application application){
        MetroDB db = MetroDB.getInstance(application);
        metroDAO=db.metroDAO();
        allMetro=metroDAO.getAllMetro();

    }
    public void insert(TripInfo ti) {
        new InsertTripInfoAsyncTask(metroDAO).execute(ti);

    }
    public void delete(TripInfo ti){
        new DeleteTripInfoAsyncTask(metroDAO).execute(ti);

    }
    public void deleteAllMetro(){
        new DeleteAllTripInfoAsyncTask(metroDAO).execute();
    }
    public LiveData<List<TripInfo>> getAllMetro(){
        return allMetro;

    }
    private static class InsertTripInfoAsyncTask extends AsyncTask<TripInfo,Void,Void>{
        private MetroDAO metroDAO;

        private InsertTripInfoAsyncTask(MetroDAO metroDAO){
            this.metroDAO=metroDAO;
            
        }

        @Override
        protected Void doInBackground(TripInfo... tripInfos) {
            metroDAO.insert(tripInfos[0]);
            return null;
        }
    }

    private static class DeleteTripInfoAsyncTask extends AsyncTask<TripInfo,Void,Void>{
        private MetroDAO metroDAO;

        private DeleteTripInfoAsyncTask(MetroDAO metroDAO){
            this.metroDAO=metroDAO;

        }

        @Override
        protected Void doInBackground(TripInfo... tripInfos) {
            metroDAO.delete(tripInfos[0]);
            return null;
        }
    }
    private static class DeleteAllTripInfoAsyncTask extends AsyncTask<Void,Void,Void>{
        private MetroDAO metroDAO;

        private DeleteAllTripInfoAsyncTask(MetroDAO metroDAO){
            this.metroDAO=metroDAO;

        }

        @Override
        protected Void doInBackground(Void... voids) {
            metroDAO.deleteAllMetro();
            return null;
        }
    }

}

