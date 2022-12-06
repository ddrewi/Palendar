package com.example.palendar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ToggleButton;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class FillScheduleActivity extends AppCompatActivity {

        public static final String EVENT_VALUE = "Event Data";
        public static FirebaseHelper firebaseHelper;
        User user;
        Event myEvent;

        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_fill_schedule);
            Intent intent = getIntent();
            firebaseHelper = new FirebaseHelper();
            //ArrayList<Time> dataToDisplay = intent.getParcelableExtra(CreateEventActivity.ARRAYLIST_VALUES);
            myEvent = intent.getParcelableExtra(CreateEventActivity.EVENT_VALUE);
//            ArrayList<Time> timeList = new ArrayList<>();
//
//            for(int i = 0; i < dataToDisplay.size(); i++){
//                timeList.add(new Time(dataToDisplay.get(i)));
//            }

            TimeAdapter timeArrayAdapter = new TimeAdapter(this, myEvent.getTimes());

//            ArrayAdapter<String> listAdapter = new ArrayAdapter<>(
//                    this, android.R.layout.simple_list_item_1, dataToDisplay);

            GridView gridView = (GridView) findViewById(R.id.timeOptions);

            gridView.setAdapter(timeArrayAdapter);


        }

        public void setTimes(View view){
            GridView gridView = (GridView) findViewById(R.id.timeOptions);
            boolean[] preferredTimes = new boolean[gridView.getChildCount()];

            /*
            int testInt = 0;
            for(int i = 0; i < gridView.getChildCount(); i++){
                testInt++;
            }
            Log.d("Setting", "" + testInt);
             */

            for(int i = 0; i < gridView.getChildCount(); i++){
                ToggleButton toggleButton = (ToggleButton) gridView.getChildAt(i);
                if (toggleButton.isChecked()){
                    int counter = myEvent.getTimes().get(i).getCounter();
                    myEvent.getTimes().get(i).setCounter(counter + 1);
                    preferredTimes[i] = true;
                }
                else {
                    preferredTimes[i] = false;
                }
                //Log.d("morning", "" + myEvent.getTimes().get(i).getTime() + myEvent.getTimes().get(i).getCounter());
                Log.d("morning", myEvent.getTimes().get(i).getTime() + preferredTimes[i]);
            }

            // At this point, the event should be populated locally.

            user = new User(firebaseHelper.getmAuth().getUid(), preferredTimes);
            Log.d("afternoon", "" + user.getUserID());

            ArrayList<User> users = myEvent.getUsers();
            users.add(user);
            myEvent.setUsers(users);


            myEvent.setDocID(myEvent.getDocID());


            firebaseHelper.editEvent(myEvent);


            Intent intent = new Intent(FillScheduleActivity.this, HomeActivity.class);
            intent.putExtra(EVENT_VALUE, myEvent);
            startActivity(intent);

        }




}