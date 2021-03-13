package com.example.leagueapp;

import android.content.Intent;
import android.os.Bundle;

import com.example.leagueapp.data.ChampionData;
import com.example.leagueapp.data.FreeChampionData;
import com.example.leagueapp.data.FreeChampionRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Debug;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.leagueapp.ui.main.SectionsPagerAdapter;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String apiKey = "RGAPI-a40425ab-ffc3-496e-884b-8ee4a8f1d936";

    private FreeChampionViewModel freeChampionViewModel;
    private ArrayList<Integer> freeChampionList;
    private ArrayList<ChampionData> championDataList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        this.freeChampionViewModel = new ViewModelProvider(this)
                .get(FreeChampionViewModel.class);

        championDataList = new ArrayList<>();

        this.freeChampionViewModel.loadFreeChampionData(apiKey);
        this.freeChampionViewModel.getFreeChampionData().observe(
                this,
                new Observer<FreeChampionData>() {
                    @Override
                    public void onChanged(FreeChampionData freeChampionData) {
                        if (freeChampionData != null) {
                            Log.d(TAG,"Got the data from the api request");
                            freeChampionList = freeChampionData.getFreeChampionList();
                            if (championDataList.isEmpty()) {
                                try {
                                    setChampionDataList();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
        );

    }


    public String readJSON() {
        String json = null;
        try {
            // Opening data.json file
            InputStream inputStream = getAssets().open("Champions.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            // read values in the byte array
            inputStream.read(buffer);
            inputStream.close();
            // convert byte to string
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return json;
        }
        return json;
    }

    public void setChampionDataList() throws JSONException {
        try {
            JSONObject object = new JSONObject(readJSON());
            JSONObject championDataObject = object.getJSONObject("data");
//            Log.d(TAG, "Getting champion data: " + championDataObject.length());
            JSONArray championDataArray = championDataObject.toJSONArray(championDataObject.names());
//            Log.d(TAG, "The size of champion data array is : " + championDataArray.length());


//            Log.d(TAG, "The size of freeChampionList inside the JSON try loop is: " + freeChampionList.size());
            for (int i = 0; i < championDataArray.length(); i++) {
//                Log.d(TAG, "The name of the champion at i is: " + championDataArray.getJSONObject(i).getString("name"));
                JSONObject champImageObj = (JSONObject) championDataArray.getJSONObject(i).get("image");
//                Log.d(TAG, "The string inside full is: " + champImageObj.get("full"));
                JSONObject jsonObject = championDataArray.getJSONObject(i);
                String key = jsonObject.getString("key");
                String name = jsonObject.getString("name");
                String title = jsonObject.getString("title");
                String imageName = champImageObj.getString("full");

                for (int j = 0; j < freeChampionList.size(); j++) {
                    int championKey = Integer.parseInt(key);
                    if (championKey == freeChampionList.get(j)) {
                        ChampionData champion = new ChampionData();

                        champion.setKey(championKey);
                        champion.setName(name);
                        champion.setTitle(title);
                        champion.setImageName(imageName);

                        championDataList.add(champion);
                    }
                }
            }
//            Log.d(TAG, "The size of championDataList is: " + championDataList.size());
//            Log.d(TAG, "These are the free champions for the week: ");
//            for (int k = 0; k < championDataList.size(); k++) {
//                Log.d("Free Champ", "index " + k + " : " + championDataList.get(k).getName());
//            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}