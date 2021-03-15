package com.example.leagueapp.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AccountData implements Serializable {
    private final String SUMMONER_ICON_URL = "https://ddragon.leagueoflegends.com/cdn/11.5.1/img/profileicon/";

    public String id;
    public String name;
    public Integer summonerLevel;
    public Integer profileIconId;

    public String getId() { return this.id; }
    public String getName() { return this.name; }
    public String getSummonerIconURL() {
        return SUMMONER_ICON_URL +profileIconId + ".png";
    }
}
