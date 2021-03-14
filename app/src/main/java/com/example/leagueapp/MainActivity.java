package com.example.leagueapp;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.leagueapp.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {
    private static final String apiKey = "RGAPI-b86321c0-3198-40b2-b1fd-6f15aebf2bfc";

    private FreeChampionViewModel freeChampionViewModel;
    private AccountDataViewModel accountDataViewModel;
    private RankedDataViewModel rankedDataViewModel;

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

        this.accountDataViewModel = new ViewModelProvider(this)
                .get(AccountDataViewModel.class);

        this.accountDataViewModel.loadAccountData(apiKey, "C9 Zven");

        this.rankedDataViewModel = new ViewModelProvider(this)
                .get(RankedDataViewModel.class);

        this.rankedDataViewModel.loadRankedData( apiKey,"X-NDYFVDo_C02eVWZSxpFDFhHnULlYxs7LF5L4JrVdLAwI4");


    }
}