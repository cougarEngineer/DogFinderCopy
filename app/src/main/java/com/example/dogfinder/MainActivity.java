package com.example.dogfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Contains links to List, Add, and SignIn Activities
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Sets UI from activity_main.xml
     * @param savedInstanceState Required for onCreate event
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Transfers user to ListActivity
     * @param view Required for onClick event
     */
    public void showList(View view) {
        Intent i = new Intent(this, ListActivity.class);
        startActivity(i);
    }

    /**
     * Transfers user to AddActivity
     * @param view Required for onClick event
     */
    public void showAdd(View view) {
        Intent i = new Intent(this, AddActivity.class);
        startActivity(i);
    }

    /**
     * Transfers user to SignInActivity
     * @param view Required for onClick event
     */
    public void showSignIn(View view) {
        Intent i = new Intent(this, FirebaseUIActivity.class);
        startActivity(i);
    }
}

