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


        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_fill_schedule);

            Intent intent = getIntent();

            // ArrayList<String> dataToDisplay = intent.getStringArrayListExtra(CreateEventActivity.ARRAYLIST_VALUES);

            ArrayList<Time> dataToDisplay = new ArrayList<Time>();

            dataToDisplay.add(new Time("12:00"));

            ArrayAdapter<Time> timeArrayAdapter = new ArrayAdapter<>(
                    this, android.R.layout.simple_list_item_1, dataToDisplay);

            TimeAdapter myTimeAdapter = new TimeAdapter(this, dataToDisplay);

            GridView gridView = (GridView) findViewById(R.id.timeOptions);

//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, dataToDisplay);

//            gridView.setAdapter(adapter);
            


        }


    public ArrayList<String> getTimes(String time1, String time2){
        int firstTimeIndex = 0;
        int secondTimeIndex = 0;

        ArrayList<String> timesToDisplay = new ArrayList<>();
        String[] timeStrings = getResources().getStringArray(R.array.times);



        for(int i = 0; i < timeStrings.length; i++){
            if(time1.equals(timeStrings[i])){
                firstTimeIndex = i;
            } else if(time2.equals(timeStrings[i])){
                secondTimeIndex = i;
            }
        }

        if(firstTimeIndex < secondTimeIndex){
            for(int i = firstTimeIndex; i < secondTimeIndex; i++){
                timesToDisplay.add(timeStrings[i]);
            }
        }else{
            for(int i = firstTimeIndex; i <timeStrings.length; i++){
                timesToDisplay.add(timeStrings[i]);
            }
            for(int i = 0; i < secondTimeIndex; i++){
                timesToDisplay.add(timeStrings[i]);
            }
        }

        return timesToDisplay;
    }

}