package com.example.leagueapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.leagueapp.data.RankedData;
import com.example.leagueapp.data.RankedDataRepository;

import java.util.List;

public class RankedDataViewModel extends ViewModel{
    private RankedDataRepository repository;
    private LiveData<List<RankedData>> rankedData;

    public RankedDataViewModel() {
        this.repository = new RankedDataRepository();
        rankedData = repository.getRankedData();
    }

    public LiveData<List<RankedData>> getRankedData() { return this.rankedData; }

    public void loadRankedData(String apiKey, String id) {
        this.repository.loadRankedData(id,apiKey);
    }
}
