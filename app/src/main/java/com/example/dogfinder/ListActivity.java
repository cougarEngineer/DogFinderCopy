package com.example.dogfinder;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    private List<DogProfile> dogProfiles;
    private boolean dataReady = false;
    private List<DogProfile> filteredDogProfiles;

    private DogProfileDatabaseInteractor dpdi = new DogProfileDatabaseInteractor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Log.d(this.toString(), "starting retrieval");
        new getDataTask().execute(); //get all data with getDataTask (below)

        //TODO: Brigham, you should be able to hook up your listviewadapter to filteredDogProfiles here

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //will populate dropdowns with relevant location data
    private void populateDrop() {
        /* TODO: Michael */
    }

    //will change filteredDogProfiles to exclude those filtered out
    private void onClickFilterButton() {
        //todo: michael
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
            filteredDogProfiles = dogProfiles;
            Log.d(this.toString(), "DogProfiles retrieved");
            populateDrop();
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
