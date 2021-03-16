package com.example.leagueapp.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccountChampionDataRepository {

    private String region = "na1";
    private String id = "X-NDYFVDo_C02eVWZSxpFDFhHnULlYxs7LF5L4JrVdLAwI4";

    private static final String TAG = RankedDataRepository.class.getSimpleName();

    private String BASE_URL = "https://"+region+".api.riotgames.com/lol/champion-mastery/v4/champion-masteries/";

    private MutableLiveData<List<AccountChampionData>> accountChampionData;

    private RiotGameService riotGameService;

    public AccountChampionDataRepository(){
        this.accountChampionData = new MutableLiveData<>();
        this.accountChampionData.setValue(null);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.riotGameService = retrofit.create(RiotGameService.class);
    }

    public LiveData<List<AccountChampionData>> getAccountChampionData() {return this.accountChampionData; }

    public void loadAccountChampionData(String apiKey, String id) {
        Log.d(TAG,"LOAD ACCOUNT CHAMPION DATA FUNCTION WAS CALLED");
        Call<List<AccountChampionData>> req = this.riotGameService.fetchAccountChampionData(apiKey, id);
        req.enqueue(new Callback<List<AccountChampionData>>() {
            @Override
            public void onResponse(Call<List<AccountChampionData>> call, Response<List<AccountChampionData>> response) {
                Log.d(TAG, "Callback got response with the URL: " + call.request().url());
                if (response.code() == 200) {
                    accountChampionData.setValue(response.body());
                    Log.d(TAG, "Response code was 200");
                    Log.d(TAG, "Printing the body of the response: " + response.body());
                } else {
                    Log.d(TAG, "unsuccessful API request: " + call.request().url());
                    Log.d(TAG, "  -- response status code: " + response.code());
                    Log.d(TAG, "  -- response: " + response.toString());
                }
            }

            @Override
            public void onFailure(Call<List<AccountChampionData>> call, Throwable t) {
                Log.d(TAG, "unsuccessful API request: " + call.request().url());
                t.printStackTrace();
            }
        });
    }
}
