package com.example.leagueapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leagueapp.data.ChampionData;
import com.example.leagueapp.data.FreeChampionData;

import java.util.ArrayList;

public class FreeChampionAdapter extends RecyclerView.Adapter<FreeChampionAdapter.FreeChampionViewHolder> {
    private ArrayList<ChampionData> championData;

    public FreeChampionAdapter() {
        this.championData = new ArrayList<>();
    }

    @NonNull
    @Override
    public FreeChampionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.free_champion_list_item, parent, false);
        return new FreeChampionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FreeChampionViewHolder holder, int position) {
        holder.bind(this.championData.get(position).getName());
    }

    public void updateChampionData(ArrayList<ChampionData> championData) {
        this.championData = championData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (championData == null) {
            return 0;
        } else {
            return championData.size();
        }
    }

    public class FreeChampionViewHolder extends RecyclerView.ViewHolder {
        final private TextView champName;

        public FreeChampionViewHolder(@NonNull View itemView) {
            super(itemView);
            champName = itemView.findViewById(R.id.tv_free_champion_name);
        }

        public void bind(String name) {
            champName.setText(name);
        }
    }
}
