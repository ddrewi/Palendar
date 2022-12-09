package com.example.palendar;

import static androidx.core.content.ContextCompat.createDeviceProtectedStorageContext;
import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.security.ConfirmationCallback;
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

import java.lang.invoke.ConstantCallSite;
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
    public final String TAG = "FBH";
    private static String uid = null;      // var will be updated for currently signed in user
    private FirebaseAuth mAuth;
    private Event currentEvent;
    private String docID = null;
    private int numUsers = 0;

    private FirebaseFirestore db;
    private ArrayList<Event> events;   // will refer to all the events created and stored in Firestore


    public FirebaseHelper() {
        mAuth = FirebaseAuth.getInstance();
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


    public void addEventToFirestore(Event event){
        // add Event event to the database
        // this method is overloaded and incorporates the interface to handle the asynch calls
        addEventToFirestore(event, new FirestoreCallback() {
            @Override
            public void onCallback(Event event) {
                Log.i(TAG, "Inside addEvent, onCallback " + event.getDocID());
            }
        });
    }


    private void addEventToFirestore(Event event, FirestoreCallback firestoreCallback) {
        db.collection("events").add(event)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    public void onSuccess(DocumentReference documentReference) {
                        Log.i(TAG, "added successfully");
                        docID = documentReference.getId();
                        db.collection("events").document(documentReference.getId()).update("docID", documentReference.getId());
                        readData(firestoreCallback);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i(TAG, "Error adding document", e);
                    }
                });
    }


    public void editEvent(Event event){
        // add Event event to the database
        // this method is overloaded and incorporates the interface to handle the asynch calls
        editEvent(event, new FirestoreCallback() {
            @Override
            public void onCallback(Event event) {
                Log.i(TAG, "Inside editEvent, onCallback " + event.getDocID() + " " + numUsers);
            }
        });
    }


    private void editEvent(Event event, FirestoreCallback firestoreCallback){
        String docID = event.getDocID();
        Log.d(TAG, "Inside editEvent " + docID);

        db.collection("events").document(docID)
                .set(event)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.i(TAG, "Success updating document");
                        currentEvent = event;
                        readData(firestoreCallback);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i(TAG, "Error updating document", e);
                    }
                });
    }


    private void readData(FirestoreCallback firestoreCallback) {
        currentEvent = null;
        db.collection("events").document(docID)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            currentEvent = task.getResult().toObject(Event.class);
                            docID = currentEvent.getDocID();
                            numUsers = currentEvent.getUsers().size();
                            Log.d(TAG, "reached readData " + docID + " " + numUsers);
                            //This line is necessary for onCallback
                            firestoreCallback.onCallback(currentEvent);
                        } else {
                            Log.d(TAG, "grabbing current event not successful");
                        }
                    }
                });

    }


/*    private void joinEvent(String code, FirestoreCallback firestoreCallback){
        db.collection("events").document(code)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            currentEvent = task.getResult().toObject(Event.class);
                            currentEvent.get
                        }

                    }
                                       }

    }*/

//on data change listener


    public int getNumUsers() {
        return numUsers;
    }

    public void setNumUsers(int numUsers) {
        this.numUsers = numUsers;
    }

    public String getDocID() {
        return docID;
    }


    //https://stackoverflow.com/questions/48499310/how-to-return-a-documentsnapshot-as-a-result-of-a-method/48500679#48500679
    public interface FirestoreCallback {
        void onCallback(Event event);
    }

    public FirebaseFirestore getDb() {
        return db;
    }

    public void setDb(FirebaseFirestore db) {
        this.db = db;
    }
}