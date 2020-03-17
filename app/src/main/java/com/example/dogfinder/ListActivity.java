package com.example.dogfinder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ListActivity holds code to implement the behavior of a screen with a list of profiles,
 * and filtering options for that
 */
public class ListActivity extends AppCompatActivity {

    private List<DogProfile> dogProfiles = new ArrayList<>();
    private List<DogProfile> filteredDogProfiles = new LinkedList<>();
    private static final String ANY_SEL_TEXT = "(Any)";
    private Set<String> states = new HashSet<>();
    private Set<String> cities = new HashSet<>();
    private Gson gson = new Gson();
    private FirebaseFirestore db;
    private ListView listView;
    private Spinner stateSpinner;
    private Spinner citySpinner;


    /**
     * Runs on creation of the activity
     *
     * @param savedInstanceState contains info from previous instances of this activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //grabbing all ui items
        listView = findViewById(R.id.profileList);
        stateSpinner = findViewById(R.id.stateSpinner);
        citySpinner = findViewById(R.id.citySpinner);
        db = FirebaseFirestore.getInstance();

        //doing firebase getting here now
        db.collection("profiles").get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Map<String, Object> data = document.getData();
                            dogProfiles.add(gson.fromJson((String) data.get("profile"), DogProfile.class));
                            Log.d("database get", document.getId() + " => " + document.getData());
                        }

                        doWhenDataGot();
                    } else {
                        Log.d("database get", "Error getting documents: ", task.getException());
                    }
                });
        //listener for when a state is selected to populate the city dropdown
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

    //is called after successful retrieval of data
    private void doWhenDataGot() {
        filteredDogProfiles = new LinkedList<>(dogProfiles);

        //initial listview creation
        Log.d("ListActivity", "starting listView creation");
        ListAdapter adapter = new CustomListAdapter(this, R.layout.custom_list_template, filteredDogProfiles);
        listView.setAdapter(adapter);
        Log.d("ListActivity", "adapter count: " + adapter.getCount());
        Log.d("ListActivity", "listView populated");

        //initial dropdowns creation;
        states.add(ANY_SEL_TEXT);
        cities.add(ANY_SEL_TEXT);
        populateDropState();
        setCitySpinner();

        //show dog profile after clicking it on the list
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent iP = new Intent(new Intent(ListActivity.this, ShowProfileActivity.class));
                startActivity(iP);
            }
        });
    }


    /**
     * Determines what code is executed when the Filter button is pressed
     * @param view Required for onClick methods
     */
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
        //update listview when filter button pressed
        ListAdapter adapter = new CustomListAdapter(this, R.layout.custom_list_template, filteredDogProfiles);
        listView.setAdapter(adapter);
    }

    //will populate dropdowns with relevant location data
    private void populateDropState() {
        Log.d("ListActivity", "adding state drops: " + dogProfiles.size() + " dogProfiles");
        int i = 0;
        for (DogProfile dp : dogProfiles) {
            if (dp.getLocation() == null || dp.getLocation().getState().equals("")) {
                continue;
            } else {
                states.add(dp.getLocation().getState());
            }
        }
        setStateSpinner();
    }

    private void populateDropCity(String item) {
        if (!item.equals(ANY_SEL_TEXT)) {
            cities.clear();
            cities.add(ANY_SEL_TEXT);
            for (DogProfile dp : dogProfiles) {
                if (dp.getLocation() != null && dp.getLocation().getState().equals(item)) {
                    cities.add(dp.getLocation().getCity());
                }
            }

        } else {
            cities.clear();
            cities.add(ANY_SEL_TEXT);
        }
        setCitySpinner();
    }

    private void setStateSpinner() {
        String[] statesArr = states.toArray(new String[0]);
        Arrays.sort(statesArr);
        ArrayAdapter<String> stateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, statesArr);
        stateSpinner.setAdapter(stateAdapter);
        stateSpinner.setSelection(0);
    }

    private void setCitySpinner() {
        String[] citiesArr = cities.toArray(new String[0]);
        Arrays.sort(citiesArr);
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, citiesArr);
        citySpinner.setAdapter(cityAdapter);
        citySpinner.setSelection(0);
    }
}
