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
    private static final String apiKey = "RGAPI-c6a345a8-0b30-4bba-86cf-10114dd660de";

    private FreeChampionViewModel freeChampionViewModel;
    private ArrayList<Integer> freeChampionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        this.freeChampionViewModel = new ViewModelProvider(this)
                .get(FreeChampionViewModel.class);

        this.freeChampionViewModel.loadFreeChampionData(apiKey);
        this.freeChampionViewModel.getFreeChampionData().observe(
                this,
                new Observer<FreeChampionData>() {
                    @Override
                    public void onChanged(FreeChampionData freeChampionData) {
                        if (freeChampionData != null) {
                            Log.d(TAG,"Got the data from the database");
                            freeChampionList = freeChampionData.getFreeChampionList();
                            Log.d(TAG, "The id at index 1 is: " + freeChampionList);
                        }
                    }
                }
        );
    }
}