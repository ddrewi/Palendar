package com.example.palendar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class FillScheduleActivity extends AppCompatActivity {

        @SuppressLint("ClickableViewAccessibility")
        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_fill_schedule);

            Button button = (Button) findViewById(R.id.testButton);
            button.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction() == MotionEvent.ACTION_UP){

                        // Do what you want
                        return true;
                    }
                    return false;
                }
            });
        }





//    ToggleButton toggleButton = (ToggleButton) findViewById(R.id.testToggleButton);
//    View.OnHoverListener
//    toggleButton.(new View.OnClickListener() {
//        public void onClick(View v) {
//            startActivity(new Intent(MainActivity.this,StandingsActivity.class));
//        }
//    });
}