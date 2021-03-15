package com.example.leagueapp.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccountDataRepository {
    private String region = "na1";

    private static final String TAG = AccountDataRepository.class.getSimpleName();
    private String BASE_URL = "https://"+region+".api.riotgames.com/lol/summoner/v4/summoners/";

    private MutableLiveData<AccountData> accountData;

    private RiotGameService riotGameService;

    public AccountDataRepository() {
        this.accountData = new MutableLiveData<>();
        this.accountData.setValue(null);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.riotGameService = retrofit.create(RiotGameService.class);
    }

    public LiveData<AccountData> getAccountData() {return this.accountData; }

    public void loadAccountData(String apiKey, String accountName) {
        Call<AccountData> req = this.riotGameService.fetchAccountData(apiKey, accountName);
        req.enqueue(new Callback<AccountData>() {
            @Override
            public void onResponse(Call<AccountData> call, Response<AccountData> response) {
                Log.d(TAG, "Callback got response with the URL: " + call.request().url());
                if (response.code() == 200) {
                    accountData.setValue(response.body());
                    Log.d(TAG, "Set the AccountData with the response" + accountData.getValue().toString());
                    Log.d(TAG, accountData.getValue().name);
                    Log.d(TAG, accountData.getValue().id);
                    Log.d(TAG, accountData.getValue().profileIconId.toString());
                } else {
                    Log.d(TAG, "unsuccessful API request: " + call.request().url());
                    Log.d(TAG, "  -- response status code: " + response.code());
                    Log.d(TAG, "  -- response: " + response.toString());
                }
            }

            @Override
            public void onFailure(Call<AccountData> call, Throwable t) {
                Log.d(TAG, "unsuccessful API request: " + call.request().url());
                t.printStackTrace();
            }
        });
    }
}
