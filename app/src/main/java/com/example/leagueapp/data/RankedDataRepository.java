package com.example.leagueapp.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RankedDataRepository {

    private String region = "na1";
    private String id = "X-NDYFVDo_C02eVWZSxpFDFhHnULlYxs7LF5L4JrVdLAwI4";

    private static final String TAG = RankedDataRepository.class.getSimpleName();

    private String BASE_URL = "https://"+region+".api.riotgames.com/lol/league/v4/entries/";

    private MutableLiveData<RankedData> rankedData;

    private RiotGameService riotGameService;

    public RankedDataRepository(){
        this.rankedData = new MutableLiveData<>();
        this.rankedData.setValue(null);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.riotGameService = retrofit.create(RiotGameService.class);
    }

    public LiveData<RankedData> getRankedData() {return this.rankedData; }

    public void loadRankedData(String apiKey, String id) {
        Call<RankedData> req = this.riotGameService.fetchRankedData(apiKey, id);
        req.enqueue(new Callback<RankedData>() {
            @Override
            public void onResponse(Call<RankedData> call, Response<RankedData> response) {
                Log.d(TAG, "Callback got response with the URL: " + call.request().url());
                if (response.code() == 200) {
                    rankedData.setValue(response.body());
                    Log.d(TAG, "Set the AccountData with the response" + rankedData.getValue().toString());
//                    Log.d(TAG, rankedData.getValue().queueType);
//                    Log.d(TAG, rankedData.getValue().rank);
//                    Log.d(TAG, rankedData.getValue().tier);
//                    Log.d(TAG, rankedData.getValue().wins.toString());
//                    Log.d(TAG, rankedData.getValue().losses.toString());
//                    Log.d(TAG, rankedData.getValue().leaguePoints.toString());

                } else {
                    Log.d(TAG, "unsuccessful API request: " + call.request().url());
                    Log.d(TAG, "  -- response status code: " + response.code());
                    Log.d(TAG, "  -- response: " + response.toString());
                }
            }

            @Override
            public void onFailure(Call<RankedData> call, Throwable t) {
                Log.d(TAG, "unsuccessful API request: " + call.request().url());
                t.printStackTrace();
            }
        });
    }
}
