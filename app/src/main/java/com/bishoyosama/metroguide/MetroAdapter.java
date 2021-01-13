package com.bishoyosama.metroguide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MetroAdapter extends RecyclerView.Adapter<MetroAdapter.MetroHolder>{
    private List<TripInfo> tripInfos = new ArrayList<>();

    @NonNull
    @Override
    public MetroHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tripinfo_item, parent, false);
        return new MetroHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MetroHolder holder, int position) {
        TripInfo currentTi = tripInfos.get(position);
        holder.textViewSource.setText(currentTi.source);
        holder.textViewDestination.setText(currentTi.destination);
        holder.textViewDatetime.setText(currentTi.datetime);

    }

    @Override
    public int getItemCount() {
        return tripInfos.size();
    }
    public void setTripInfos(List<TripInfo> tripInfos) {
        this.tripInfos = tripInfos;
        notifyDataSetChanged();
    }

    class MetroHolder extends RecyclerView.ViewHolder {
        private TextView textViewSource;
        private TextView textViewDestination;
        private TextView textViewDatetime;
        public MetroHolder(View itemView) {
            super(itemView);
            textViewSource = itemView.findViewById(R.id.text_view_source);
            textViewDestination = itemView.findViewById(R.id.text_view_destination);
            textViewDatetime = itemView.findViewById(R.id.text_view_datetime);
        }
    }
}
