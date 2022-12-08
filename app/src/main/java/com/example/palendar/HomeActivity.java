package com.example.palendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    public static final String EVENT_VALUE = "DATA TO DISPLAY ON NEXT PAGE";

    // Followed this tutorial for this activity
    // Does a great job explaining the code
    // https://www.youtube.com/watch?v=bBJF1M5h_UU



    TextView name, mail;
    Button logout;
    EditText joinCodeEditText;
    public static FirebaseHelper firebaseHelper;
    Spinner spinner;
    Button viewEventButton;
    Event selectedEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        joinCodeEditText = findViewById(R.id.joinCodeEditText);
        logout = findViewById(R.id.logout);
        //name = findViewById(R.id.name);
        //mail = findViewById(R.id.mail);
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

        ArrayList<Event> myEvents = new ArrayList<Event>();
        ArrayList<String> myEventNames = new ArrayList<String>();
        for(int i = 0; i < myEvents.size(); i++){
            myEventNames.add(myEvents.get(i).getName());
        }

        //***** TEMPORARY **********************
        for(int i = 0; i < 5; i++){
            myEvents.add(new Event());
            ArrayList<Time> tempTimes = new ArrayList<>();
            for(int k = 0; k < 5; k++){
                tempTimes.add(new Time("" + i));
            }
            myEvents.get(i).setTimes(tempTimes);
            myEventNames.add("event " + i);
        }
        spinner = findViewById(R.id.eventSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_list_item, myEventNames);
        adapter.setDropDownViewResource(R.layout.spinner_list_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedEvent = myEvents.get(i);
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