package com.example.leagueapp.data;

import com.google.gson.annotations.SerializedName;

public class ChampionData {
    private final String CHAMPION_ICON_URL = "https://ddragon.leagueoflegends.com/cdn/11.5.1/img/champion/";

    private int key;
    private String name;
    private String title;
    private String imageName;

    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageURl() {
        return CHAMPION_ICON_URL + imageName;
    }
}
