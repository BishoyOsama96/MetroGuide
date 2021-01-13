package com.bishoyosama.metroguide;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bishoyosama.metroguide.MetroAdapter;

import java.util.List;

public class History extends AppCompatActivity {
    private com.bishoyosama.metroguide.MetroViewModel metroViewModel;
    MetroAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_history);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter=new MetroAdapter();
        recyclerView.setAdapter(adapter);
        metroViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(com.bishoyosama.metroguide.MetroViewModel.class);
        metroViewModel.getAllMetro().observe(this,new Observer<List<com.bishoyosama.metroguide.TripInfo>>(){

            @Override
            public void onChanged(List<com.bishoyosama.metroguide.TripInfo> tripInfos) {
                adapter.setTripInfos(tripInfos);
            }
        });
    }
}