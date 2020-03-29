package com.example.dogfinder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Objects;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * Contains links to List, Add, and SignIn Activities
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The Username.
     */
    static final String USERNAME = "username";

    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    String userID;
    String username;

    /**
     * Sets UI from activity_main.xml
     * @param savedInstanceState Required for onCreate event
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.currentUserTextView);

        if(fAuth.getCurrentUser() != null) {
            userID = fAuth.getCurrentUser().getUid();
            DocumentReference dRef = fStore.collection("users").document(userID);
            dRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    username = documentSnapshot.getString("username");
                    tv.setText("Welcome, " + username + "!");
                }
            });
        } else {
            tv.setText("Not signed in");
        }
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
        if(fAuth.getCurrentUser() == null) {
            Toast.makeText(this, "Please sign in to add dogs", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent i = new Intent(this, AddActivity.class);
        startActivity(i);
    }

    /**
     * Transfers user to SignInActivity
     * @param view Required for onClick event
     */
    public void showSignIn(View view) {
        if(fAuth.getCurrentUser() != null) {
            Toast.makeText(this, "You are already signed in", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent i = new Intent(this, SignInActivity.class);
        startActivity(i);
    }

    /**
     * Logs user out
     * @param view Required for onClick event
     */
    public void logOut(View view) {
        if(fAuth.getCurrentUser() == null) {
            Toast.makeText(this, "You are not signed in", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseAuth.getInstance().signOut();
        Toast.makeText(this, "Signed out", Toast.LENGTH_SHORT).show();
        TextView tv = findViewById(R.id.currentUserTextView);
        tv.setText("Not signed in");
    }
}

