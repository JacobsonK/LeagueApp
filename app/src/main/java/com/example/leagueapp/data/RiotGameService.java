package com.example.leagueapp.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RiotGameService {
    @GET("champion-rotations")
    Call<FreeChampionData> fetchFreeChampionData(@Query("api_key") String apiKey);

    @GET("by-name/{accountName}")
    Call<AccountData> fetchAccountData(@Path("accountName") String accountName, @Query("api_key") String apiKey);

    @GET("by-summoner/{id}")
    Call<RankedData> fetchRankedData(@Path("id") String id, @Query("api_key") String apiKey);

}
