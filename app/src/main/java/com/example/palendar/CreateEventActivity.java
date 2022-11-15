package com.example.palendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateEventActivity extends AppCompatActivity {

    public static final String ARRAYLIST_VALUES = "Data to display in gridView";

    EditText eventNameET;
    Spinner spinner1;
    Spinner spinner2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        eventNameET = findViewById(R.id.eventNameET);
        spinner1 = findViewById(R.id.time1Spinner);
        spinner2 = findViewById(R.id.time2Spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_custom_spinner_dropdown, getResources().getStringArray(R.array.times));

        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        // spinner1.setOnItemSelectedListener(this);
        // spinner2.setOnItemSelectedListener(this);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView,
                                    View itemView, int position, long id) {
                Intent intent = new Intent(CreateEventActivity.this, FillScheduleActivity.class);


                // create an ArrayList of whatever type of data you are displaying in this app
                // In this particular app, everything is centralized around the Food class.
                ArrayList<String> listData = new ArrayList<>();


                // Send this particular ArrayList of Food data to the next activity, where we display
                // each name of each object in the ArrayList.
                intent.putStringArrayListExtra(ARRAYLIST_VALUES, listData);
                startActivity(intent);

            }
        };

    }

    public void createEvent(View view){
        Event event = new Event(eventNameET.toString(),spinner1.toString(), spinner2.toString());
        Log.d("Ariagno", "Event Created");

        Intent intent = new Intent(CreateEventActivity.this, FillScheduleActivity.class);
        startActivity(intent);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }
    // This method is required, even if empty, for the OnItemSelectedListener to work

    public void onNothingSelected(AdapterView<?> parent) { }
}