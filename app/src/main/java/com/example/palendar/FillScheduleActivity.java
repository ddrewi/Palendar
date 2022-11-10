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

            ArrayList<String> dataToDisplay = new ArrayList<String>();

            dataToDisplay.add("string1");
            dataToDisplay.add("string2");
            dataToDisplay.add("string1");
            dataToDisplay.add("string2");
            dataToDisplay.add("string1");
            dataToDisplay.add("string2");
            dataToDisplay.add("string1");
            dataToDisplay.add("string2");
            dataToDisplay.add("string1");
            dataToDisplay.add("string2");
            dataToDisplay.add("string1");
            dataToDisplay.add("string2");
            dataToDisplay.add("string1");
            dataToDisplay.add("string2");
            dataToDisplay.add("string1");
            dataToDisplay.add("string2");

            ArrayAdapter<String> listAdapter = new ArrayAdapter<>(
                    this, android.R.layout.simple_list_item_1, dataToDisplay);

            GridView gridView = (GridView) findViewById(R.id.timeOptions);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, dataToDisplay);

            gridView.setAdapter(adapter);

//            for(int i = 0; i < dataToDisplay.size(); i++){
//                ToggleButton toggleButton = findViewById(R.id.toggleButton00
//                toggleButton.setTextOff(dataToDisplay.get(i));
//                toggleButton.setTextOff(dataToDisplay.get(i));
//            }


        }





//    ToggleButton toggleButton = (ToggleButton) findViewById(R.id.testToggleButton);
//    View.OnHoverListener
//    toggleButton.(new View.OnClickListener() {
//        public void onClick(View v) {
//            startActivity(new Intent(MainActivity.this,StandingsActivity.class));
//        }
//    });
}