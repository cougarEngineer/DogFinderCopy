package com.example.dogfinder;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DogProfileDatabaseInteractor {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Gson gson = new Gson();

    public List<DogProfile> get() {
        ArrayList<DogProfile> list = new ArrayList<>();
        db.collection("profiles").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> data = document.getData();
                                list.add(gson.fromJson((String) data.get("profile"), DogProfile.class));
                                Log.d("database get", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d("database get", "Error getting documents: ", task.getException());
                        }
                    }
                });
        return list;
    }

    public void add(DogProfile dp) {
        Map<String, String> data = new HashMap<>();
        data.put("profile", gson.toJson(dp));
        db.collection("profiles")
                .add(data)
                .addOnSuccessListener(documentReference -> Log.d("database set", "DocumentSnapshot written with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w("database set", "Error adding document", e));
    }


    //temp
    /*
    public List<DogProfile> get() {
        List<DogProfile> list = new ArrayList<>();
        DogProfile temp = new DogProfile();
        temp.setName("Fido");
        list.add(temp);
        return list;
    }
    public void add(DogProfile dp){
    }

     */
}
