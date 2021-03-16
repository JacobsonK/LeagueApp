package com.example.leagueapp.data;

import java.io.Serializable;

public class AccountChampionData implements Serializable {
    private final String CHAMPION_ICON_URL = "https://ddragon.leagueoflegends.com/cdn/11.5.1/img/champion/";
    public Integer championId;
    public Integer championPoints;
    public String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getChampionIconURL() {
        return CHAMPION_ICON_URL +name;
    }
}
