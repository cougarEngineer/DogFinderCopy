package com.example.dogfinder;

import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


public class DogProfileDatabaseInteractor {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Gson gson = new Gson();

    public void add(DogProfile dp) {
        Map<String, String> data = new HashMap<>();
        data.put("profile", gson.toJson(dp));
        db.collection("profiles")
                .add(data)
                .addOnSuccessListener(documentReference -> Log.d("database set", "DocumentSnapshot written with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w("database set", "Error adding document", e));
    }
}
