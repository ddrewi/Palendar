package com.example.palendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;

public class HomeActivity extends AppCompatActivity {
    public static final String EVENT_VALUE = "DATA TO DISPLAY ON NEXT PAGE";

    // Followed this tutorial for this activity
    // Does a great job explaining the code
    // https://www.youtube.com/watch?v=bBJF1M5h_UU


    public static FirebaseHelper firebaseHelper;

    Button logout;
    EditText joinCodeEditText;
    Spinner spinner;
    Button viewEventButton;
    Event selectedEvent;
    ArrayList<Event> userEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firebaseHelper = new FirebaseHelper();

        joinCodeEditText = findViewById(R.id.joinCodeEditText);
        logout = findViewById(R.id.logout);
        viewEventButton = findViewById(R.id.viewEventsButton);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            //name.setText(signInAccount.getDisplayName());
            //mail.setText(signInAccount.getEmail());
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),SignInActivity.class);
                startActivity(intent);
            }
        });


        // ***************** NEED WAY TO ADD EVENTS FROM FIRESTORE HERE ***************************

        userEvents = new ArrayList<>();
        /*CollectionReference allEvents = firebaseHelper.getDb().collection("events");
        firebaseHelper.getDb().collection("events")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot doc : task.getResult()) {
                                Log.d("peanuts", doc.getId() + " => " + doc.getData());
                                Event event = doc.toObject(Event.class);
                                userEvents.add(event);
                                Log.d("peanuts", "" + userEvents.get(0).getName());
                                if(event.getUsers().contains(firebaseHelper.getmAuth().getUid())){
                                    userEvents.add(event);
                                    Log.d("peanuts", "" + firebaseHelper.getmAuth().getUid());
                                }
                            }
                        } else {
                            Log.d("peanuts", "Error getting documents: ", task.getException());
                        }
                        Log.d("peanuts", "" + userEvents.get(0).getName());
                    }
                });*/

        ArrayList<String> userEventNames = new ArrayList<String>();
        for(int i = 0; i < userEvents.size(); i++){
            userEventNames.add(userEvents.get(i).getName());
        }


        spinner = findViewById(R.id.eventSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_list_item, userEventNames);
        adapter.setDropDownViewResource(R.layout.spinner_list_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedEvent = userEvents.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        viewEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ViewEventActivity.class);
                intent.putExtra(EVENT_VALUE, selectedEvent);
                startActivity(intent);
            }
        });

    }





    public void createEvent (View view) {
        Intent intent = new Intent(HomeActivity.this, CreateEventActivity.class);
        startActivity(intent);
    }

    public void joinEvent(){
        String eventName = joinCodeEditText.getText().toString();




        //firebaseHelper.addUserToEvent(eventName);
    }

}