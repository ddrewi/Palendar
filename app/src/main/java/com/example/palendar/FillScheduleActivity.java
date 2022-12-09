package com.example.palendar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
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
import java.util.concurrent.TimeUnit;

public class FillScheduleActivity extends AppCompatActivity {

        public static final String EVENT_VALUE = "Event Data";
        private static final String TAG = "Banana";
        User user;
        Event myEvent;


        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_fill_schedule);
            Intent intent = getIntent();

            //Here, we store the created event locally.
            myEvent = intent.getParcelableExtra(CreateEventActivity.EVENT_VALUE);

            TimeAdapter timeArrayAdapter = new TimeAdapter(this, myEvent.getTimes());

            GridView gridView = (GridView) findViewById(R.id.timeOptions);

            gridView.setAdapter(timeArrayAdapter);

        }

        public void updateAvailability(View view){
            GridView gridView = (GridView) findViewById(R.id.timeOptions);
            ArrayList<String> preferredTimes = new ArrayList<String>();

            //When the button is toggled, add 1 to the counter for the corresponding time.
            //Also add the name of the time to the user's String array.

            for(int i = 0; i < gridView.getChildCount(); i++){
                ToggleButton toggleButton = (ToggleButton) gridView.getChildAt(i);
                if (toggleButton.isChecked()){
                    int counter = myEvent.getTimes().get(i).getCounter();
                    myEvent.getTimes().get(i).setCounter(counter + 1);
                    String time = myEvent.getTimes().get(i).getTime();
                    preferredTimes.add(time);
                }
            }

            //At this point, the event is updated locally.

            user = new User(HomeActivity.firebaseHelper.getmAuth().getUid(), preferredTimes);
            Log.d(TAG, "" + user.getUserID());

            ArrayList<User> users = myEvent.getUsers();
            users.add(user);

            myEvent.setUsers(users);
            myEvent.setDocID(HomeActivity.firebaseHelper.getDocID());
            HomeActivity.firebaseHelper.editEvent(myEvent);

        }


        public void switchScreens(View view){
            Intent intent = new Intent(FillScheduleActivity.this, ViewEventActivity.class);
            intent.putExtra(EVENT_VALUE, myEvent);
            startActivity(intent);
        }



       /* public void setTimes(View view){
            GridView gridView = (GridView) findViewById(R.id.timeOptions);
            ArrayList<String> preferredTimes = new ArrayList<String>();

            //When the button is toggled, add 1 to the counter for the corresponding time.
            //Also add the name of the time to the user's String array.

            for(int i = 0; i < gridView.getChildCount(); i++){
                ToggleButton toggleButton = (ToggleButton) gridView.getChildAt(i);
                if (toggleButton.isChecked()){
                    int counter = myEvent.getTimes().get(i).getCounter();
                    myEvent.getTimes().get(i).setCounter(counter + 1);
                    String time = myEvent.getTimes().get(i).getTime();
                    preferredTimes.add(time);
                }
            }

            //At this point, the event is updated locally.

            user = new User(HomeActivity.firebaseHelper.getmAuth().getUid(), preferredTimes);
            Log.d(TAG, "" + user.getUserID());

            ArrayList<User> users = myEvent.getUsers();
            users.add(user);

            myEvent.setUsers(users);
            myEvent.setDocID(HomeActivity.firebaseHelper.getDocID());

            Log.d(TAG, "Before editEvent, " + myEvent.getDocID());

            HomeActivity.firebaseHelper.editEvent(myEvent);
            // At this point, asynch has not updated numUsers.

            Intent intent = new Intent(FillScheduleActivity.this, ViewEventActivity.class);
            intent.putExtra(EVENT_VALUE, myEvent);
            startActivity(intent);
        }*/
        
}