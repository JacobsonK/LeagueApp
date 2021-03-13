package com.example.leagueapp.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FreeChampionData {
    @SerializedName("freeChampionIds")
    private ArrayList<Integer> freeChampionList;

    public FreeChampionData() {
        this.freeChampionList = null;
    }

    public ArrayList<Integer> getFreeChampionList() {return  freeChampionList;}
}
