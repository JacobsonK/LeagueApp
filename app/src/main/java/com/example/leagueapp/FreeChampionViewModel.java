package com.example.leagueapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.leagueapp.data.FreeChampionData;
import com.example.leagueapp.data.FreeChampionRepository;

import java.security.PrivilegedAction;

public class FreeChampionViewModel extends ViewModel {
    private FreeChampionRepository repository;
    private LiveData<FreeChampionData> freeChampionData;

    public FreeChampionViewModel() {
        this.repository = new FreeChampionRepository();
        freeChampionData = repository.getFreeChampionData();
    }

    public LiveData<FreeChampionData> getFreeChampionData() { return this.freeChampionData; }

    public void loadFreeChampionData(String apiKey) {
        this.repository.loadFreeChampionData(apiKey);
    }
}
