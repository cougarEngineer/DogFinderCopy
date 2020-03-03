package com.example.dogfinder;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

}
