package com.example.leagueapp.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FreeChampionRepository {
    private static final String TAG = FreeChampionRepository.class.getSimpleName();
    private static final String BASE_URL = "https://na1.api.riotgames.com/lol/platform/v3/";

    private MutableLiveData<FreeChampionData> freeChampionData;

    private RiotGameService riotGameService;

    public FreeChampionRepository() {
        this.freeChampionData = new MutableLiveData<>();
        this.freeChampionData.setValue(null);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.riotGameService = retrofit.create(RiotGameService.class);
    }

    public LiveData<FreeChampionData> getFreeChampionData() {return this.freeChampionData; }

    public void loadFreeChampionData(String apiKey) {
        Call<FreeChampionData> req = this.riotGameService.fetchFreeChampionData(apiKey);
        req.enqueue(new Callback<FreeChampionData>() {
            @Override
            public void onResponse(Call<FreeChampionData> call, Response<FreeChampionData> response) {
                Log.d(TAG, "Callback got response with the URL: " + call.request().url());
                if (response.code() == 200) {
                    freeChampionData.setValue(response.body());
                    Log.d(TAG, "Set the freeChampionData with the response" + freeChampionData.getValue());
                } else {
                    Log.d(TAG, "unsuccessful API request: " + call.request().url());
                    Log.d(TAG, "  -- response status code: " + response.code());
                    Log.d(TAG, "  -- response: " + response.toString());
                }
            }

            @Override
            public void onFailure(Call<FreeChampionData> call, Throwable t) {
                Log.d(TAG, "unsuccessful API request: " + call.request().url());
                t.printStackTrace();
            }
        });
    }
}


