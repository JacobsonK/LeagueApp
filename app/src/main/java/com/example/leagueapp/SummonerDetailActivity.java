package com.example.leagueapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.leagueapp.data.AccountData;
import com.example.leagueapp.data.RankedData;

import java.util.ArrayList;
import java.util.List;

public class SummonerDetailActivity extends AppCompatActivity {

    public static final String EXTRA_ACCOUNT_DATA = "SummonerDetailActivity.AccountData";
    public static final String EXTRA_RANKED_DATA = "SummonerDetailActivity.RankedData";
    private AccountData accountData;
    private ArrayList<RankedData> rankedData;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summoner_detail);

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra(EXTRA_ACCOUNT_DATA)) {
            this.accountData = (AccountData) intent.getSerializableExtra(EXTRA_ACCOUNT_DATA);
            TextView summonerNameTV = findViewById(R.id.tv_summoner_name);
            summonerNameTV.setText(this.accountData.getName());
        }
        if (intent != null && intent.hasExtra(EXTRA_RANKED_DATA)) {
            this.rankedData = (ArrayList<RankedData>) intent.getSerializableExtra(EXTRA_RANKED_DATA);
            TextView rankedWinsTV = findViewById(R.id.tv_ranked_wins);
            rankedWinsTV.setText(this.rankedData.get(0).wins.toString());
            rankedWinsTV.setTextColor(Color.GREEN);

            TextView rankedLossesTV = findViewById(R.id.tv_ranked_losses);
            rankedLossesTV.setText(this.rankedData.get(0).losses.toString());
            rankedLossesTV.setTextColor(Color.RED);

            TextView rankTV = findViewById(R.id.tv_rank);
            rankTV.setText(this.rankedData.get(0).rank);

            TextView divisionTV = findViewById(R.id.tv_division);
            divisionTV.setText(this.rankedData.get(0).tier);

            ImageView summonerIconIV = findViewById(R.id.iv_summoner_icon);
            Glide.with(this).load(accountData.getSummonerIconURL()).into(summonerIconIV);
        }
    }

}
