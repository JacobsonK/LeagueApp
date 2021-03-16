package com.example.leagueapp;

import android.accounts.Account;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.leagueapp.data.AccountChampionData;
import com.example.leagueapp.data.AccountChampionDataRepository;

import java.util.List;

public class AccountChampionDataViewModel extends ViewModel {
    private AccountChampionDataRepository repository;
    private LiveData<List<AccountChampionData>> accountChampionData;

    public AccountChampionDataViewModel() {
        this.repository = new AccountChampionDataRepository();
        accountChampionData = repository.getAccountChampionData();
    }

    public LiveData<List<AccountChampionData>> getAccountChampionData() { return this.accountChampionData; }

    public void loadAccountChampionData(String apiKey, String accountName) {
        this.repository.loadAccountChampionData(accountName,apiKey);
    }
}