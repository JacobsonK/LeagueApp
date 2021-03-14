package com.example.leagueapp;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.leagueapp.data.ChampionData;
import com.example.leagueapp.data.FreeChampionData;
import com.example.leagueapp.ui.main.PlaceholderFragment;

import java.util.ArrayList;

public class FreeChampionAdapter extends RecyclerView.Adapter<FreeChampionAdapter.FreeChampionViewHolder> {
    private ArrayList<ChampionData> championData;
    private OnFreeChampionClickListener onFreeChampionClickListener;

    public interface OnFreeChampionClickListener {
        void onFreeChampionClick(ChampionData champion);
    }

    public FreeChampionAdapter(OnFreeChampionClickListener onFreeChampionClickListener) {
        this.championData = new ArrayList<>();
        this.onFreeChampionClickListener = onFreeChampionClickListener;
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
        holder.bind(this.championData.get(position));
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
        final private ImageView champIcon;

        public FreeChampionViewHolder(@NonNull View itemView) {
            super(itemView);
            champName = itemView.findViewById(R.id.tv_free_champion_name);
            champIcon = itemView.findViewById(R.id.iv_champion_icon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onFreeChampionClickListener.onFreeChampionClick(
                            championData.get(getAdapterPosition())
                    );
                }
            });
        }

        public void bind(ChampionData champion) {
            Context ctx = this.itemView.getContext();

            champName.setText(champion.getName());
            Glide.with(ctx).load(champion.getImageURl()).into(champIcon);
        }
    }
}
