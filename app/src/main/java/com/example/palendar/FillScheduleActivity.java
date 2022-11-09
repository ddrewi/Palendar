package com.example.palendar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class FillScheduleActivity extends AppCompatActivity {


        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_fill_schedule);

            Intent intent = getIntent();

            ArrayList<String> dataToDisplay = intent.getStringArrayListExtra(CreateEventActivity.ARRAYLIST_VALUES);

            ArrayAdapter<String> listAdapter = new ArrayAdapter<>(
                    this, android.R.layout.simple_list_item_1, dataToDisplay);

            GridView gridView = (GridView) findViewById(R.id.timeOptions);
            Log.i("Mark", "grid");
            gridView.setAdapter(listAdapter);

        }





//    ToggleButton toggleButton = (ToggleButton) findViewById(R.id.testToggleButton);
//    View.OnHoverListener
//    toggleButton.(new View.OnClickListener() {
//        public void onClick(View v) {
//            startActivity(new Intent(MainActivity.this,StandingsActivity.class));
//        }
//    });
}