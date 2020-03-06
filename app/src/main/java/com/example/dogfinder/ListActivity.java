package com.example.dogfinder;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ListActivity extends AppCompatActivity {

    private List<DogProfile> dogProfiles;
    private List<DogProfile> filteredDogProfiles;
    private static final String ANY_SEL_TEXT = "[Any]";
    private Set<String> states = new HashSet<>();
    private Set<String> cities = new HashSet<>();
    private DogProfileDatabaseInteractor dpdi = new DogProfileDatabaseInteractor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Log.d(this.toString(), "starting retrieval");
        new getDataTask().execute(); //get all data with getDataTask (below)

        //TODO: Brigham, you should be able to hook up your listviewadapter to filteredDogProfiles here

        states.add(ANY_SEL_TEXT);
        cities.add(ANY_SEL_TEXT);
        Log.d("listActivity", "states: " + states.size() + " cities: " + states.size());
        Spinner stateSpinner = findViewById(R.id.stateSpinner);
        ArrayAdapter<String> stateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, states.toArray(new String[states.size()]));
        stateSpinner.setAdapter(stateAdapter);
        Spinner citySpinner = findViewById(R.id.citySpinner);
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cities.toArray(new String[cities.size()]));
        citySpinner.setAdapter(cityAdapter);
        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                populateDropCity((String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //will populate dropdowns with relevant location data
    private void populateDropState() {
        for (DogProfile dp : dogProfiles) {
            if (dp.getLocation() == null || dp.getLocation().getState().equals("")) {
                break;
            } else {
                states.add(dp.getLocation().getState());
            }
        }
    }

    private void populateDropCity(String item) {
        if (item.equals(ANY_SEL_TEXT)) {
            return;
        } else {
            cities.clear();
            cities.add(ANY_SEL_TEXT);
            for (DogProfile dp : dogProfiles) {
                if (dp.getLocation() != null && dp.getLocation().getState().equals(item)) {
                    cities.add(dp.getLocation().getCity());
                }
            }
        }
    }

    //will change filteredDogProfiles to exclude those filtered out
    public void onClickFilterButton(View view) {
        filteredDogProfiles.clear();
        String state, city;
        Spinner stateSpinner = findViewById(R.id.stateSpinner);
        Spinner citySpinner = findViewById(R.id.citySpinner);
        state = stateSpinner.getSelectedItem().toString();
        city = citySpinner.getSelectedItem().toString();
        for (DogProfile dp : dogProfiles) {
            if (state.equals(ANY_SEL_TEXT)) {
                filteredDogProfiles.add(dp);
            } else if ((dp.getLocation() != null && state.equals(dp.getLocation().getState()))) {
                if (city.equals(ANY_SEL_TEXT) || city.equals(dp.getLocation().getCity())) {
                    filteredDogProfiles.add(dp);
                }
            }
        }
    }

    //AsyncTask to get the data from DogProfileDatabaseInteractor
    private class getDataTask extends AsyncTask<Void, Void, List<DogProfile>> {

        @Override
        protected List<DogProfile> doInBackground(Void... voids) {
            return dpdi.get();
        }

        @Override
        protected void onPostExecute(List<DogProfile> dp) {
            super.onPostExecute(dogProfiles);
            dogProfiles = dp;
            filteredDogProfiles = new LinkedList<>(dp);
            Log.d(this.toString(), "DogProfiles retrieved");
            populateDropState();
        }
    }
        /*
        //These notes below can be deleted - notes to self for testing.

        //test example using a string array - will change for actual Dog Profile ArrayList
        String[] testText = {"Dog1", "Dog2", "Dog3", "Dog4", "Dog5", "Dog6", "Dog7"};
        ListAdapter profileAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, testText);
        ListView profileListView = (ListView) findViewById(R.id.profileList);
        profileListView.setAdapter(profileAdapter);

        //click item on list
        profileListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //what happens when clicked
                        //example with String
                        String dog = String.valueOf(parent.getItemAtPosition(position));
                    }
                }
        );
        */


}
