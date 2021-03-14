package com.example.leagueapp;

import android.accounts.Account;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.leagueapp.data.AccountData;
import com.example.leagueapp.data.AccountDataRepository;

import java.security.PrivilegedAction;

public class AccountDataViewModel extends ViewModel {
    private AccountDataRepository repository;
    private LiveData<AccountData> accountData;

    public AccountDataViewModel() {
        this.repository = new AccountDataRepository();
        accountData = repository.getAccountData();
    }

    public LiveData<AccountData> getAccountData() { return this.accountData; }

    public void loadAccountData(String apiKey, String accountName) {
        this.repository.loadAccountData(accountName,apiKey);
    }
}
