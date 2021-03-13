package com.example.leagueapp.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leagueapp.FreeChampionAdapter;
import com.example.leagueapp.R;
import com.example.leagueapp.data.ChampionData;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    private RecyclerView freeChampionsRV;
    private FreeChampionAdapter freeChampionAdapter;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root;
        switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
            case 1:
                root = inflater.inflate(R.layout.fragment_main, container, false);
                final TextView textView = root.findViewById(R.id.section_label);
                textView.setText("This is case 1");
                return root;
            case 2:
                root = inflater.inflate(R.layout.fragment_champion, container, false);
                this.freeChampionsRV = root.findViewById(R.id.rv_free_champion);
                this.freeChampionsRV.setLayoutManager(new LinearLayoutManager(root.getContext()));
                this.freeChampionsRV.setHasFixedSize(true);

                this.freeChampionAdapter = new FreeChampionAdapter();
                this.freeChampionsRV.setAdapter(freeChampionAdapter);

                ArrayList<ChampionData> tempChampDataList = new ArrayList<>();

                ChampionData champion1 = new ChampionData();
                champion1.setKey(0);
                champion1.setName("Aatrox");
                champion1.setTitle("the Darkin Blade");
                champion1.setImageName("Aatrox.png");

                ChampionData champion2 = new ChampionData();
                champion2.setKey(1);
                champion2.setName("Ahri");
                champion2.setTitle("Blade");
                champion2.setImageName("2.png");

                ChampionData champion3 = new ChampionData();
                champion3.setKey(2);
                champion3.setName("Akali");
                champion3.setTitle("stuff");
                champion3.setImageName("3.png");

                tempChampDataList.add(champion1);
                tempChampDataList.add(champion2);
                tempChampDataList.add(champion3);

                freeChampionAdapter.updateChampionData(tempChampDataList);

                return root;
        }
        return null;
    }
}