package com.example.palendar;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * The purpose of this class is to hold ALL the code to communicate with Firebase.  This class
 * will connect with Firebase auth and Firebase firestore.  Each class that needs to verify
 * authentication OR access data from the database will reference a variable of this class and
 * call a method of this class to handle the task.  Essentially this class is like a "gopher" that
 * will go and do whatever the other classes want or need it to do.  This allows us to keep all
 * our other classes clean of the firebase code and also avoid having to update firebase code
 * in many places.  This is MUCH more efficient and less error prone.
 */
public class FirebaseHelper {
    public final String TAG = "Firebase";
    private static String uid = null;      // var will be updated for currently signed in user
    private FirebaseAuth mAuth;


    private FirebaseFirestore db;
    private ArrayList<Event> events;   // will refer to all the events created and stored in Firestore


    public FirebaseHelper() {
        db = FirebaseFirestore.getInstance();
        events = new ArrayList<>(); // instantiate arraylist for app use

    }


    public FirebaseAuth getmAuth() {
        return mAuth;
    }


    public void logOutUser() {
        mAuth.signOut();
        this.uid = null;
    }


    public void updateUid(String uid) {
        this.uid = uid;
    }


    public void addEventToFirestore(Event event) {
        Log.d("Andrew", "fxn called");
        db.collection("events").add(event)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    public void onSuccess(DocumentReference documentReference) {
                        Log.i("Andrew", "added successfully");
                        db.collection("events").document(documentReference.getId()).update("docID", documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("Ishaan", "Error adding document", e);
                    }
                });

    }

    public void editEvent(Event event){
        String docId = event.getDocID();
        db.collection("events").document(docId).set(event);
    }


    public void addUserToEvent(String code){
        /* For every event document in the events collection,
            if the code matches the document's docID,
            then add the user to the user array list.
            Need to add checks for events that the user
            is already a part of (duplicate users)
            if successful, switch screen to fill screen

            OR

            try retrieving data
            if fails, then return toast?
         */


        //users collection needs to be created
        // user -> this user?

    }




}