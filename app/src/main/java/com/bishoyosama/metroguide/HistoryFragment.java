package com.bishoyosama.metroguide;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class HistoryFragment extends Fragment {
    String s,d;
    private com.bishoyosama.metroguide.MetroViewModel metroViewModel;
    MetroAdapter adapter;

    public HistoryFragment() {
        // Required empty public constructor
    }

    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();

        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        // setContentView(R.layout.fragment_history);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        adapter=new MetroAdapter();
        recyclerView.setAdapter(adapter);
        metroViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(com.bishoyosama.metroguide.MetroViewModel.class);
        metroViewModel.getAllMetro().observe(this,new Observer<List<TripInfo>>(){

            @Override
            public void onChanged(List<com.bishoyosama.metroguide.TripInfo> tripInfos) {
                adapter.setTripInfos(tripInfos);
            }
        });
        return view;

    }
    public void sent(String start,String destination){



    }
}