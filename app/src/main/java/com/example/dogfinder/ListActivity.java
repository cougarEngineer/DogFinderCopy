package com.example.dogfinder;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ListActivity extends AppCompatActivity {

    private List<DogProfile> dogProfiles = new ArrayList<>();
    private List<DogProfile> filteredDogProfiles = new LinkedList<>();
    private static final String ANY_SEL_TEXT = "[Any]";
    private Set<String> states = new HashSet<>();
    private Set<String> cities = new HashSet<>();
    //    private DogProfileDatabaseInteractor dpdi = new DogProfileDatabaseInteractor();
    private Gson gson = new Gson();
    private FirebaseFirestore db;
    private ListView listView;
    private Spinner stateSpinner;
    private Spinner citySpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Log.d("ListActivity", "starting retrieval");

        listView = findViewById(R.id.profileList);
        stateSpinner = findViewById(R.id.stateSpinner);
        citySpinner = findViewById(R.id.citySpinner);
        db = FirebaseFirestore.getInstance();

        db.collection("profiles").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
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
                    }
                });


        //TODO: Brigham, you should be able to hook up your listviewadapter to filteredDogProfiles here
        //Custom List View Adapter
        /*
        NOTES: CustomListAdapter class contains the code for the Custom Adapter. A custom adapter is
        required to display complex lists. The custom_list_template.xml resource file contains the template
        for the list. This tells the listView how to display the information contained in our custom list.

        ERRORS: No build errors currently, however the getView function in the CustomListAdapter class is
        not being called. https://stackoverflow.com/questions/16338281/custom-adapter-getview-method-is-not-called
        contains some information pertaining to this issue. This is keeping the list from appearing on the screen.

        ListView listView = (ListView) findViewById(R.id.profileList);
        Log.d("ListActivity", "starting listView creation");
        ListAdapter adapter = new CustomListAdapter(this, R.layout.custom_list_template, filteredDogProfiles);
        listView.setAdapter(adapter);
        Log.d("ListActivity", "adapter count: " + adapter.getCount());
        Log.d("ListActivity", "listView populated");

*/
        /*
        //Simple List Item Test Using Simple Adapter (SUCCESSFUL)
        //Uncomment if you want to see what a normal list of strings looks like on the list screen.

        String[] testText = {"Dog1", "Dog2", "Dog3", "Dog4", "Dog5", "Dog6", "Dog7"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, testText);
        ListView listView = (ListView) findViewById(R.id.profileList);
        listView.setAdapter(adapter);
         */
/*
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
*/
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //is called after successful retrieval of data
    private void doWhenDataGot() {
        Log.d("ListActivity", "doWhenDataGot starting");

        filteredDogProfiles = new LinkedList<>(dogProfiles);
        Log.d("ListActivity getDataTas", "DogProfiles retrieved");

        populateDropState();


        //initial listview creation
        Log.d("ListActivity", "starting listView creation");
        ListAdapter adapter = new CustomListAdapter(this, R.layout.custom_list_template, filteredDogProfiles);
        listView.setAdapter(adapter);
        Log.d("ListActivity", "adapter count: " + adapter.getCount());
        Log.d("ListActivity", "listView populated");


        states.add(ANY_SEL_TEXT);
        cities.add(ANY_SEL_TEXT);
        Log.d("listActivity", "states: " + states.size() + " cities: " + states.size());

        ArrayAdapter<String> stateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, states.toArray(new String[states.size()]));
        stateSpinner.setAdapter(stateAdapter);

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
    }




    //will populate dropdowns with relevant location data
    private void populateDropState() {
        Log.d("ListActivity", "adding state drops: " + dogProfiles.size() + " dogProfiles");
        for (DogProfile dp : dogProfiles) {
            if (dp.getLocation() == null || dp.getLocation().getState().equals("")) {
                break;
            } else {
                states.add(dp.getLocation().getState());
            }
        }
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
        //update listview when filter button pressed
        ListAdapter adapter = new CustomListAdapter(this, R.layout.custom_list_template, filteredDogProfiles);
        listView.setAdapter(adapter);
    }

}
