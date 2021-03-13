package com.example.leagueapp;

import android.os.Bundle;

import com.example.leagueapp.data.FreeChampionData;
import com.example.leagueapp.data.FreeChampionRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.leagueapp.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String apiKey = "RGAPI-71cdd8bc-5f6a-4e2f-9a1e-603ef25ee31c";

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