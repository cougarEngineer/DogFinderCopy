package com.example.dogfinder;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import java.util.List;


public class DogProfileDatabaseInteractor {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    /*
    public List<DogProfile> get() {
        ArrayList<DogProfile> list = new ArrayList<>();
        db.collection("profiles").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                list.add(document.toObject(DogProfile.class));
                                Log.d("database get", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d("database get", "Error getting documents: ", task.getException());
                        }
                    }
                });
        return list;
    }
    public void add(DogProfile dp){
db.collection("profiles")
        .add(dp)
        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d("database set", "DocumentSnapshot written with ID: " + documentReference.getId());
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("database set", "Error adding document", e);
            }
        });
    }


     */
    //temp
    public List<DogProfile> get() {
        return new ArrayList<>();
    }
    public void add(DogProfile dp){

    }
}
