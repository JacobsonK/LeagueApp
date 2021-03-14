package com.example.leagueapp;

import android.accounts.Account;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.leagueapp.data.AccountData;
import com.example.leagueapp.data.AccountDataRepository;
import com.example.leagueapp.data.RankedData;
import com.example.leagueapp.data.RankedDataRepository;

import java.security.PrivilegedAction;

public class RankedDataViewModel extends ViewModel{
    private RankedDataRepository repository;
    private LiveData<RankedData> rankedData;

    public RankedDataViewModel() {
        this.repository = new RankedDataRepository();
        rankedData = repository.getRankedData();
    }

    public LiveData<RankedData> getRankedData() { return this.rankedData; }

    public void loadRankedData(String apiKey, String id) {
        this.repository.loadRankedData(id,apiKey);
    }
}
