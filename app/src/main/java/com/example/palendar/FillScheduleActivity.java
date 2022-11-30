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

        Event myEvent;

        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_fill_schedule);
            Intent intent = getIntent();

            //ArrayList<Time> dataToDisplay = intent.getParcelableExtra(CreateEventActivity.ARRAYLIST_VALUES);
            myEvent = intent.getParcelableExtra(CreateEventActivity.ARRAYLIST_VALUES);
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

            /*for(int i = 0; i < gridView.getChildCount(); i++){
                ToggleButton toggleButton = (ToggleButton) gridView.getChildAt(i);
                myEvent.getTimes().get(i).setChecked(toggleButton.isChecked());
            }
               */

            Intent intent = new Intent(FillScheduleActivity.this, HomeActivity.class);
            intent.putExtra(EVENT_VALUE, myEvent);
            startActivity(intent);

        }




}