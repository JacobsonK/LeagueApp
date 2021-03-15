package com.example.leagueapp.ui.main;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leagueapp.FreeChampionAdapter;
import com.example.leagueapp.FreeChampionViewModel;
import com.example.leagueapp.MainActivity;
import com.example.leagueapp.R;
import com.example.leagueapp.data.ChampionData;
import com.example.leagueapp.data.FreeChampionData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment implements FreeChampionAdapter.OnFreeChampionClickListener {
    private static final String TAG = PlaceholderFragment.class.getSimpleName();
    private static final String apiKey = "RGAPI-a40425ab-ffc3-496e-884b-8ee4a8f1d936";

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    private RecyclerView freeChampionsRV;
    private FreeChampionAdapter freeChampionAdapter;

    private FreeChampionViewModel freeChampionViewModel;
    private ArrayList<Integer> freeChampionList;
    private ArrayList<ChampionData> championDataList;
    private TextView banner;

    private PackageManager packageManager;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root;
        switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
            case 1:
                root = inflater.inflate(R.layout.fragment_main, container, false);
                final TextView textView = root.findViewById(R.id.section_label);
                banner = root.findViewById(R.id.tv_banner);
                banner.setText("League App");
                return root;
            case 2:
                root = inflater.inflate(R.layout.fragment_champion, container, false);
                this.freeChampionsRV = root.findViewById(R.id.rv_free_champion);
                this.freeChampionsRV.setLayoutManager(new LinearLayoutManager(root.getContext()));
                this.freeChampionsRV.setHasFixedSize(true);

                this.packageManager = getContext().getPackageManager();

                this.freeChampionAdapter = new FreeChampionAdapter(this);
                this.freeChampionsRV.setAdapter(freeChampionAdapter);

                this.freeChampionViewModel = new ViewModelProvider(this)
                        .get(FreeChampionViewModel.class);

                championDataList = new ArrayList<>();

                this.freeChampionViewModel.loadFreeChampionData(apiKey);
                this.freeChampionViewModel.getFreeChampionData().observe(
                        getViewLifecycleOwner(),
                        new Observer<FreeChampionData>() {
                            @Override
                            public void onChanged(FreeChampionData freeChampionData) {
                                if (freeChampionData != null) {
                                    Log.d(TAG,"Got the data from the api request");
                                    freeChampionList = freeChampionData.getFreeChampionList();
                                    if (championDataList.isEmpty()) {
                                        try {
                                            setChampionDataList();
                                            freeChampionAdapter.updateChampionData(championDataList);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        }
                );

                return root;
        }
        return null;
    }


    public String readJSON() {
        String json = null;
        try {
            // Opening data.json file
            InputStream inputStream = getContext().getAssets().open("Champions.json");
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

    @Override
    public void onFreeChampionClick(ChampionData champion) {
        Log.d(TAG, "The champion that was clicked is: " + champion.getName());

        Intent intent = new Intent(Intent.ACTION_SEARCH);
        intent.setPackage("com.google.android.youtube");
        intent.putExtra(
                "query",
                champion.getName() + " champion spotlight");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}