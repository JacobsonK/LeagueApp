package com.example.leagueapp.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RiotGameService {
    @GET("champion-rotations")
    Call<FreeChampionData> fetchFreeChampionData(@Query("api_key") String apiKey);
}
